/**
 * <p>文件名称：Person.java</p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2012-2018</p>
 * <p>公    司：金证财富南京科技有限公司</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2019/3/18</p>
 * <p>修改记录1：</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 * @version 1.0
 * @author pwl
 */
package com.pwl.websorket.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.websocket.Session;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author pwl
 * @version 1.0    2019/3/18
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
public class Person
{
    String id     = "";
    String name   = "";
    int    roomId = -1;
    int    type   = 0;
    String msg    = "";
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    @JSONField(serialize = false)
    Session obj = null;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getRoomId()
    {
        return roomId;
    }
    
    public void setRoomId(int roomId)
    {
        this.roomId = roomId;
    }
    
    public Session getObj()
    {
        return obj;
    }
    
    public Person(Session obj, String id)
    {
        this.id = id;
        this.obj = obj;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public int getType()
    {
        return type;
    }
    
    public void setType(int type)
    {
        this.type = type;
    }
}
