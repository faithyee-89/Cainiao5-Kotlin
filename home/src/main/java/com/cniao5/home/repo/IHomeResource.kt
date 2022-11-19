package com.cniao5.home.repo

import androidx.lifecycle.LiveData
import com.cniao5.common.network.model.DataResult
import com.cniao5.home.net.BannerListRsp
import com.cniao5.home.net.PageModuleListRsp
import com.cniao5.service.network.BaseCniaoRsp

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:11
  * @Description:
 */
interface IHomeResource {

    val livePageModules: LiveData<PageModuleListRsp>

    val liveBanner: LiveData<BannerListRsp>

    /**
     * 页面的模块列表
     */
    suspend fun getModuleList()

    /**
     * 页面内的模块的item列表
     */
    suspend fun getModuleItems(moduleId: Int): DataResult<BaseCniaoRsp>

    /**
     * 获取banner数据
     */
    suspend fun getBanner()

}