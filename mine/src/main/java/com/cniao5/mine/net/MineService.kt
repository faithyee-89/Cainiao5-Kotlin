package com.cniao5.mine.net

import com.cniao5.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:17
  * @Description:   Mine模块相关的网络接口
 */
interface MineService {

    /**
     * 用户详情信息的获取
     */
    @GET("/member/userinfo")
    fun getUserInfo(@Header("token") token: String?): Call<BaseCniaoRsp>


}