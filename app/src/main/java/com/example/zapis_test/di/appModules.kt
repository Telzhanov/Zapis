package com.example.zapis_test.di

import android.util.Log
import com.example.zapis_test.BuildConfig
import com.example.zapis_test.api.ZapisApi
import com.example.zapis_test.repository.salon.SalonRepository
import com.example.zapis_test.repository.salon.SalonRepositoryImpl
import com.example.zapis_test.ui.home.HomeViewModel
import com.example.zapis_test.ui.salon_detail.DetailViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {

    single { createHttpClient()}
    single { createApiService(get())}
    single { SalonRepositoryImpl(get()) as SalonRepository }

}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

fun createHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("OkHttp", message) })
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        okHttpClient
            .addInterceptor(interceptor)
    }
    return okHttpClient.build()
}



fun createApiService(okHttpClient: OkHttpClient):ZapisApi {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.KAIRAT_API)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ZapisApi::class.java)
}


val appModules = listOf(networkModule, viewModelModule)