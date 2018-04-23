package com.abc12366.uc.util.wx;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhushuai 2017-7-27
 */
public class MsgMap {
    public static final Map<String, Integer> msgmapping = new HashMap<String, Integer>() {
        /**
         *消息类型
         */
        private static final long serialVersionUID = 1L;

        {
            put("text", Integer.valueOf(0));
            put("image", Integer.valueOf(1));
            put("voice", Integer.valueOf(2));
            put("video", Integer.valueOf(3));
            put("shortvideo", Integer.valueOf(4));
            put("location", Integer.valueOf(5));
            put("link", Integer.valueOf(6));
            put("event", Integer.valueOf(7));
        }
    };
    public static final Map<String, Integer> eventmapping = new HashMap<String, Integer>() {
        /**
         *消息事件类型
         */
        private static final long serialVersionUID = 1L;

        {
            put("subscribe", Integer.valueOf(0));
            put("unsubscribe", Integer.valueOf(1));
            put("SCAN", Integer.valueOf(2));
            put("LOCATION", Integer.valueOf(3));
            put("CLICK", Integer.valueOf(4));
            put("VIEW", Integer.valueOf(5));
        }
    };

    public MsgMap() {
    }
    //获取消息类型
    public static int getMsgType(String key) {
        return ((Integer) (msgmapping.get(key)==null?100:msgmapping.get(key))).intValue();
    }
    //获取消息事件类型
    public static int getEventType(String key) {
        return ((Integer) (eventmapping.get(key)==null?100:eventmapping.get(key))).intValue();
    }
}
