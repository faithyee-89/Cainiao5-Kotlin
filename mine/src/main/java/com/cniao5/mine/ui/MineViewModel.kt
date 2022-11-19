package com.cniao5.mine.ui

import com.cniao5.common.base.BaseViewModel
import com.cniao5.mine.repo.IMineResource

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:18
  * @Description:   Mine模块的viewModel
 */
class MineViewModel(private val repo: IMineResource) : BaseViewModel() {

    //用在userInfoFragment中
    val liveInfo = repo.liveUserInfo

    /**
     * 获取用户信息
     */
    fun getUserInfo(token: String?) = serverAwait {
        repo.getUserInfo(token)
    }

}