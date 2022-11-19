package com.cniao5.study.ui.play

import com.cniao5.common.base.BaseViewModel
import com.cniao5.study.repo.IStudyResource

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:20
 * @Description:
 */
class ClassPlayViewModel(private val repo: IStudyResource) : BaseViewModel() {

    val liveCoursePermission = repo.livePermissionResult
    val liveChapterList = repo.liveChapterList
    val livePlayInfo = repo.livePlayCourse


    fun checkPermission(courseId: Int) = serverAwait { repo.hasPermission(courseId) }

    fun getChapters(courseId: Int) = serverAwait { repo.getChapters(courseId) }

    fun getPlayInfo(key: String) = serverAwait { repo.getPlayInfo(key) }

}