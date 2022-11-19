package com.cniao5.service.assistant

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:18
  * @Description:   配置doKit的工具类
 */
object AssistantApp {

    fun initConfig(application: Application) {
        DoraemonKit.install(
            application, mutableListOf(
                ServerHostKit()
            )
        )
    }

}