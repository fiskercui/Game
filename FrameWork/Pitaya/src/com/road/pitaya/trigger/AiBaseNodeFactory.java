package com.road.pitaya.trigger;

/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.util.ClassUtil;



/**
 * @author : cookie
 * @date : 2012-11-28
 * @version 
 * Ai结点创建工厂
 */
public abstract class AiBaseNodeFactory<M, N, T extends Annotation> implements IAiNodeFactory<M, N>
{
    private Logger LOGGER = LoggerFactory.getLogger(AiBaseNodeFactory.class.getName());
    
    //handle的实例，这里保存每个AiTreeNode的实例，如果能够重复利用的AiTreeNode就用这里的
    private HashMap<Integer, AiTreeNode<?, ?>> handlesInstance = new HashMap<Integer, AiTreeNode<?,?>>();
    
    //handle的class，有些类有参数可能需要实时创建
    private HashMap<Integer, Class<?>> handlesClass = new HashMap<Integer, Class<?>>();
    
    public boolean init()
    {
        reload();
        return true;
    }

    public void reload()
    {
        handlesInstance.clear();
        handlesClass.clear();
        int count = SearchCommandHandlers(getPackPath(), getAnnoClass());
        LOGGER.info(String.format("total load %d gameConditions.", count));
    }

    //取得注释的类型
    public abstract Class<T> getAnnoClass();
    
    //取得包路径
    public abstract String getPackPath();
    
    //取得类型
    public abstract Integer getNodeType(T annotation);
    
    /**
     * 创建一个游戏条件
     * @param type
     *          类型
     * @param needNew
     *          是否需要重新创建
     * @param args
     *          构造函数的参数
     * @return
     */
    @Override
    public AiTreeNode<?, ?> createAiTreeNode(int type, boolean needNew, String args)
    {
        //如果不需要重新建的话，就用原有的
        if (!needNew)
        {
            return handlesInstance.get(type);
        }
        
        //如果需要重新创建的话，就用附加参数构造一个
        Class<?> class1 = handlesClass.get(type);
        AiTreeNode<?, ?> baseNode = null;
        try
        {
           baseNode = (AiTreeNode<?, ?>)ClassUtil.newInstance(class1, args);
        }
        catch (Exception e)
        {
            LOGGER.error("BaseNodeFactory:Create new AiTreeNode err. nodeNum:" + type, e);
        }
        return baseNode;
    }
    
    /**
     * 查找加了GameCmdAnnotation标注的类作为游戏数据包处理器
     * @return
     */
    protected int SearchCommandHandlers(String packPath, Class<T> class2)
    {
        int count = 0;
        Package pack = Package.getPackage(packPath);
        Set<Class<?>> allClasses = ClassUtil.getClasses(pack);
        for (Class<?> class1 : allClasses)
        {
            T annotation = class1.getAnnotation(class2);

            if (annotation != null)
            {
                try
                {
                    handlesInstance.put(getNodeType(annotation),
                                (AiTreeNode<?, ?>) class1.newInstance());
                    handlesClass.put(getNodeType(annotation), class1);
                    count++;
                }
                catch (InstantiationException e)
                {
                    LOGGER.error("BaseNodeFactory:SearchCommandHandlers", e);
                }
                catch (IllegalAccessException e)
                {
                    LOGGER.error("BaseNodeFactory:SearchCommandHandlers", e);
                }
            }
        }
        return count;
    }
}
