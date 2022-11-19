package com.cniao5.service

import com.cniao5.common.network.KtRetrofit
import org.koin.dsl.module

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:19
  * @Description:   Service模块相关的koin的module配置
 */

val moduleService = module {

    single<KtRetrofit> { (host: String) -> KtRetrofit.initConfig(host) }

}