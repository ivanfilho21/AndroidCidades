package com.example.testapp.details

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.testapp.util.ImageColorUtil
import com.example.testapp.R
import com.example.testapp.databinding.ActivityDetailsBinding
import com.example.testapp.home.recycler_view.CityModel

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CITY_DETAILS = "test.app.extra_city_details"
    }

    private val binding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    private var cityDetails: CityModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Recebe o objeto passado via extra do Intent
        cityDetails = intent?.extras?.getParcelable(EXTRA_CITY_DETAILS)

        // Configura a action bar e seu título
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "${cityDetails?.title}, ${cityDetails?.subtitle} - Brasil"

        // Configura os dados da tela
        initLayout()
    }

    private fun initLayout() {
        binding.run {
            val context = root.context

            // Coloca a imagem
            cityDetails?.imageId?.let { imgResId ->
                coverImage.setImageResource(imgResId)
            }

            ImageColorUtil.getDominantTopColorFromImage(
                context,
                coverImage.drawable as? BitmapDrawable
            )?.let { topColor ->
                // Altera a cor da StatusBar
                window.run {
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    statusBarColor = topColor
                }

                // Altera a cor da ActionBar
                collapsingToolbarLayout.setContentScrimColor(topColor)
            }

            ImageColorUtil.getDominantBottomColorFromImage(
                context,
                coverImage.drawable as? BitmapDrawable
            )?.let { bgColor ->
                // Define a cor do fundo da tela
                root.setBackgroundColor(bgColor)

                // A cor do texto muda conforme a cor do fundo da tela
                val textColor = if (ImageColorUtil.isDark(bgColor)) R.color.white else R.color.black
                description.setTextColor(
                    ContextCompat.getColor(context, textColor)
                )

                // Define o gradiente que vai fazer a transição da imagem para o fundo da tela
                overlay.background = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(0, 0, bgColor)
                )
            }
        }
    }

}