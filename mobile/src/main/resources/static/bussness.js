var ACTION = {
    JS_EVAL: 9999,
    OPEN_SORKET: 1000,//websorket连接
    ROOM_MSG: 1001,//房间对话连接
    ENTER_ROOM: 2000, //进入房间
    LEAVE_ROOM: 2001, //离开房间
};
bus = function (msg) {
    var data = JSON.parse(msg.data);
    if (typeof (data) != 'object') {
        kdrec.log("消息解析异常");
    }
    switch (data.action) {
        case ACTION.OPEN_SORKET :
            kdrec.log("init websorket");
            kdrec.log(msg);
            kdrec.cache.set("token", data.msg.id);
            break;
        case ACTION.ENTER_ROOM :
            kdrec.log("进入房间");
            kdrec.log(msg)
            break;
        case ACTION.ROOM_MSG :
            kdrec.log("房间消息");
            kdrec.log(msg)
            reMsg(msg)
            break;
        case ACTION.LEAVE_ROOM :
            kdrec.log("离开房间");
            kdrec.log(msg)
            break;
        default:
            break;
    }
};