package com.example.zapis_test.ui.salon_detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.zapis_test.models.SalonDetail
import com.example.zapis_test.repository.salon.SalonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val repository: SalonRepository) : ViewModel() {

    val disposables = CompositeDisposable()
    val liveData by lazy {
        MutableLiveData<DetailSalonData>()
    }

    fun getDetailSalon(id: Int) {
        disposables.add(
        repository.getDetailSalon(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.value = DetailSalonData.ShowLoading }
            .doFinally { liveData.value = DetailSalonData.HideLoading }
            .subscribe({
                liveData.value = DetailSalonData.SalonResult(it)
            }, {
                liveData.value = DetailSalonData.Error(it.message)
            }))

    }


    sealed class DetailSalonData {
        object ShowLoading: DetailSalonData()
        object HideLoading: DetailSalonData()
        data class SalonResult(val result: SalonDetail): DetailSalonData()
        data class Error(val message: String?): DetailSalonData()
    }

}