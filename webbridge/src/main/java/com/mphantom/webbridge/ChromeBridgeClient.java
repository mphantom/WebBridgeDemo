package com.mphantom.webbridge;

import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by wushaorong on 16-6-2.
 */

public class ChromeBridgeClient extends WebChromeClient {
    private JsCallJava mCallJava;
    private static String Tag = "chromBridgeClient";

    public ChromeBridgeClient() {
        mCallJava = JsCallJava.newInstance();
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Log.d(Tag, message);
        result.confirm(mCallJava.callMethod(view, message));
        return super.onJsPrompt(view, url, message, defaultValue, result);
//        return true;
    }
}
