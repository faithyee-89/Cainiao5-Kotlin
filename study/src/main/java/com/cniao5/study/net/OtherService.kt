package com.cniao5.study.net

import com.cniao5.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:20
  * @Description:
 */
interface OtherService {

    /**
     * 获取老师列表
     */
    @GET("/teacher/list")
    fun getTeacherList(): Call<BaseCniaoRsp>

    /**
     * 根据teacher的id 获取老师的课程
     */
    @GET("/teacher/courses")
    fun getTeacherCourseList(@Query("id") id: Int): Call<BaseCniaoRsp>

    /**
     * 根据teacher的id 获取老师的基本信息
     */
    @GET("/teacher/detail")
    fun getTeacherInfo(@Query("id") id: Int): Call<BaseCniaoRsp>

    /**
     * 收藏/取消收藏课程
     */
    @POST("/course/favorites")
    fun postFavorites(@Body body: FavoriteReq): Call<BaseCniaoRsp>


    /**
     * 根据course_id 查询相关推荐
     */
    @GET("/course/related/recommend")
    fun getRecommend(@Query("course_id") courseId: Int): Call<BaseCniaoRsp>


    /**
     * 根据course_id 查询课程详情
     */
    @GET("/course/detail")
    fun getCourseInfo(@Query("course_id") courseId: Int): Call<BaseCniaoRsp>

    /**
     * 根据course_id查询对应的课程评论
     */
    @GET("/comment/list")
    fun getCourseCommentList(
        @Query("course_id") courseId: Int,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
    ): Call<BaseCniaoRsp>


}