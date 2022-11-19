package com.cniao5.login

import com.cniao5.common.network.KtRetrofit
import com.cniao5.common.utils.getBaseHost
import com.cniao5.login.net.LoginService
import com.cniao5.login.repo.ILoginResource
import com.cniao5.login.repo.LoginRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:18
  * @Description:
 */
val moduleLogin = module {


    //service retrofit
    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(LoginService::class.java)
    }

    //repo LoginResource

    single { LoginRepo(get()) } bind ILoginResource::class

    //viewModel

    viewModel { LoginViewModel(get()) }

}