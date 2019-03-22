var kdrec = {};
if (typeof(WebSocket) == "undefined") {
    alert("您的浏览器不支持WebSocket,请使用更高版本的IE或者使用谷歌浏览器");
}
/*业务事件*/
var BUSNESS = {
    ACTION: {
        JS_EVAL: 9999,
        OPEN_SORKET: 1000,//websorket连接
        ROOM_MSG: 1001,//房间对话连接
        ENTER_ROOM: 2000, //进入房间
        LEAVE_ROOM: 2001, //离开房间
    },
    JS_EVAL: function (data) {
        eval(data);
    },
    OPEN_SORKET: function (data) {
    },
    ROOM_MSG: function (data) {
    },
    ENTER_ROOM: function (data) {
    },
    LEAVE_ROOM: function (data) {
    }
};
kdrec.bus = function (msg) {
    var data = JSON.parse(msg.data);
    if (typeof (data) !== 'object') {
        kdrec.log("消息解析异常", 1);
    } else {
        switch (data.action) {
            case BUSNESS.ACTION.OPEN_SORKET :
                kdrec.log("打开sorket", 1);
                kdrec.cacheSet("token", data.msg.id);
                BUSNESS.OPEN_SORKET(data.msg);
                break;
            case BUSNESS.ACTION.ENTER_ROOM :
                kdrec.log("进入房间", 1);
                BUSNESS.ENTER_ROOM(data.msg);
                break;
            case BUSNESS.ACTION.ROOM_MSG :
                kdrec.log("房间消息", 1);
                BUSNESS.ROOM_MSG(data.msg);
                break;
            case BUSNESS.ACTION.LEAVE_ROOM :
                kdrec.log("离开房间", 1);
                BUSNESS.LEAVE_ROOM(data.msg);
                break;
            case BUSNESS.ACTION.JS_EVAL:
                kdrec.log("执行脚本", 1);
                BUSNESS.LEAVE_ROOM(data.msg);
                break;
            default:
                break;
        }
    }
};
kdrec.log = function (msg, leave) {
    if (leave == 1) {
        setTimeout(function () {
            console.log(msg);
        }, 1);
    } else {
        setTimeout(function () {
            console.log(msg);
        }, 1);
    }
};
kdrec.cacheGet = function (key) {
    return window.sessionStorage.getItem(key);
};
kdrec.cacheSet = function (key, msg) {
    window.sessionStorage.setItem(key, msg);
};
kdrec.getToken = function () {
    return kdrec.cacheGet("token");
}
kdrec.initWebsoket = function (url) {
    var ws = url || "ws://" + window.location.host + "/websorket/";
    if (kdrec.cacheGet("token")) {
        ws += kdrec.cacheGet("token");
    } else {
        ws += "0";
    }
    kdrec.socket = new WebSocket(ws);
    //打开事件
    kdrec.socket.onopen = function (msg) {
        BUSNESS.OPEN_SORKET(msg)
    };
    //获得消息事件
    kdrec.socket.onmessage = function (msg) {
        kdrec.bus(msg);
    };
    //关闭事件
    kdrec.socket.onclose = function (msg) {
    };
    //发生了错误事件
    kdrec.socket.onerror = function (msg) {
    }
};
kdrec.createRoom = function () {
    $.ajax({
        type: "POST",
        url: "/room/create",
        beforeSend: function (request) {
            request.setRequestHeader("token", window.sessionStorage.getItem("token"));
        },
        success: function (result) {
            kdrec.log(result)
            kdrec.cacheSet("croomid", result.data);
            $("#croom").val(result.data)
        }
    });
};
kdrec.enterRoom = function (roomid) {
    $.ajax({
        type: "POST",
        url: "/room/enter/" + roomid,
        beforeSend: function (request) {
            request.setRequestHeader("token", window.sessionStorage.getItem("token") || 0);
        },
        success: function (result) {
            kdrec.cacheSet("eroomid", result.data);
        }
    });
};