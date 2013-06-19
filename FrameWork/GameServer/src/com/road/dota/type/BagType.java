/**
*All rights reserved. This material is confidential and proprietary to 7ROAD
*/
package com.road.dota.type;

/**
 * @author : cookie
 * @date : 2012-12-13
 * @version 
 * 背包类型
 */
public enum BagType
{
    NULL(0),            //没有放入任何背包的物品
    MAIN(1),            //主背包
    EQUIP(2),           //装备背包
    FIGHT(3),           //战斗道具背包
    TEMP(4),            //临时背包(铁匠铺，抽奖神马的)
    HIDE(5),            //隐藏背包(战斗中拾取道具神马的)
    VICE(6),            //副将快捷栏背包(包括当前英雄所有可选副将)
    FIGHT_LIVING(7),    //副将战斗背包(已经选定需要出征的副将的背包) 包括6和8
    SOLDIER(8),         //战士快捷栏背包(包括当前英雄所有可选战士)
    FIGHT_SKILL1(9),     //技能背包，进入战斗的技能
    FIGHT_SKILL2(10),     //技能背包，进入战斗的技能
    FIGHT_SKILL3(11),     //技能背包，进入战斗的技能
    VICE_EQUIP(12);      //副将装备栏
    private byte value;
    
    private BagType(int value)
    {
        this.value = (byte) value;
    }
    
    /**
     * @return the value
     */
    public byte getValue()
    {
        return value;
    }
    
    public static BagType parse(int value)
    {
        BagType type = BagType.MAIN;
        switch (value)
        {
            case 0:
                type = BagType.NULL;
                break;
            case 1:
                type = BagType.MAIN;
                break;
            case 2:
                type = BagType.EQUIP;
                break;
            case 3:
                type = BagType.FIGHT;
                break;
            case 4:
                type = BagType.TEMP;
                break;
            case 5:
                type = BagType.HIDE;
                break;
            case 6:
                type = BagType.VICE;
                break;
            case 7:
                type = BagType.FIGHT_LIVING;
                break;
            case 8:
                type = BagType.SOLDIER;
                break;
            case 9:
                type = BagType.FIGHT_SKILL1;
                break;
            case 10:
                type = BagType.FIGHT_SKILL2;
                break;
            case 11:
                type = BagType.FIGHT_SKILL3;
                break;
            case 12:
                type = BagType.VICE_EQUIP;
                break;
            default:
                break;
        }
        return type;
    }
}
