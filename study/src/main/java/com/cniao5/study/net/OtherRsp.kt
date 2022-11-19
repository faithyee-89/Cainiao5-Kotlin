package com.cniao5.study.net

import androidx.annotation.Keep


/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:19
  * @Description:
 */

//region 课程相关

//fixme 使用post请求，在拦截器里面做签名运算的时候，这些item都要当作string才行，不要用int，或其他数组类型
@Keep
data class FavoriteReq(
    val course_id: String,//课程id
    val type: String,//1 收藏 2 取消收藏
    val user_id: String = "0"//默认当前用户
)

@Keep
data class FavoriteRsp(
    val message: String?,
    val result: Int
)

class RecommendListRsp : ArrayList<RecommendItem>()

@Keep
data class RecommendItem(
    val brief: String?,
    val comment_count: Int,
    val cost_price: Double,
    val first_category: FirstCategory?,
    val id: Int,
    val img_url: String?,
    val is_distribution: Boolean,
    val is_free: Int,
    val is_live: Int,
    val is_pt: Boolean,
    val lessons_count: Int,
    val lessons_played_time: Int,
    val name: String?,
    val now_price: Double
) {
    @Keep
    data class FirstCategory(
        val code: String?,
        val id: Int,
        val title: String?
    )
}

@Keep
data class CourseDetailInfo(
    val brief: String?,
    val can_buy: Int,
    val can_use_coupon: Int,
    val class_difficulty: Int,
    val comment_count: Int,
    val cost_price: Double,
    val course_type: Int,
    val created_time: String?,
    val desc: String?,//html格式的
    val expiry_day: Int,
    val first_category: FirstCategory?,
    val fit_to: String?,
    val goal: String?,
    val h5site: String?,
    val id: Int,
    val img_url: String?,
    val is_distribution: Boolean,
    val is_free: Int,
    val is_live: Int,
    val is_pt: Boolean,
    val lessons_count: Int,
    val lessons_finished_count: Int,
    val lessons_played_time: Int,
    val lessons_time: Int,
    val name: String?,
    val now_price: Double,
    val pre_tech: String?,
    val qr_img_url: String?,
    val recommend_count: Int,
    val sub_title: String?,
    val teacher: Teacher?,
    val teacher_ids: String?,
    val website: String?
) {
    @Keep
    data class FirstCategory(
        val code: String?,
        val id: Int,
        val title: String?
    )

    @Keep
    data class Teacher(
        val brief: String?,
        val company: String?,
        val id: Int,
        val job_title: String?,
        val logo_url: String?,
        val teacher_name: String?
    )
}

//endregion

//region 讲师相关

@Keep
data class TeacherListRsp(
    val datas: List<Teacher>?,
    val page: Int,
    val size: Int,
    val total: Int,
    val total_page: Int
) {
    @Keep
    data class Teacher(
        val brief: String?,
        val company: String?,
        val id: Int,
        val job_title: String?,
        val logo_url: String?,
        val teacher_name: String?
    )
}

//讲师的课程
class TeacherCourseListRsp : ArrayList<TeacherCourseItem>()

@Keep
data class TeacherCourseItem(
    val brief: String?,
    val comment_count: Int,
    val cost_price: Double,
    val first_category: FirstCategory?,
    val id: Int,
    val img_url: String?,
    val is_distribution: Boolean,
    val is_pt: Boolean,
    val lessons_played_time: Int,
    val name: String?,
    val now_price: Double
) {
    @Keep
    data class FirstCategory(
        val code: String?,
        val id: Int,
        val title: String?
    )
}


@Keep
data class TeacherInfoRsp(
    val brief: String?,
    val company: String?,
    val courses: Int,
    val id: Int,
    val is_follow: Int,
    val job_title: String?,
    val logo_url: String?,
    val students: Int,
    val teacher_name: String?
)
//endregion