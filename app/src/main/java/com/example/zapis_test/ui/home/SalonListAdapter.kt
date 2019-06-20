package com.example.zapis_test.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.zapis_test.R
import com.example.zapis_test.models.Salon
import kotlinx.android.synthetic.main.item_salon.view.*

class SalonListAdapter(val listener: SalonAdapterListener): RecyclerView.Adapter<SalonListAdapter.SalonViewHolder>() {

    var salons = ArrayList<Salon>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SalonViewHolder {
        return SalonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_salon, parent,false))
    }

    override fun getItemCount(): Int =  salons.size

    fun initSalons(list: ArrayList<Salon>) {
        salons.clear()
        salons.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: SalonViewHolder, position: Int) {
        viewHolder.bindView(salons.get(position))
        viewHolder.itemView.setOnClickListener {
            listener.onClick(salons.get(position))
        }
    }


    class SalonViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindView(item: Salon) = with(view) {
            typeSalon.text = item.type
            nameSalon.text = item.name
            Glide
                .with(context)
                .load("http://zp.jgroup.kz" + item.pictureUrl)
                .into(imageSalon)
        }

    }
}

interface SalonAdapterListener {
    fun onClick(item: Salon)
}