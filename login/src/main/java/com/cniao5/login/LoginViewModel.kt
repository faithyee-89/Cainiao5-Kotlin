package com.cniao5.login

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.ToastUtils
import com.cniao5.common.base.BaseViewModel
import com.cniao5.login.net.LoginReqBody
import com.cniao5.login.repo.ILoginResource

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:17
  * @Description:   登录界面逻辑的viewModel
 */
class LoginViewModel(private val resource: ILoginResource) : BaseViewModel() {

    //账号，密码 的observable 对象
    val obMobile = ObservableField<String>()
    val obPassword = ObservableField<String>()

    val liveRegisterRsp = resource.registerRsp
    val liveLoginRsp = resource.loginRsp

    /**
     * 检查是否注册的账号
     */
    private fun checkRegister(mobi: String) = serverAwait {
        resource.checkRegister(mobi)
    }

    /**
     * 调用登录
     * val mobi: String = "18648957777",
     * val password: String = "cn5123456"
     */
    internal fun repoLogin() {
        val account = obMobile.get() ?: return
        val password = obPassword.get() ?: return
        serverAwait {
            resource.requestLogin(LoginReqBody(account, password))
        }
    }


    /**
     * 调用登录，两步，1，判断手机号是否已经注册
     * 2，已经注册的，才去调用登录
     */
    fun goLogin() {
        val account = obMobile.get() ?: return
        checkRegister(account)
    }

    fun wechat(ctx: Context) {
        ToastUtils.showShort("点击了微信登录")
    }

    fun qq(v: View) {
        ToastUtils.showShort("点击了QQ登录")
    }

    fun weibo() {
        ToastUtils.showShort("点击了微博登录")
    }

    fun AA(view: View) {
        ToastUtils.showShort("静态点击方式")
    }

}