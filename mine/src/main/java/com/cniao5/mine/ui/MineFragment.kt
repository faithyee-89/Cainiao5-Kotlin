package com.cniao5.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.alibaba.android.arouter.launcher.ARouter
import com.cniao5.common.base.BaseFragment
import com.cniao5.common.network.config.SP_KEY_USER_TOKEN
import com.cniao5.common.utils.CniaoSpUtils
import com.cniao5.common.webview.WebActivity
import com.cniao5.mine.R
import com.cniao5.mine.databinding.FragmentMineBinding
import com.cniao5.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:18
  * @Description:
 */
class MineFragment : BaseFragment() {

    private val viewModel: MineViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view).apply {
            vm = viewModel
            //UI操作 登出
            btnLogoutMine.setOnClickListener {
                CniaoDbHelper.deleteUserInfo(requireContext())
                CniaoSpUtils.remove(SP_KEY_USER_TOKEN)
                ARouter.getInstance().build("/login/login").navigation()
            }


            //跳转userInfoFragment
            ivUserIconMine.setOnClickListener {
                val info = viewModel.liveInfo.value
                if (info == null) {
                    ARouter.getInstance().build("/login/login").navigation()
                } else {
                    info.company = "自由职业者"
//                    val action = MineFragmentDirections
//                        .actionMineFragmentToUserInfoFragment(info)
//                    findNavController().navigate(action)
                }
            }

            tvUserNameMine.setOnClickListener { ivUserIconMine.callOnClick() }

            tvOrdersMine.setOnClickListener {
                WebActivity.openUrl(requireContext(), "https://m.cniao5.com/user/order")
            }
            tvCouponMine.setOnClickListener {
                WebActivity.openUrl(requireContext(), "https://m.cniao5.com/user/coupon")
            }
            isvStudyCardMine.setOnClickListener {
                WebActivity.openUrl(requireContext(), "https://m.cniao5.com/sharecard")
            }
            isvShareSaleMine.setOnClickListener {
                WebActivity.openUrl(requireContext(), "https://m.cniao5.com/distribution")
            }
            isvGroupShoppingMine.setOnClickListener {
                WebActivity.openUrl(requireContext(), "https://m.cniao5.com/user/pintuan")
            }
            isvLikedCourseMine.setOnClickListener {
                WebActivity.openUrl(requireContext(), "https://m.cniao5.com/user/favorites")
            }
            isvFeedbackMine.setOnClickListener {
                WebActivity.openUrl(requireContext(), "https://cniao555.mikecrm.com/ktbB0ht")
            }
        }
    }


    override fun initData() {
        super.initData()
        CniaoDbHelper.getLiveUserInfo(requireContext()).observeKt {
            viewModel.getUserInfo(it?.token)
        }
    }
}