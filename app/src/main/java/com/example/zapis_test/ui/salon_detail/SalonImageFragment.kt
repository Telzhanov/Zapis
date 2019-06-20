package com.example.zapis_test.ui.salon_detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.zapis_test.R
import com.example.zapis_test.ui.salon_detail.ImageSalonViewPager.Companion.IMAGE_SALON
import kotlinx.android.synthetic.main.fragment_salon_image.*

class SalonImageFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_salon_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageStr = arguments?.getString(IMAGE_SALON)
        Glide
            .with(this)
            .load("http://zp.jgroup.kz" + imageStr)
            .into(image)
    }
}