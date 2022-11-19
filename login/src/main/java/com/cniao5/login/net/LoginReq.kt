package com.cniao5.login.net

import androidx.annotation.Keep

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:13
  * @Description:
 */

@Keep
data class LoginReqBody(
    val mobi: String,
    val password: String
)