package com.cniao5.course.net

import androidx.annotation.Keep

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:10
  * @Description:
 */

//课程分类
class CourseCategoryRsp : ArrayList<CourseTypeItem>()

@Keep
data class CourseTypeItem(
    val code: String?,//课程类型，用于分类列表获取，
    val id: Int,//类型id
    val title: String?//类型title
)


//课程列表 ，用于课程中心
@Keep
data class CourseListRsp(
    val datas: List<Data>?,
    val page: Int,
    val size: Int,
    val total: Int,
    val total_page: Int
) {
    /**
     * "brief": "这是课程简介", //  简介
    "comment_count": 0, //  精选评论数量
    "cost_price": 1000, //  原价
    "expiry_day": 3650000, //  课程学习有效期（天）
    "finished_lessons_count": 0, // 已更新的课时数
    //  一级分类
    "first_category": {
    "code": "java",
    "id": 8,
    "title": "Java"
    },
    "id": 10251, //  课程ID
    "img_url": "https://img.cniao5.com/FrYffHQKU6PGgjUgvmwUKMP25IgW", //  封面
    "is_free": 0, // 是否是免费课程
    "is_live": 0, //  是否是直播课程
    "is_pt": false, // 是否参加了拼团活动
    "is_share_card": false, // 是否加入了学习邀请卡活动
    "lessons_count": 100, // 总课时
    "lessons_played_time": 0, // 学习人数
    "name": "这是一个测试课程",// 名字
    "now_price": 40// 当前价格
     */
    @Keep
    data class Data(
        val brief: String?,
        val comment_count: Int,
        val cost_price: Double,
        val expiry_day: Int,
        val finished_lessons_count: Int,
        val first_category: FirstCategory?,
        val id: Int,
        val img_url: String?,
        val is_free: Int,//1 是 0 否
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
}
