/**
 * <p>文件名称：PersionQueue.java</p>
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
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author pwl
 * @version 1.0    2019/3/18
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
public class RoomQueue
{
    private static Map<Integer, Room> roomQueue = new HashMap<>();
    
    public static synchronized void add(Room r)
    {
        if (!roomQueue.containsKey(r.roomId))
        {
            roomQueue.put(r.roomId, r);
        }
    }
    
    public static synchronized void remove(Room r)
    {
        if (roomQueue.containsKey(r.roomId))
        {
            roomQueue.remove(r.roomId);
        }
    }
    
    
    public static Room getRoom(int id)
    {
        return roomQueue.get(id);
    }
    
    public static int getSize()
    {
        return roomQueue.size();
    }
}
