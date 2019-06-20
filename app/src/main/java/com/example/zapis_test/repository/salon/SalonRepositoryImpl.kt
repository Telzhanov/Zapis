package com.example.zapis_test.repository.salon

import com.example.zapis_test.api.ZapisApi
import com.example.zapis_test.models.Salon
import com.example.zapis_test.models.SalonDetail
import com.example.zapis_test.repository.salon.SalonRepository
import io.reactivex.Single

class SalonRepositoryImpl(val apiService: ZapisApi): SalonRepository {

    override fun getPopularSalons(): Single<List<Salon>> {
        return apiService.getPopularSalons()
            .map {
                it.salons
            }

    }

    override fun getRecommendedSalons(): Single<List<Salon>> {
        return apiService.getRecommendedSalons()
            .map {
                it.salons
            }
    }

    override fun getDetailSalon(id: Int): Single<SalonDetail> {
        return apiService.getSalon(id)

    }

}