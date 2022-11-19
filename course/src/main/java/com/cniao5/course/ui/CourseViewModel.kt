package com.cniao5.course.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cniao5.common.base.BaseViewModel
import com.cniao5.course.net.CourseCategoryRsp
import com.cniao5.course.repo.ICourseResource

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:10
 * @Description:
 */
class CourseViewModel(private val repo: ICourseResource) : BaseViewModel() {

    //课程分类
    val liveTypes: LiveData<CourseCategoryRsp> = repo.liveCourseType


    fun getCourseTypeList() = serverAwait {
        repo.getCourseTypeList()
    }

    suspend fun liveTypedCourseList() =
        repo.getTypeCourseList(
            -1, "all", -1, -1, -1
        )
            .asLiveData(viewModelScope.coroutineContext)
            .cachedIn(viewModelScope)

    suspend fun typedCourseList(
        course_type: Int = -1,//类型 (-1 全部) (1 普通课程) (2 职业课程/班级课程) (3 实战课程) 默认 -1
        code: String = "all",//方向从课程分类接口获取    默认 all;例如 android,python
        difficulty: Int = -1,//难度 (-1 全部) (1 初级) (2 中级) (3 高级) (4 架构) 默认 -1
        is_free: Int = -1,//价格 (-1, 全部) （0 付费） (1 免费) 默认 -1
        q: Int = -1,//排序  (-1 最新) (1 评价最高)  (2 学习最多) 默认 -1
    ) = repo.getTypeCourseList(
        course_type, code, difficulty, is_free, q
    )


}