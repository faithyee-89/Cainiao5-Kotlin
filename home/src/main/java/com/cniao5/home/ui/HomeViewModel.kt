package com.cniao5.home.ui

import com.cniao5.common.base.BaseViewModel
import com.cniao5.home.repo.IHomeResource

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:12
  * @Description:
 */
class HomeViewModel(private val repo: IHomeResource) : BaseViewModel() {


    val livePageModules = repo.livePageModules

    val liveBanner = repo.liveBanner


    fun getModules() = serverAwait { repo.getModuleList() }


    suspend fun getItems(moduleId: Int) = repo.getModuleItems(moduleId)


    fun getBanner() = serverAwait { repo.getBanner() }


}