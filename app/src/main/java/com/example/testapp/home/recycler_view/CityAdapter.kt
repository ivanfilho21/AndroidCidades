package com.example.testapp.home.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.ItemCityBinding

class CityAdapter(
    private val dataset: List<CityModel>,
    private val onClick: (CityModel) -> Unit
) : RecyclerView.Adapter<CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCityBinding.inflate(layoutInflater, parent, false)
        return CityViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(dataset[position])
    }
}