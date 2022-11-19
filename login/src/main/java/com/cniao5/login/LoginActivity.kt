package com.cniao5.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.cniao5.common.base.BaseActivity
import com.cniao5.common.ktx.context
import com.cniao5.common.network.config.SP_KEY_USER_TOKEN
import com.cniao5.common.utils.CniaoSpUtils
import com.cniao5.login.databinding.ActivityLoginBinding
import com.cniao5.login.net.RegisterRsp
import com.cniao5.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:17
  * @Description:
 */
@Route(path = "/login/login")
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModel()

    override fun getLayoutRes() = R.layout.activity_login

    override fun initView() {
        super.initView()
        mBinding.apply {
            vm = viewModel
            //点击事件
            mtoolbarLogin.setNavigationOnClickListener { finish() }
            tvRegisterLogin.setOnClickListener {
                ToastUtils.showShort("当前课程项目未实现注册账号功能!")
            }
        }
    }

    override fun initConfig() {
        super.initConfig()
        viewModel.apply {

            liveRegisterRsp.observeKt {
                if (it?.is_register == RegisterRsp.FLAG_IS_REGISTERED) {
                    repoLogin()
                }
            }
            liveLoginRsp.observeKt { rsp ->
//                ToastUtils.showShort("登录结果 " + rsp.toString())
                rsp?.let {
                    CniaoDbHelper.insertUserInfo(context, rsp)
                    CniaoSpUtils.put(SP_KEY_USER_TOKEN, rsp.token)
                }
                //关闭activity
                finish()
            }
        }
    }

}