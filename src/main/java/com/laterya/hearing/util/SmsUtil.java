package com.laterya.hearing.util;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class SmsUtil {
    /**
     * @param mobile 手机号
     * @param code   发送的验证码
     */
    public static void sendMessage(String mobile, String code) {
        HttpResponse response = null;
        String host = "https://gyytz.market.alicloudapi.com/sms/smsSend";
        String appcode = "7556657c08864bc3ba8b246fb52c1880";
        Map<String, Object> body = new HashMap<>();
        body.put("mobile", mobile);
        body.put("param", "**code**:" + code + ",**minute**:" + 3);
        body.put("smsSignId", "e4afb0d081d146edb350089a65e7d2c6");
        body.put("templateId", "908e94ccf08b4476ba6c876d13f084ad");
        try {
            response = HttpUtil.createPost(host)
                    .header("Authorization", "APPCODE " + appcode)
                    .form(body)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
}
