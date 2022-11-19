package com.cniao5.login

import com.cniao5.common.BaseApplication
import com.cniao5.service.moduleService
import org.koin.core.context.loadKoinModules

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:22
  * @Description:
 */
class LoginApplication : BaseApplication() {


    override fun initConfig() {
        super.initConfig()
        loadKoinModules(moduleService)
        loadKoinModules(moduleLogin)
    }
}