package com.example.testapp.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.details.DetailsActivity
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.home.recycler_view.CityAdapter
import com.example.testapp.home.recycler_view.CityModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Root é o primeiro elemento de uma tela, nesse caso nem precisa colocar um ID no XML.
        binding.root.adapter = CityAdapter(createList()) {
            startActivity(Intent(this, DetailsActivity::class.java).apply {
                putExtra(DetailsActivity.EXTRA_CITY_DETAILS, it)
            })
        }
    }

    /**
     * Retorna a lista de cidades ordenada em ordem alfabética.
     */
    private fun createList(): List<CityModel> {
        return listOf(
            CityModel("São Luís", "MA", R.drawable.img_slz),
            CityModel("Brasília", "DF", R.drawable.img_brb),
            CityModel("Rio de Janeiro", "RJ", R.drawable.img_rj),
            CityModel("Fortaleza", "CE", R.drawable.img_for),
        ).sortedBy { it.title }
    }
}