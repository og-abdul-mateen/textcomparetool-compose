package com.chickenpiecestudio.textcomparetool.koinDi

import com.chickenpiecestudio.textcomparetool.viewmodels.DiffViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // MyViewModel ViewModel
    viewModel { DiffViewModel() }
}