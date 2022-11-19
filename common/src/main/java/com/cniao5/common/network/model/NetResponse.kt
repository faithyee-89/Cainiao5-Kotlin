package com.cniao5.common.network.model

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:06
 * @Description:   基础的网络返回数据结构
 */
data class NetResponse(
    val code: Int,//响应码
    val data: Any?,//响应数据内容
    val message: String//响应数据的结果描述
)