package com.cniao5.study.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cniao5.common.base.BaseViewModel
import com.cniao5.service.repo.CniaoUserInfo
import com.cniao5.study.net.StudyInfoRsp
import com.cniao5.study.repo.IStudyResource

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:20
 * @Description:
 */
class StudyViewModel(private val repo: IStudyResource) : BaseViewModel() {

    //学习页面的数据
    val liveStudyInfo: LiveData<StudyInfoRsp?> = repo.liveStudyInfo

    //用户基本信息，头像和名字
    val obUserInfo = ObservableField<CniaoUserInfo>()

    //    val adapter=StudiedAdapter()

    fun getStudyData() = serverAwait {
        repo.getStudyInfo()
    }

    suspend fun studiedList() =
        repo.getStudyList()
            .asLiveData(viewModelScope.coroutineContext)
            .cachedIn(viewModelScope)

    suspend fun boughtList() = repo.getBoughtCourse()
        .asLiveData()
        .cachedIn(viewModelScope)
}