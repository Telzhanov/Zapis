package com.example.zapis_test.repository.salon

import com.example.zapis_test.models.Salon
import com.example.zapis_test.models.SalonDetail
import io.reactivex.Single

interface SalonRepository {

    fun getPopularSalons(): Single<List<Salon>>

    fun getRecommendedSalons(): Single<List<Salon>>

    fun getDetailSalon(id: Int): Single<SalonDetail>

}