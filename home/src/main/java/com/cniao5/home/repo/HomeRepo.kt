package com.cniao5.home.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.cniao5.common.network.model.DataResult
import com.cniao5.common.network.support.serverData
import com.cniao5.home.net.BannerListRsp
import com.cniao5.home.net.HomeService
import com.cniao5.home.net.PageModuleListRsp
import com.cniao5.service.network.*

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:11
  * @Description:
 */
class HomeRepo(private val service: HomeService) : IHomeResource {

    private val _liveModules = MutableLiveData<PageModuleListRsp>()

    private val _liveBanner = MutableLiveData<BannerListRsp>()


    override val livePageModules: LiveData<PageModuleListRsp> = _liveModules

    override val liveBanner: LiveData<BannerListRsp> = _liveBanner

    override suspend fun getModuleList() {
        service.getPageModuleList()
            .serverData()
            .onSuccess {
                onBizOK<PageModuleListRsp> { code, data, message ->
                    _liveModules.value = data
                    LogUtils.i("首页的配置 onBizeOK $data")
                }

                onBizError { code, message ->
                    LogUtils.e("首页的配置onBizError $message $code")
                }
            }
            .onFailure {
                LogUtils.e("首页的配置onFailure ${it.message}")
            }
    }

    override suspend fun getModuleItems(moduleId: Int): DataResult<BaseCniaoRsp> {
        return service.getModuleItems(moduleId).serverData()
    }

    override suspend fun getBanner() {
        service.getBannerList()
            .serverData()
            .onSuccess {
                onBizOK<BannerListRsp> { code, data, message ->
                    _liveBanner.value = data
                    LogUtils.i("首页banner onBizOK $data")
                }

                onBizError { code, message ->
                    LogUtils.e("首页banner onBizError $message $code")
                }
            }
            .onFailure {
                LogUtils.e("首页banner onFailure ${it.message}")
            }
    }

}