package com.cniao5.login.net

import com.cniao5.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:13
  * @Description:   登录模块的接口
 */
interface LoginService {

    @GET("accounts/phone/is/register")
    fun isRegister(@Query("mobi") mobi: String): Call<BaseCniaoRsp>

    @POST("accounts/course/10301/login")
    fun login(@Body reqBody: LoginReqBody): Call<BaseCniaoRsp>

}