package com.cniao5.mine.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.cniao5.common.network.support.serverData
import com.cniao5.mine.net.MineService
import com.cniao5.mine.net.UserInfoRsp
import com.cniao5.service.network.onBizError
import com.cniao5.service.network.onBizOK
import com.cniao5.service.network.onFailure
import com.cniao5.service.network.onSuccess

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:17
  * @Description:
 */
class MineRepo(private val service: MineService) : IMineResource {

    private val _userInfoRsp = MutableLiveData<UserInfoRsp?>()

    override val liveUserInfo: LiveData<UserInfoRsp?> = _userInfoRsp

    override suspend fun getUserInfo(token: String?) {
        service.getUserInfo(token)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("获取用户信息 BizError $code,$message")
                    _userInfoRsp.value = null
                }
                onBizOK<UserInfoRsp> { code, data, message ->
                    _userInfoRsp.value = data
                    LogUtils.i("获取用户信息 BizOK $data")
                    return@onBizOK
                }
            }.onFailure {
                _userInfoRsp.value = null
                LogUtils.e("获取用户信息 接口异常 ${it.message}")
            }
    }

}