package com.cniao5.course.utils

import com.cniao5.course.net.CourseListRsp

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:11
  * @Description:
 */
object CourseUtils {

    @JvmStatic
    fun parseStudentComment(info: CourseListRsp.Data?): String {
        return "${info?.lessons_played_time} ${info?.comment_count}人评价"
    }

    @JvmStatic
    fun parseFree(info: CourseListRsp.Data?): String {
        return if (info?.is_free == 1) "免费" else "￥${info?.now_price}"
    }
}