package com.zsj.websocketredis.redis.action;


import com.alibaba.fastjson.JSONObject;
import com.zsj.websocketredis.common.WebSocketManager;

/**
 * do nothing action
 * @author xiongshiyan at 2018/10/12 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class NoActionAction implements Action{
    @Override
    public void doMessage(WebSocketManager manager, JSONObject object) {
        // do no thing
    }
}
