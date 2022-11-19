package com.cniao5.study.repo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.blankj.utilcode.util.LogUtils
import com.cniao5.common.network.support.serverData
import com.cniao5.service.network.onBizError
import com.cniao5.service.network.onBizOK
import com.cniao5.service.network.onFailure
import com.cniao5.service.network.onSuccess
import com.cniao5.study.net.BoughtRsp
import com.cniao5.study.net.StudiedRsp
import com.cniao5.study.net.StudyService

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:20
  * @Description:   学习中心相关分页数据的pagingSource
 */
class StudiedItemPagingSource(private val service: StudyService) :
    PagingSource<Int, StudiedRsp.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StudiedRsp.Data> {

        var result: LoadResult<Int, StudiedRsp.Data> =
            LoadResult.Error(Exception("加载中...."))

        val pageNum = params.key ?: 1
        service.getStudyList(page = pageNum, size = params.loadSize)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("获取学习过的课程列表 BizError $code,$message")
                    result = LoadResult.Error(Exception(message))
                }
                onBizOK<StudiedRsp> { code, data, message ->
                    LogUtils.i("获取学习过的课程列表 BizOK $data")
                    val totalPage = data?.total_page ?: 0
                    result = LoadResult.Page(
                        data = data?.datas ?: emptyList(),
                        prevKey = if (pageNum == 1) null else pageNum - 1,//初始化的时候要为null，避免第一页重复加载
                        nextKey = if (pageNum < totalPage) pageNum + 1 else null
                    )
                }
            }.onFailure {
                LogUtils.e("获取学习过的课程列表 接口异常 ${it.message}")
                result = LoadResult.Error(it)
            }
        return result
    }

    override fun getRefreshKey(state: PagingState<Int, StudiedRsp.Data>): Int? {
        TODO("Not yet implemented")
    }

}

/**
 * 已购买课程的分页数据
 */
class BoughtItemPagingSource(private val service: StudyService) :
    PagingSource<Int, BoughtRsp.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BoughtRsp.Data> {

        var result: LoadResult<Int, BoughtRsp.Data> =
            LoadResult.Error(Exception("加载中...."))

        val pageNum = params.key ?: 1
        service.getBoughtCourse(page = pageNum, size = params.loadSize)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("获取购买的课程 BizError $code,$message")
                    result = LoadResult.Error(Exception(message))
                }
                onBizOK<BoughtRsp> { code, data, message ->
                    LogUtils.i("获取购买的课程 BizOK $data")
                    val totalPage = data?.total_page ?: 0
                    result = LoadResult.Page(
                        data = data?.datas ?: emptyList(),
                        prevKey = null,//初始化的时候要为null，避免第一页重复加载
                        nextKey = if (pageNum < totalPage) pageNum.plus(1) else null
                    )
                }
            }.onFailure {
                LogUtils.e("获取购买的课程 接口异常 ${it.message}")
                result = LoadResult.Error(it)
            }
        return result
    }

    override fun getRefreshKey(state: PagingState<Int, BoughtRsp.Data>): Int? {
        TODO("Not yet implemented")
    }

}