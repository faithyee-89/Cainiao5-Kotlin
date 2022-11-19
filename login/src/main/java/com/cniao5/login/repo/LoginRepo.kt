package com.cniao5.login.repo

import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.cniao5.common.model.SingleLiveData
import com.cniao5.common.network.support.serverData
import com.cniao5.login.net.LoginReqBody
import com.cniao5.login.net.LoginRsp
import com.cniao5.login.net.LoginService
import com.cniao5.login.net.RegisterRsp
import com.cniao5.service.network.onBizError
import com.cniao5.service.network.onBizOK
import com.cniao5.service.network.onFailure
import com.cniao5.service.network.onSuccess

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:16
  * @Description:
 */
class LoginRepo(private val service: LoginService) : ILoginResource {

    private val _registerRsp = SingleLiveData<RegisterRsp>()
    private val _loginRsp = SingleLiveData<LoginRsp>()

    override val registerRsp: LiveData<RegisterRsp> = _registerRsp
    override val loginRsp: LiveData<LoginRsp> = _loginRsp

    override suspend fun checkRegister(mobi: String) {
        service.isRegister(mobi)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("是否注册 BizError $code,$message")
                    ToastUtils.showShort(message)
                }
                onBizOK<RegisterRsp> { code, data, message ->
                    _registerRsp.value = data
                    LogUtils.i("是否注册 BizOK $data")
                    return@onBizOK
                }
            }.onFailure {
                LogUtils.e("是否注册 接口异常 ${it.message}")
                ToastUtils.showShort(it.message)
            }
    }

    override suspend fun requestLogin(body: LoginReqBody) {
        service.login(body)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("登录接口 BizError $code,$message")
                    ToastUtils.showShort("登录接口 BizError $code,$message")
                }
                onBizOK<LoginRsp> { code, data, message ->
                    _loginRsp.value = data
                    //同步到room数据库，登录状态

                    LogUtils.i("登录接口 BizOK $data")
                }
            }.onFailure {
                ToastUtils.showShort("登录接口 异常 ${it.message}")
                LogUtils.e("登录接口 异常 ${it.message}")
            }
    }

}