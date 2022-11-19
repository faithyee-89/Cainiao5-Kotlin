package com.cniao5.home.net

import androidx.annotation.Keep

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:11
  * @Description:
 */

//region 首页 页面配置返回
/**
 * 页面内单独模块的item列表
 */
class ModuleItemRsp : ArrayList<ComponentItem>()

@Keep
data class ComponentItem(
    val apply_deadline_time: String,
    val balance_payment_time: Any?,
    val button_desc: Any?,
    val course: Course,
    val created_time: String,
    val current_price: Double,
    val graduate_time: String,
    val id: Int,
    val is_apply_stop: Int,
    val learning_mode: Int,
    val lessons_count: Any?,
    val number: Int,
    val original_price: Double?,
    val start_class_time: String,
    val status: Int,
    val stop_use_down_payment_time: Any?,
    val student_count: Int,
    val student_limit: Int,
    val study_expiry_day: Int,
    val teacher_ids: String,
    val title: String
) {
    @Keep
    data class Course(
        val h5site: String,
        val id: Int,
        val img_url: String,
        val name: String,
        val website: String
    )
}

/*@Keep
data class PageListRsp(
    val datas: List<Data>?,
    val page: Int,
    val size: Int,
    val total: Int,
    val total_page: Int
) {
    @Keep
    data class Data(
        val name: String?,
        val page_id: Int,
        val platform: String?
    )
}*/


//页面的模块配置list
class PageModuleListRsp : ArrayList<PageModuleItem>()

@Keep
data class PageModuleItem(
    val created_time: String?,
    val data_url: String?,//请求这个模块的url的path，目前固定是/allocation/component/list
    val id: Int,//标记id
    val is_show_more: Int,//是否显示更多的 按钮
    val layout: Int,
    val more_redirect_url: String?,
    val scroll: Int,
    val sub_title: String?,
    val title: String?,//首页的模块名字，比如就业班
    val type: Int//type标记
)
//endregion


//banner
class BannerListRsp : ArrayList<BannerItem>()

@Keep
data class BannerItem(
    val client_url: String?,
    val created_time: String?,
    val id: Int,
    val img_url: String?,//图片地址
    val name: String?,
    val order_num: Int,
    val page_show: Int,
    val redirect_url: String?,//跳转链接
    val state: Int,
    val type: String?
)

/*
* id 1 就业班；2、新上好课；3、限时免费；4、Android精选；5、Ai精选；6、大数据精选；7、10w学员推荐；8、人气讲师；9、黄埔军校
 */

//就业班
class JobClassList() : ArrayList<JobClassList.JobClassListItem>() {
    @Keep
    data class JobClassListItem(
        val apply_deadline_time: String?,
        val course: Course?,
        val created_time: String?,
        val current_price: Double,
        val graduate_time: String?,
        val id: Int,
        val is_apply_stop: Int,
        val learning_mode: Int,
        val lessons_count: Int,
        val lessons_time: Int,
        val number: Int,
        val original_price: Double,
        val start_class_time: String?,
        val status: Int,
        val student_count: Int,
        val student_limit: Int,
        val study_expiry_day: Int,
        val teacher_ids: String?,
        val title: String?
    ) {
        @Keep
        data class Course(
            val h5site: String?,
            val id: Int,
            val img_url: String?,
            val name: String?,
            val website: String?
        )
    }

}

//新上好课
class NewClassList : ArrayList<HomeCourseItem>()
class LimitFreeList : ArrayList<HomeCourseItem>()
class AndroidSelection : ArrayList<HomeCourseItem>()
class AISelection : ArrayList<HomeCourseItem>()
class BDList : ArrayList<HomeCourseItem>()
class Suggest10w : ArrayList<HomeCourseItem>()

@Keep
data class HomeCourseItem(
    val brief: String?,
    val comment_count: Int,
    val cost_price: Double,
    val expiry_day: Int,
    val finished_lessons_count: Int,
    val first_category: FirstCategory?,
    val id: Int,
    val img_url: String?,
    val is_free: Int,
    val is_live: Int,
    val is_pt: Boolean,
    val is_share_card: Boolean,
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


//人气讲师
class PopTeacherList : ArrayList<PopTeacherList.PopTeacherListItem>() {
    @Keep
    data class PopTeacherListItem(
        val brief: String?,
        val company: String?,
        val id: Int,
        val job_title: String?,
        val logo_url: String?,
        val teacher_course: List<TeacherCourse>?,
        val teacher_name: String?
    ) {
        @Keep
        data class TeacherCourse(
            val cost_price: Double,
            val id: Int,
            val img_url: String?,
            val lessons_played_time: Int,
            val comment_count: Int,
            val name: String?,
            val now_price: Double,
            val score: Int
        )
    }
}