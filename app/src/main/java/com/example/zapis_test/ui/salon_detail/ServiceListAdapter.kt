package com.example.zapis_test.ui.salon_detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zapis_test.R
import com.example.zapis_test.models.Service
import kotlinx.android.synthetic.main.item_service.view.*

class ServiceListAdapter: RecyclerView.Adapter<ServiceListAdapter.ServiceViewHolder>() {

    val listService = ArrayList<Service>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ServiceViewHolder {
        return ServiceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false))
    }

    override fun getItemCount(): Int = listService.size

    override fun onBindViewHolder(viewHolder: ServiceViewHolder, position: Int) {
        viewHolder.bindView(listService.get(position))
    }

    fun initList(list: ArrayList<Service>) {
        listService.clear()
        listService.addAll(list)
        notifyDataSetChanged()
    }

    class ServiceViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindView(item: Service) = with(view) {
            serviceTitle.text = item.name
            time.text = item.duration.toString() + "мин "
            price.text = " " + item.price + "₸"

        }
    }
}