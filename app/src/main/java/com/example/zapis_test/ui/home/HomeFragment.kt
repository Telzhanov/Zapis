package com.example.zapis_test.ui.home

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zapis_test.R
import com.example.zapis_test.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import android.support.design.widget.AppBarLayout
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import com.example.zapis_test.models.Salon
import com.example.zapis_test.ui.salon_detail.DetailActivity
import org.koin.android.ext.android.inject


class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by inject()
    private lateinit var popularListAdapter: SalonListAdapter
    private lateinit var recommendedListAdapter: SalonListAdapter
    private var popularSalons: ArrayList<Salon>? = null
    private var recommendedSalons: ArrayList<Salon>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container ,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            bindView()
            setAdapter()
            setData()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(POPULAR, popularSalons)
        outState.putSerializable(RECOMMENDED, recommendedSalons)
        super.onSaveInstanceState(outState)
    }


    private fun setData() {
        viewModel.getPopular()
        viewModel.getRecommended()
        viewModel.liveData.observe(this, Observer {
            when(it) {
                is HomeViewModel.HomeData.HideLoading -> {

                }
                is HomeViewModel.HomeData.ShowLoading -> {

                }
                is HomeViewModel.HomeData.Error -> {

                }
                is HomeViewModel.HomeData.PoplularResult -> {
                    popularSalons = it.salons
                    popularListAdapter.initSalons(it.salons)
                }
                is HomeViewModel.HomeData.RecommendedResult -> {
                    recommendedSalons = it.salons
                    recommendedListAdapter.initSalons(it.salons)
                }
            }
        })
    }

    private fun setAdapter() {
        val listener = object: SalonAdapterListener {
            override fun onClick(item: Salon) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(SALON_DETAIL, item)
                context?.startActivity(intent)
            }

        }
        recommendedListAdapter = SalonListAdapter(listener)
        recommendedList.adapter = recommendedListAdapter
        recommendedList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        popularListAdapter = SalonListAdapter(listener)
        popularList.adapter = popularListAdapter
        popularList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun bindView() {
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                collapsingToolbar.contentScrim = ResourcesCompat.getDrawable(resources,R.drawable.bg_collapsing_white,null)

            } else {
                collapsingToolbar.contentScrim = ResourcesCompat.getDrawable(resources,R.drawable.bg_collapsing_transparent,null)
            }
        })
    }

    companion object  {
        const val POPULAR = "POPULAR"
        const val RECOMMENDED = "RECOMMENDED"
        const val SALON_DETAIL = "SALON_DETAIL"
    }


}