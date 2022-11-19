package com.cniao5.home.utils

import com.cniao5.home.net.HomeCourseItem
import com.cniao5.home.net.PopTeacherList


/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:12
  * @Description:
 */
object HomeUtils {

    @JvmStatic
    fun parseStudentComment(info: HomeCourseItem?): String {
        return "${info?.lessons_played_time} ${info?.comment_count}人评价"
    }

    @JvmStatic
    fun parseFree(info: HomeCourseItem?): String {
        return if (info?.is_free == 1) "免费" else "￥${info?.now_price}"
    }


    @JvmStatic
    fun safeListUrl(info: PopTeacherList.PopTeacherListItem?): String {
        return if (info?.teacher_course.isNullOrEmpty()) "" else info?.teacher_course?.get(0)?.img_url
            ?: ""
    }

    @JvmStatic
    fun safeListTitle(info: PopTeacherList.PopTeacherListItem?): String {
        return if (info?.teacher_course.isNullOrEmpty()) "" else info?.teacher_course?.get(0)?.name
            ?: ""
    }

    @JvmStatic
    fun safeListComment(info: PopTeacherList.PopTeacherListItem?): String {
        return if (info?.teacher_course.isNullOrEmpty()) "" else "${info?.teacher_course?.get(0)?.lessons_played_time} ${
            info?.teacher_course?.get(
                0
            )?.comment_count
        }人评价"
    }

    @JvmStatic
    fun safeListPrice(info: PopTeacherList.PopTeacherListItem?): String {
        return if (info?.teacher_course.isNullOrEmpty()) "" else "${info?.teacher_course?.get(0)?.now_price}"
    }


}