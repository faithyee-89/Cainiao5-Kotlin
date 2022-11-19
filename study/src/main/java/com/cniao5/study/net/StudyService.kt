package com.cniao5.study.net

import com.cniao5.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:20
  * @Description:
 */
interface StudyService {


    /**
     * 用户学习详情
     */
    @GET("/member/study/info")
    fun getStudyInfo(): Call<BaseCniaoRsp>


    /**
     * 用户学习过的课程列表
     */
    @GET("/member/courses/studied")
    fun getStudyList(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Call<BaseCniaoRsp>


    /**
     * 用户购买的课程
     */
    @GET("/member/courses/bought")
    fun getBoughtCourse(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Call<BaseCniaoRsp>


    /**
     * 根据course_id 查询当前学员是否有课程，班级的权限
     */
    @GET("/course/authority")
    fun getCoursePermission(@Query("course_id") courseId: Int): Call<BaseCniaoRsp>


    /**
     * 根据course_id 查询课程章节
     */
    @GET("/course/chapter")
    fun getCourseChapter(@Query("course_id") courseId: Int): Call<BaseCniaoRsp>


    /**
     * 根据课程key，获取播放地址
     */
    @GET("/lesson/play/v2")
    fun getCoursePlayUrl(@Query("key") key: String): Call<BaseCniaoRsp>


}