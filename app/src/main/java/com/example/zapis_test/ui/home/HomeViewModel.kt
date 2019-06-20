package com.example.zapis_test.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.zapis_test.models.Salon
import com.example.zapis_test.repository.salon.SalonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val repository: SalonRepository): ViewModel() {

    private val disposable = CompositeDisposable()

    val liveData by lazy {
        MutableLiveData<HomeData>()
    }

    fun getPopular() {
        disposable.add(
            repository.getPopularSalons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveData.value = HomeData.ShowLoading }
                .doFinally { liveData.value = HomeData.HideLoading }
                .subscribe({
                    liveData.value = HomeData.PoplularResult(it as ArrayList<Salon>)
                }, {
                    liveData.value = HomeData.Error(it.message)
                })
        )
    }

    fun getRecommended() {
        disposable.add(
            repository.getRecommendedSalons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveData.value = HomeData.ShowLoading }
                .doFinally { liveData.value = HomeData.HideLoading }
                .subscribe({
                    liveData.value = HomeData.RecommendedResult(it as ArrayList<Salon>)
                }, {
                    liveData.value = HomeData.Error(it.message)
                })
        )
    }


    sealed class HomeData {
        data class PoplularResult(val salons: ArrayList<Salon>) : HomeData()
        data class RecommendedResult(val salons: ArrayList<Salon>): HomeData()
        data class Error(val message: String?): HomeData()
        object ShowLoading: HomeData()
        object HideLoading: HomeData()

    }

}