/**
 * <p>文件名称：RoomCtrl.java</p>
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
import com.pwl.websorket.constant.Constant;
import com.pwl.websorket.exception.BaseException;
import com.pwl.websorket.exception.ErrorCode;
import com.pwl.websorket.util.MsgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author pwl
 * @version 1.0    2019/3/18
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
@Controller
@RequestMapping("/room")
@ResponseBody
public class RoomCtrl
{
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public DataResult createRoom(HttpServletRequest request)
    {
        String personId = request.getHeader("token");
        if ("".equals(personId))
        {
            throw new BaseException(ErrorCode.NO_LOGIN);
        }
        Person person = PersionQueue.getPerson(personId);
        if (person.getRoomId() != Constant.EMPRT_ROOM)
        {
            DataResult ret = new DataResult();
            ret.setData(person.getRoomId());
            return ret;
        }
        int roomId = RoomQueue.getSize() + 100;
        Room room = new Room(roomId, "发冠军按", "");
        room.addRoom(person);
        RoomQueue.add(room);
        person.setRoomId(roomId);
        DataResult ret = new DataResult();
        ret.setData(roomId);
        return ret;
    }
    
    @RequestMapping("/enter/{roomId}")
    public DataResult enterRoom(HttpServletRequest request, @PathVariable int roomId)
    {
        String personId = request.getHeader("token");
        Person person = PersionQueue.getPerson(personId);
        person.setRoomId(roomId);
        Room room = RoomQueue.getRoom(roomId);
        room.addRoom(person);
        MsgUtil.sendRoomMsg(room, new SorketMsg(Action.ENTER_ROOM, person));
        DataResult ret = new DataResult();
        ret.setData(roomId);
        return ret;
    }
    
    @RequestMapping("/leave/{id}")
    public DataResult leaveRoom(HttpServletRequest request, @PathVariable int roomId)
    {
        String personId = request.getHeader("token");
        Person person = PersionQueue.getPerson(personId);
        person.setRoomId(-1);
        Room room = RoomQueue.getRoom(roomId);
        room.removeRoom(person);
        MsgUtil.sendRoomMsg(room, new SorketMsg(Action.LEAVE_ROOM, person.getId()));
        DataResult ret = new DataResult();
        ret.setData(roomId);
        return ret;
    }
}
