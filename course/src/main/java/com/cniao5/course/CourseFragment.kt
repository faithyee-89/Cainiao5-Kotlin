package com.cniao5.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.cniao5.common.base.BaseFragment
import com.cniao5.course.databinding.FragmentCourseBinding
import com.cniao5.course.databinding.PopCourseTypeBinding
import com.cniao5.course.ui.CourseLoadAdapter
import com.cniao5.course.ui.CoursePageAdapter
import com.cniao5.course.ui.CourseViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_course.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:11
 * @Description:   课程中心的Fragment
 */
class CourseFragment : BaseFragment() {

    private val viewModel: CourseViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_course

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentCourseBinding.bind(view).apply {
            vm = viewModel
        }
    }

    private val adapter = CoursePageAdapter()

    override fun initData() {
        super.initData()
        viewModel.getCourseTypeList()
        //获取到课程类型下的数据列表
        viewModel.apply {

            //课程分类
            liveTypes.observeKt { types ->
                tl_category_course.removeAllTabs()
                //添加了全部，所以tab比type个数多1
                tl_category_course.addTab(
                    tl_category_course.newTab().also { tab ->
                        tab.text = "全部"
                    }
                )
                types?.forEach { item ->
                    tl_category_course.addTab(
                        tl_category_course.newTab().also { tab ->
                            tab.text = item.title
                        }
                    )
                }

            }

            tl_category_course.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val types = liveTypes.value ?: return
                    tl_category_course.tabCount
                    //第0个 是 全部
                    val index = tab?.position ?: 0
                    val selCode = if (index > 0) {
                        types.get(index - 1).code ?: "all"
                    } else "all"
                    lifecycleScope.launchWhenCreated {
                        typedCourseList(code = selCode).collectLatest {
                            adapter.submitData(it)
                        }
                    }
                    LogUtils.i("tab个数 ${tl_category_course.tabCount} types size ${types.size}")
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            rv_course.adapter = adapter.withLoadStateFooter(CourseLoadAdapter(adapter))

            adapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading) {
                    pb_fragment_course.visibility = View.VISIBLE
                } else {
                    pb_fragment_course.visibility = View.GONE

                    // getting the error
                    val error = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    error?.let {
                        ToastUtils.showShort(it.error.message)
                    }
                }
            }
        }
        //筛选点击
        popFilter()
    }

    private lateinit var popWindow: PopupWindow
    private fun popFilter() {
        val popBinding =
            PopCourseTypeBinding.inflate(LayoutInflater.from(requireContext()), null, false)
        popWindow = PopupWindow(
            popBinding.root,
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        val obCheckPos = ObservableInt(0)
        popBinding.apply {
            pos = obCheckPos
            tvAllType.setOnClickListener {
                obCheckPos.set(0)
                popWindow.dismiss()
                adapter.refresh()
            }
            tvBizType.setOnClickListener {
                obCheckPos.set(1)
                popWindow.dismiss()
                adapter.refresh()
            }
            tvProType.setOnClickListener {
                obCheckPos.set(2)
                popWindow.dismiss()
                adapter.refresh()
            }
        }
        //点击筛选
        spinner_type_course.setOnClickListener { v ->
            popWindow.showAsDropDown(v)
        }

    }

}