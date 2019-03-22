/**
 * <p>文件名称：Room.java</p>
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author pwl
 * @version 1.0    2019/3/18
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
public class Room
{
    int                 roomId   = -1;
    String              ower     = "";
    String              roomName = "房间";
    String              roomPwd  = "";
    Map<String, Person> member   = new HashMap<>();
    
    public Room(int roomId, String roomName, String roomPwd)
    {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomPwd = roomPwd;
    }
    
    public Map<String, Person> getMember()
    {
        return member;
    }
    
    public int getRoomId()
    {
        return roomId;
    }
    
    public void setRoomId(int roomId)
    {
        this.roomId = roomId;
    }
    
    public String getRoomName()
    {
        return roomName;
    }
    
    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }
    
    public String getRoomPwd()
    {
        return roomPwd;
    }
    
    public void setRoomPwd(String roomPwd)
    {
        this.roomPwd = roomPwd;
    }
    
    public void addRoom(Person p)
    {
        if (this.member.size() == 0)
        {
            this.ower = p.getId();
        }
        if (!this.member.containsKey(p.getId()))
        {
            this.member.put(p.getId(), p);
        }
    }
    
    public void removeRoom(Person p)
    {
        if (this.member.containsKey(p.getId()))
        {
            this.member.remove(p.getId());
        }
        if (this.ower.equals(p.getId()))
        { //房主离开
            if (this.member.size() > 0)
            {
                Iterator<Map.Entry<String, Person>> iter = this.member.entrySet().iterator();
                Map.Entry<String, Person> person = iter.next();
                this.ower = person.getValue().getId();
            }
            else
            {
                this.ower = "";
                RoomQueue.remove(this);
            }
        }
    }
    
    public Boolean isEmpty()
    {
        return "".equals(ower);
    }
}
