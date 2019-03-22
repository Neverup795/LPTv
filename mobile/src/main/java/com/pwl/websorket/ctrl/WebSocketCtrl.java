/**
 * <p>文件名称：WebSocketServer.java</p>
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
package com.pwl.websorket.ctrl;

import com.pwl.websorket.bean.*;
import com.pwl.websorket.constant.Action;
import com.pwl.websorket.util.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.UUID;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author pwl
 * @version 1.0    2019/3/18
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
@ServerEndpoint("/websorket/{id}")
@Component
public class WebSocketCtrl
{
    static Logger log = LoggerFactory.getLogger(WebSocketCtrl.class);
    
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id)
    {
        Person p = null;
        if ("0".equals(id))
        {
            p = new Person(session, UUID.randomUUID().toString());
        }
        else
        {
            p = new Person(session, id);
        }
        p.setName(p.getId().substring(0, 3));
        PersionQueue.add(p);
        log.info("新用户加入：{},当前在线人数为:{}", p.getId(), PersionQueue.getSize());
        MsgUtil.sendMsg(p, new SorketMsg(Action.OPEN_SORKET, p));
    }
    
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session)
    {
        Person p = PersionQueue.getPerson(session);
        int roomId = p.getRoomId();
        if (roomId != -1)
        {
            Room room = RoomQueue.getRoom(roomId);
            room.removeRoom(p);
            if (room.getMember().size() > 0)
            {
                MsgUtil.sendRoomMsg(room, new SorketMsg(Action.LEAVE_ROOM, p));
            }
            
        }
        PersionQueue.remove(p);
        log.info("{}:连接关闭！当前在线人数为:{}", p.getId(), PersionQueue.getSize());
    }
    
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        Person person = PersionQueue.getPerson(session);
        int roomId = person.getRoomId();
        person.setMsg(message);
        if (roomId != -1)
        {
            Room room = RoomQueue.getRoom(roomId);
            MsgUtil.sendRoomMsg(room, new SorketMsg(Action.ROOM_MSG, person));
        }
    }
    
    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error)
    {
        log.error("发生错误");
        error.printStackTrace();
    }
    
}
