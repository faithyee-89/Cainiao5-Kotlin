package com.cniao5.study

import com.cniao5.common.network.KtRetrofit
import com.cniao5.common.utils.getBaseHost
import com.cniao5.study.net.StudyService
import com.cniao5.study.repo.IStudyResource
import com.cniao5.study.repo.StudyRepo
import com.cniao5.study.ui.StudyViewModel
import com.cniao5.study.ui.play.ClassPlayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @Author:        faithyee
 * @CreateDate:    2022-11-19 18:20
 * @Description:
 */
val moduleStudy = module {

    //service retrofit
    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(StudyService::class.java)
    }

    //repo LoginResource

    single { StudyRepo(get<StudyService>()) } bind IStudyResource::class

    viewModel { StudyViewModel(get()) }

    viewModel { ClassPlayViewModel(get()) }
}