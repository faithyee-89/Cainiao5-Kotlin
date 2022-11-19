package com.cniao5.course.repo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.blankj.utilcode.util.LogUtils
import com.cniao5.common.network.support.serverData
import com.cniao5.course.net.CourseListRsp
import com.cniao5.course.net.CourseService
import com.cniao5.service.network.onBizError
import com.cniao5.service.network.onBizOK
import com.cniao5.service.network.onFailure
import com.cniao5.service.network.onSuccess

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:10
  * @Description:
 */
class CoursePagingSource(
    private val service: CourseService,
    private val course_type: Int,//类型 (-1 全部) (1 普通课程) (2 职业课程/班级课程) (3 实战课程) 默认 -1
    private val code: String,//方向从课程分类接口获取    默认 all;例如 android,python
    private val difficulty: Int,//难度 (-1 全部) (1 初级) (2 中级) (3 高级) (4 架构) 默认 -1
    private val is_free: Int,//价格 (-1, 全部) （0 付费） (1 免费) 默认 -1
    private val q: Int,//排序  (-1 最新) (1 评价最高)  (2 学习最多) 默认 -1
) : PagingSource<Int, CourseListRsp.Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CourseListRsp.Data> {

        var result: LoadResult<Int, CourseListRsp.Data> =
            LoadResult.Error(Exception("加载中...."))

        val pageNum = params.key ?: 1
        service.getCourseList(course_type, code, difficulty, is_free, q, pageNum, params.loadSize)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("获取type类型的课程列表 BizError $code,$message")
                    result = LoadResult.Error(Exception(message))
                }
                onBizOK<CourseListRsp> { code, data, message ->
                    LogUtils.i("获取type类型的课程列表 BizOK $data")
                    val totalPage = data?.total_page ?: 0
                    result = LoadResult.Page(
                        data = data?.datas ?: emptyList(),
                        prevKey = if (pageNum == 1) null else pageNum - 1,//初始化的时候要为null，避免第一页重复加载
                        nextKey = if (pageNum < totalPage) pageNum + 1 else null
                    )
                }
            }.onFailure {
                LogUtils.e("获取type类型的课程列表 接口异常 ${it.message}")
                result = LoadResult.Error(it)
            }
        return result
    }

    override fun getRefreshKey(state: PagingState<Int, CourseListRsp.Data>): Int? {
        TODO("Not yet implemented")
    }
}