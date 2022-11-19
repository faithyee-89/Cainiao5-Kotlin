package com.cniao5.course.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.blankj.utilcode.util.LogUtils
import com.cniao5.common.network.support.serverData
import com.cniao5.course.net.CourseCategoryRsp
import com.cniao5.course.net.CourseListRsp
import com.cniao5.course.net.CourseService
import com.cniao5.course.repo.data.CoursePagingSource
import com.cniao5.service.network.onBizError
import com.cniao5.service.network.onBizOK
import com.cniao5.service.network.onFailure
import com.cniao5.service.network.onSuccess
import kotlinx.coroutines.flow.Flow

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:10
  * @Description:
 */
class CourseRepo(private val service: CourseService) : ICourseResource {

    private val _courseTypes = MutableLiveData<CourseCategoryRsp>()

    override val liveCourseType: LiveData<CourseCategoryRsp> = _courseTypes

    override suspend fun getCourseTypeList() {
        service.getCourseCategory()
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("获取课程分类 BizError $code,$message")
                }
                onBizOK<CourseCategoryRsp> { code, data, message ->
                    _courseTypes.value = data
                    LogUtils.i("获取课程分类 BizOK $data")
                    return@onBizOK
                }
            }.onFailure {
                LogUtils.e("获取课程分类 接口异常 ${it.message}")
            }
    }

    private val pageSize = 20
    override suspend fun getTypeCourseList(
        course_type: Int,
        code: String,
        difficulty: Int,
        is_free: Int,
        q: Int
    ): Flow<PagingData<CourseListRsp.Data>> {
        val config =
            PagingConfig(
                pageSize = pageSize,
                prefetchDistance = 5,
                initialLoadSize = 10,
                maxSize = pageSize * 3
            )
        return Pager(config = config, null) {
            CoursePagingSource(service, course_type, code, difficulty, is_free, q)
        }.flow
    }


}