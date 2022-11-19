package com.cniao5.course.net

import com.cniao5.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:10
  * @Description:
 */
interface CourseService {

    /**
     * 课程分类
     */
    @GET("/course/category")
    fun getCourseCategory(): Call<BaseCniaoRsp>

    /**
     * 课程列表v2,根据code入参，分类python，java，android等类别下的课程列表，，就是课程页面的所有，有入参
     */
    @GET("/course/v2/list")
    fun getCourseList(
        @Query("course_type") course_type: Int = -1,//类型 (-1 全部) (1 普通课程) (2 职业课程/班级课程) (3 实战课程) 默认 -1
        @Query("code") code: String = "all",//方向从课程分类接口获取    默认 all;例如 android,python
        @Query("difficulty") difficulty: Int = -1,//难度 (-1 全部) (1 初级) (2 中级) (3 高级) (4 架构) 默认 -1
        @Query("is_free") is_free: Int = -1,//价格 (-1, 全部) （0 付费） (1 免费) 默认 -1
        @Query("q") q: Int = -1,//排序  (-1 最新) (1 评价最高)  (2 学习最多) 默认 -1
        @Query("page") page: Int = 1,//当前页
        @Query("size") size: Int = 20//大小
    ): Call<BaseCniaoRsp>

}