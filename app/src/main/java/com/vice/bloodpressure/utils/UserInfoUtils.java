package com.vice.bloodpressure.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.model.UserInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * @类说明 UserInfoUtils
 * @作者 hhsoft
 * @创建日期 2018/12/4 15:05
 */
public class UserInfoUtils {

    /**
     * CLIENT_ID
     *
     * @param context
     * @return
     */
    public static String getClientID(Context context) {
        String clientID = SharedPreferencesUtils.getInfo(context, SharedPreferencesConstant.CLIENT_ID);
        return TextUtils.isEmpty(clientID) ? HHSoftSystemUtils.deviceToken(context) : clientID;
    }


    /**
     * 获取用户ID，默认是0
     */
    public static String getUserID(Context context) {
        String userID = SharedPreferencesUtils.getInfo(context, SharedPreferencesConstant.USER_ID);
        return TextUtils.isEmpty(userID) ? "0" : userID;
    }


    /**
     * 重置信息--用户退出登录，重置某些信息
     */
    public static void resetUserInfo(Context context) {

        Map<String, String> map = new HashMap<>();
        map.put(SharedPreferencesConstant.USER_ID, "0");

        SharedPreferencesUtils.saveInfo(context, map);

    }

    /**
     * 退出登录
     * CLIENTID 不需要清空
     * 单点登录： BaseCallBack、Handler传null
     */
    public static void outlog(final Context context, final CallBack callBack, final Handler handler) {
        if (callBack == null) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancelAll();
            resetUserInfo(context);
            updateDeviceState(context, "0");
        } else {
            resetUserInfo(context);
            updateDeviceState(context, "0");
            if (handler != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.callBack(null);
                    }
                });
            }
        }
    }

    /**
     * 请求验证token
     */
    public static String getAcceToken(Context context) {
        //        String accessToken = SharedPreferencesUtils.getInfo(context, SharedPreferencesConstant.ACCESS_TOKEN);
        //        if (TextUtils.isEmpty(accessToken)) {
        //            accessToken = "";
        //        }
        //        return accessToken;
        return "";
    }

    /**
     * 更新设备状态
     */
    public static void updateDeviceState(Context context, String userID) {
        String clientID = SharedPreferencesUtils.getInfo(context, SharedPreferencesConstant.CLIENT_ID);
        //        Call<String> requestCall = SettingDataManager.updateDeviceState(userID, clientID, (call, response) -> {
        //
        //        }, (call, throwable) -> {
        //
        //        });

    }

    public static String getUserInfo(Context context, String infoKey) {
        return SharedPreferencesUtils.getInfo(context, infoKey);
    }


    /**
     * 获取个人资料
     */

    public static UserInfo getUserInfo(Context context) {
        Map<String, String> map = new HashMap<>();
        //        map.put(SharedPreferencesConstant.LOGIN_NAME, "0");


        Map<String, String> resultMap = SharedPreferencesUtils.getInfo(context, map);

        UserInfo userInfo = new UserInfo();
        //        userInfo.setLoginName(resultMap.get(SharedPreferencesConstant.LOGIN_NAME));

        //待完善
        return userInfo;
    }

    /**
     * 保存个人中心信息
     */
    public static void saveUserInfo(Context context, UserInfo userInfo) {

        Map<String, String> map = new HashMap<>();
        //        map.put(SharedPreferencesConstant.LOGIN_NAME, userInfo.getLoginName());

        SharedPreferencesUtils.saveInfo(context, map);
    }

    /**
     * 保存用户登录信息
     */
    public static void saveLoginInfo(Context context, UserInfo userInfo) {
        Map<String, String> map = new HashMap<>();
        map.put(SharedPreferencesConstant.USER_ID, userInfo.getUserID());

        SharedPreferencesUtils.saveInfo(context, map);
    }


}