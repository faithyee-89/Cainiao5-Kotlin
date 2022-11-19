package com.cniao5.home

import com.cniao5.common.network.KtRetrofit
import com.cniao5.common.utils.getBaseHost
import com.cniao5.home.net.HomeService
import com.cniao5.home.repo.HomeRepo
import com.cniao5.home.repo.IHomeResource
import com.cniao5.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:12
  * @Description:
 */
val moduleHome = module {
    //service retrofit
    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(HomeService::class.java)
    }

    //repo LoginResource

    single { HomeRepo(get()) } bind IHomeResource::class

    viewModel { HomeViewModel(get()) }
}