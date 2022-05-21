package com.ermolaev_arkhip.redditapp.di

import com.ermolaev_arkhip.redditapp.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val value = module {
        viewModel {
            MainViewModel(repository = get())
        }
    }
}