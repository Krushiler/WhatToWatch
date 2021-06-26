package com.example.whattowatch.di

import com.example.whattowatch.NetworkService
import com.example.whattowatch.ui.films.FilmsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { FilmsViewModel(get(), get()) }
}

val networkModule = module {
    single { NetworkService() }
}