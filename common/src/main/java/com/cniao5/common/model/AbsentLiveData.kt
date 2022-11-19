package com.cniao5.common.model

import androidx.lifecycle.LiveData

/**
 * @Author:         faithyee
 * @CreateDate:     2022-11-19 18:04
 * @Description:   创建一个空的liveData的对象类
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T : Any?> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}