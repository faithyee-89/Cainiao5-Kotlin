package com.cniao5.common.webview

import android.webkit.JavascriptInterface
import com.blankj.utilcode.util.LogUtils
import com.cniao5.common.network.config.SP_KEY_USER_TOKEN
import com.cniao5.common.utils.CniaoSpUtils


/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:09
  * @Description:
 */
object JsAndroidApi {

    const val JS_CALL_APP_KEY = "cniaoApp"

    @JavascriptInterface
    fun getAppToken(): String {
        LogUtils.w("JsAndroidApi 中 js调用了getToken")
        return CniaoSpUtils.getString(SP_KEY_USER_TOKEN) ?: ""
    }
}