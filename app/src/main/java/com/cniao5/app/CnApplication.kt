package com.cniao5.app

import com.alibaba.android.arouter.launcher.ARouter
import com.cniao5.common.BaseApplication
import com.cniao5.common.ktx.application
import com.cniao5.course.moduleCourse
import com.cniao5.home.moduleHome
import com.cniao5.login.moduleLogin
import com.cniao5.mine.moduleMine
import com.cniao5.service.assistant.AssistantApp
import com.cniao5.service.moduleService
import com.cniao5.study.moduleStudy
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

/**
 * @Description:    Application的声明类
 * @Author:         faithyee
 * @CreateDate:     2022-11-19 17:59
 */
class CnApplication : BaseApplication() {

    private val modules = arrayListOf<Module>(
        moduleService,
        moduleHome,
        moduleLogin,
        moduleMine,
        moduleStudy,
        moduleCourse
    )

    override fun initConfig() {
        super.initConfig()
        //添加common 模块之外的其他模块，组件的koin的modules
        loadKoinModules(modules)
        AssistantApp.initConfig(application)
        ARouter.init(application)
    }

}