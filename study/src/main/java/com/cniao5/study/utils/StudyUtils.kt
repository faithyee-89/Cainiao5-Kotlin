package com.cniao5.study.utils

import androidx.databinding.BindingAdapter
import com.cniao5.study.net.StudiedRsp
import com.daimajia.numberprogressbar.NumberProgressBar

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:20
 * @Description:
 */
object StudyUtils {

    @JvmStatic
    fun rankStr(rank: Int): String {
        return if (rank > 0) "第${rank}名" else "千里之外"
    }


    @JvmStatic
    fun parseStudentComment(info: StudiedRsp.Data?): String {
        return "${info?.lessons_played_time} ${info?.comment_count}人评价"
    }

    @JvmStatic
    fun parseFree(info: StudiedRsp.Data?): String {
        return if (info?.is_free == 1) "免费" else "￥${info?.now_price}"
    }

}

@BindingAdapter("app:progress_current", requireAll = false)
fun setProgress(pb: NumberProgressBar, progress: Double?) {
    pb.progress = ((progress ?: 0.0) * 100).toInt()
}