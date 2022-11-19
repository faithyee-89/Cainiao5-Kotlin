package com.cniao5.mine.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:17
  * @Description:   mine模块的用户信息的返回数据，api的 data的解析后的数据类型
 */

@Keep
@Parcelize
data class UserInfoRsp(
    val addr: String?,
    val alipay: String?,
    val channel: String?,
    val city: String?,
    var company: String?,
    val confirmed: Boolean,
    val desc: String?,
    val device: String?,
    val education: String?,
    val email: String?,
    val focus_it: String?,
    val follower_count: Int,
    val following_count: Int,
    val form_id: Long,
    val form_id_time: String?,
    val id: Long,
    val invite_code: String?,
    val inviter_id: Long,
    val is_teacher: Boolean,
    val job: String?,
    val label: String?,
    val login_time: String?,
    val logo_url: String?,
    val major: String?,
    val mobi: String?,
    val province: String?,
    val qq: String?,
    val question_collect_qa: List<String>?,
    val real_name: String?,
    val reg_app: String?,
    val reg_time: String?,
    val school: String?,
    val unique_code: String?,
    val username: String?,
    val wechat_id: String?,
    val work_years: String?
) : Parcelable