package com.cniao5.course

import com.cniao5.common.network.KtRetrofit
import com.cniao5.common.utils.getBaseHost
import com.cniao5.course.net.CourseService
import com.cniao5.course.repo.CourseRepo
import com.cniao5.course.repo.ICourseResource
import com.cniao5.course.ui.CourseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
  * @Author:        faithyee
  * @CreateDate:    2022-11-19 18:11
  * @Description:
 */
val moduleCourse = module {

    //service retrofit
    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(CourseService::class.java)
    }

    //repo LoginResource

    single { CourseRepo(get()) } bind ICourseResource::class

    viewModel { CourseViewModel(get()) }
}