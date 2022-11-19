package com.cniao5.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.cniao5.common.base.BaseFragment
import com.cniao5.common.network.model.DataResult
import com.cniao5.home.databinding.FragmentHomeBinding
import com.cniao5.home.net.*
import com.cniao5.home.ui.BannerAdapter
import com.cniao5.home.ui.HomeAdapter
import com.cniao5.home.ui.HomeItem
import com.cniao5.home.ui.HomeViewModel
import com.cniao5.service.network.*
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:12
  * @Description:
 */
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val mBanners = mutableListOf<BannerItem>()
    private val bannerAdapter by lazy { BannerAdapter(mBanners) }


    private val homeAdapter = HomeAdapter()
    private val homeList = mutableListOf<HomeItem>()


    override fun getLayoutRes() = R.layout.fragment_home

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentHomeBinding.bind(view).apply {
            vm = viewModel
            adapter = homeAdapter
        }
    }

    override fun initConfig() {
        super.initConfig()
        banner_home.addBannerLifecycleObserver(viewLifecycleOwner) //添加生命周期观察者
            .setAdapter(bannerAdapter).indicator = CircleIndicator(requireContext())
    }

    override fun initData() {
        super.initData()
        viewModel.apply {
            getBanner()
            //首页页面模块
            getModules()

            isLoading.observe(viewLifecycleOwner) {
                loading_home.visibility = if (it) View.VISIBLE else View.GONE
            }

            //liveData
            liveBanner.observeKt { banners ->
                banners ?: return@observeKt
                mBanners.clear()
                mBanners.addAll(banners)
                bannerAdapter.notifyDataSetChanged()
            }

            //module
            val scope = CoroutineScope(SupervisorJob())
            //观察 页面模块的配置数据
            livePageModules.observeKt { modules ->
                //modules，配置模块
                lifecycleScope.launchWhenCreated {
                    modules?.map { module ->
                        module.id to
                                //启动根据模块id获取模块的内容列表
                                scope.async { getItems(module.id) }
                    }?.asFlow()?.collect { pair ->
                        parseResult(pair.first, pair.second.await())
                    }
                    homeAdapter.updateList(homeList)
                }

            }
        }
    }


    private fun parseResult(typeId: Int, data: DataResult<BaseCniaoRsp>) {
        data.onSuccess {
            when (typeId) {
                TYPE_JOB_CLASS -> {
                    onBizOK<JobClassList> { code, data, message ->
                        homeList.add(HomeItem(typeId, "就业班", data))
                    }
                }
                TYPE_NEW_CLASS -> {
                    onBizOK<NewClassList> { code, data, message ->
                        homeList.add(HomeItem(typeId, "新上好课", data))
                    }
                }
                TYPE_LIMIT_FREE -> {
                    onBizOK<LimitFreeList> { code, data, message ->
                        homeList.add(HomeItem(typeId, "限时免费", data))
                    }
                }
                TYPE_ANDROID -> {
                    onBizOK<AndroidSelection> { code, data, message ->
                        homeList.add(HomeItem(typeId, "Android精选", data))
                    }
                }
                TYPE_AI -> {
                    onBizOK<AISelection> { code, data, message ->
                        homeList.add(HomeItem(typeId, "AI精选", data))
                    }
                }
                TYPE_BD -> {
                    onBizOK<BDList> { code, data, message ->
                        homeList.add(HomeItem(typeId, "大数据精选", data))
                    }
                }
                TYPE_10W -> {
                    onBizOK<Suggest10w> { code, data, message ->
                        homeList.add(HomeItem(typeId, "10w学员推荐", data))
                    }
                }
                TYPE_TEACHER -> {
                    onBizOK<PopTeacherList> { code, data, message ->
                        homeList.add(HomeItem(typeId, "人气讲师", data))
                    }
                }
                else -> {
                }
            }

            onBizError { code, message ->
                LogUtils.e("模块数据 bizError $code $message")

            }
        }.onFailure {
            LogUtils.e("模块数据 Failure")
        }
    }

    companion object {

        internal const val TYPE_JOB_CLASS = 1
        internal const val TYPE_NEW_CLASS = 2
        internal const val TYPE_LIMIT_FREE = 3
        internal const val TYPE_ANDROID = 4
        internal const val TYPE_AI = 5
        internal const val TYPE_BD = 6
        internal const val TYPE_10W = 7
        internal const val TYPE_TEACHER = 8

    }

}