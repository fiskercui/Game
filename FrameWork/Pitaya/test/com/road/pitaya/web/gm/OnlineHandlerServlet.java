/**
 *All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.pitaya.web.gm;

import com.google.gson.Gson;
import com.road.pitaya.web.BaseHandlerServlet;


/**
 * @author yip
 * @date 2013-5-21
 * @version 尚未实现
 */
public class OnlineHandlerServlet extends BaseHandlerServlet
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see com.road.pitaya.gm.IHandlerCmd#execute(java.lang.String)
     */
    @Override
    public String execute(String jsonString)
    {
        Gson gson = new Gson();
        BeanDemo demo = gson.fromJson(jsonString, BeanDemo.class);
        return Integer.toString(demo.op);
    }
    
}
