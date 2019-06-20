package com.example.zapis_test.ui.salon_detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ImageSalonViewPager(val images: List<String>, fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val fragment = SalonImageFragment()
        val bundle = Bundle()
        bundle.putString(IMAGE_SALON, images.get(position))
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = images.size

    companion object {
        const val IMAGE_SALON = "IMAGE_SALON"
    }

}