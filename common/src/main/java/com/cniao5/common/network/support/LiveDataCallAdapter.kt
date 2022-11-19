/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cniao5.common.network.support


import androidx.lifecycle.LiveData
import com.cniao5.common.network.model.ApiResponse
import com.cniao5.common.network.model.UNKNOWN_ERROR_CODE
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean


/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:07
 * @Description:   用于将retrofit的call回调数据转化为liveData的adapter
 */
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<ApiResponse<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(
                                ApiResponse.create(
                                    response
                                )
                            )
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(
                                ApiResponse.create(
                                    UNKNOWN_ERROR_CODE,
                                    throwable
                                )
                            )
                        }
                    })
                }
            }
        }
    }
}
