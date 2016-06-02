package com.mphantom.webbridge;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wushaorong on 16-6-2.
 */

public class JsCallJava {
    private static String Tag = "chromBridgeClient";
    private static JsCallJava install;

    private JsCallJava() {

    }

    public static JsCallJava newInstance() {
        if (install == null)
            install = new JsCallJava();
        return install;
    }

    public String callMethod(View view, String param) {
        if (view == null || TextUtils.isEmpty(param))
            return "";
        if (!param.startsWith("hybrid"))
            return "";
        Uri uri = Uri.parse(param);
        String host = uri.getHost();
        if (!TextUtils.equals(host, "JSBridge"))
            return "";
        String methodName = uri.getPath();
        if (TextUtils.isEmpty(methodName))
            return "";
        methodName = methodName.replace("/", "");

        Object[] object = new Object[3];
        object[0] = view;
        object[1] = param;
        object[3] = "monery";
        List<Map<String, Method>> methodList = new ArrayList<>();
        Method method = methodList.get(0).get("");

        try {
            method.invoke(null, object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Log.d("chromBridgeClient", "host=====> " + uri.getHost());
        Log.d("chromBridgeClient", "part=====> " + methodName);
        Log.d("chromBridgeClient", "qure=====> " + uri.getQuery());
        return "";
    }
}
