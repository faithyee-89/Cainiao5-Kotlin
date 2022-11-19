package com.cniao5.mine

import android.os.Bundle
import android.view.View
import com.cniao5.common.base.BaseFragment
import com.cniao5.mine.databinding.FragmentContainerMineBinding

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:18
  * @Description:   Mine模块的主Fragment，用于内部navigation的容器
 */
class MineContainerFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_container_mine

    override fun bindView(view: View, savedInstanceState: Bundle?) =
        FragmentContainerMineBinding.bind(view)

}