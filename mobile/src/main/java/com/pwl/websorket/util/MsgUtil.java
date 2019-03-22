/**
 * <p>文件名称：MsgUtil.java</p>
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
package com.pwl.websorket.util;

import com.alibaba.fastjson.JSON;
import com.pwl.websorket.bean.SorketMsg;
import com.pwl.websorket.bean.PersionQueue;
import com.pwl.websorket.bean.Person;
import com.pwl.websorket.bean.Room;

import javax.websocket.Session;
import java.io.IOException;
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
public class MsgUtil extends PersionQueue
{
    public static void sendMsg(Person p, SorketMsg msg)
    {
        sendMsg(p.getObj(), msg);
    }
    
    public static void sendMsg(Session session, SorketMsg msg)
    {
        try
        {
            session.getBasicRemote().sendText(JSON.toJSONString(msg));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void sendRoomMsg(Room room, SorketMsg msg)
    {
        Map<String, Person> map = room.getMember();
        Iterator<Map.Entry<String, Person>> iter = map.entrySet().iterator();
        while (iter.hasNext())
        {
            Map.Entry<String, Person> person = iter.next();
            sendMsg(person.getValue(), msg);
        }
    }
    
    public static void sendAllMsg(SorketMsg msg)
    {
        Map<Session, Person> map = getSessionQueue();
        Iterator<Map.Entry<Session, Person>> iter = map.entrySet().iterator();
        while (iter.hasNext())
        {
            Map.Entry<Session, Person> per = iter.next();
            sendMsg(per.getKey(), msg);
        }
    }
}
