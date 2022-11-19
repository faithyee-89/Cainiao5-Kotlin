package com.cniao5.study

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.cniao5.common.base.BaseFragment
import com.cniao5.service.repo.CniaoDbHelper
import com.cniao5.study.databinding.FragmentStudyBinding
import com.cniao5.study.ui.StudyLoadStateAdapter
import com.cniao5.study.ui.StudyPageAdapter
import com.cniao5.study.ui.StudyViewModel
import com.cniao5.study.ui.play.ClassPlayActivity
import kotlinx.android.synthetic.main.fragment_study.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:20
 * @Description:
 */
class StudyFragment : BaseFragment() {

    private val viewModel: StudyViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_study

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStudyBinding.bind(view).apply {
            vm = viewModel
            adapter = studyPageAdapter
        }
    }

    private val studyPageAdapter = StudyPageAdapter { info ->
        ClassPlayActivity.openPlay(requireContext(), info)
    }

    override fun initData() {
        super.initData()
        //观察数据库中的userInfo
        CniaoDbHelper.getLiveUserInfo(requireContext())
            .observeKt {
                viewModel.obUserInfo.set(it)
                viewModel.getStudyData()

                lifecycleScope.launchWhenCreated {
                    viewModel.studiedList().observeKt { data ->
                        studyPageAdapter.submitData(lifecycle, data ?: PagingData.empty())
                    }
                }

            }
        //获取到最近学习的数据列表
        viewModel.apply {
            studyPageAdapter.withLoadStateFooter(footer = StudyLoadStateAdapter())
            studyPageAdapter.addLoadStateListener { state ->
                when (state.refresh) {
                    is LoadState.NotLoading -> {
                        tv_error_study.visibility = View.GONE
                        pb_study.visibility = View.GONE
                    }
                    is LoadState.Loading -> {
                        pb_study.visibility = View.VISIBLE
                        tv_error_study.visibility = View.GONE
                    }
                    is LoadState.Error -> {
                        pb_study.visibility = View.GONE
                        tv_error_study.visibility = View.VISIBLE
                        lifecycleScope.launch {
                            studyPageAdapter.submitData(PagingData.empty())
                        }
                    }
                }
            }
        }
    }
}