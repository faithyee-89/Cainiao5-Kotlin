package com.cniao5.mine.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cniao5.common.base.BaseFragment
import com.cniao5.mine.R
import com.cniao5.mine.databinding.FragmentUserInfoBinding

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:18
  * @Description:   用户信息界面Fragment
 */
class UserInfoFragment : BaseFragment() {

//    private val args by navArgs<UserInfoFragmentArgs>()

    override fun getLayoutRes() = R.layout.fragment_user_info

    override fun bindView(view: View, savedInstanceState: Bundle?) =
        FragmentUserInfoBinding.bind(view).apply {
            //toolbar返回
            toolbarUserInfo.setNavigationOnClickListener { findNavController().navigateUp() }
//            toolbarUserInfo.setupWithNavController(findNavController())
//            toolbarUserInfo.navigationIcon?.setTint(Color.WHITE)
            // save 返回
            btnSaveUserInfo.setOnClickListener { findNavController().navigateUp() }
            //
//            info = args.info
        }

}