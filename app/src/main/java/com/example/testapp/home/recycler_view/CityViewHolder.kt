package com.example.testapp.home.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.ItemCityBinding

class CityViewHolder(
    private val binding: ItemCityBinding,
    private val onClick: (CityModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CityModel) {
        binding.run {
            title.text = item.title
            subtitle.text = item.subtitle

            root.setOnClickListener {
                onClick(item)
            }
        }
    }

}