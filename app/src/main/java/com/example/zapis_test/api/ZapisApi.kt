package com.example.zapis_test.api

import com.example.zapis_test.models.Salon
import com.example.zapis_test.models.SalonDetail
import com.example.zapis_test.models.SalonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ZapisApi {

    @GET("salon/getPopular")
    fun getPopularSalons(): Single<SalonResponse>

    @GET("salon/getRecommended")
    fun getRecommendedSalons(): Single<SalonResponse>

    @GET("salon/page")
    fun getSalon(@Query("id") id: Int): Single<SalonDetail>

}