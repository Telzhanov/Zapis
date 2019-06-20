package com.example.zapis_test.ui.salon_detail

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.zapis_test.R
import com.example.zapis_test.models.Salon
import com.example.zapis_test.models.Service
import com.example.zapis_test.ui.home.HomeFragment.Companion.SALON_DETAIL
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private val viewModel:DetailViewModel by inject()
    lateinit var serviceListAdapter: ServiceListAdapter
    lateinit var imageViewPager: ImageSalonViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val salon = intent.getSerializableExtra(SALON_DETAIL) as Salon
        setData(salon)
        bindView()
    }

    private fun bindView() {
        serviceListAdapter = ServiceListAdapter()
        serviceList.adapter = serviceListAdapter
        serviceList.layoutManager = LinearLayoutManager(this)
        backBtnCollapsed.setOnClickListener {
            finish()
        }
    }

    private fun setImages(list: ArrayList<String>) {
        imageViewPager = ImageSalonViewPager(list, supportFragmentManager)
        images.adapter = imageViewPager
        worm_dots_indicator.setViewPager(images)
    }


    private fun setData(salon: Salon) {
        viewModel.getDetailSalon(salon.id)
        viewModel.liveData.observe(this, Observer {
            when(it) {
                is DetailViewModel.DetailSalonData.SalonResult -> {
                    serviceListAdapter.initList(it.result.services as ArrayList<Service>)
                    setImages(it.result.salon.pictures as ArrayList<String>)
                    nameSalon.text = it.result.salon.name
                    typeSalon.text = it.result.salon.type
                    address.text = it.result.salon.address
                }
                is DetailViewModel.DetailSalonData.Error -> {

                }
                is DetailViewModel.DetailSalonData.HideLoading -> {

                }
                is DetailViewModel.DetailSalonData.ShowLoading -> {

                }
            }
        })
    }

}
