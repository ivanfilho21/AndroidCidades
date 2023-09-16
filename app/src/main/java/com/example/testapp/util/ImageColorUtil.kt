package com.example.testapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.example.testapp.R

object ImageColorUtil {

    /**
     * Retorna a cor predominante da parte de cima da imagem.
     * Caso a imagem não tenha uma cor predominante, preto é retornado.
     */
    fun getDominantTopColorFromImage(
        context: Context?,
        drawable: BitmapDrawable?,
        @ColorRes defaultColor: Int = R.color.black
    ): Int? {
        context?.let { ctx ->
            drawable?.bitmap?.let { bitmap ->
                Palette.Builder(cropBitmap(bitmap)).generate().run {
                    return getDominantColor(ContextCompat.getColor(ctx, defaultColor))
                }
            }
        }
        return null
    }

    /**
     * Retorna a cor predominante da parte de baixo da imagem.
     * Caso a imagem não tenha uma cor predominante, preto é retornado.
     */
    fun getDominantBottomColorFromImage(
        context: Context?,
        drawable: BitmapDrawable?,
        @ColorRes defaultColor: Int = R.color.black
    ): Int? {
        context?.let { ctx ->
            drawable?.bitmap?.let { bitmap ->
                Palette.Builder(cropBitmap(bitmap, false)).generate().run {
                    return getDominantColor(ContextCompat.getColor(ctx, defaultColor))
                }
            }
        }
        return null
    }

    /**
     * Retorna true se a cor passada por parâmetro for escura, caso contrário false.
     */
    fun isDark(color: Int): Boolean {
        return ColorUtils.calculateLuminance(color) < 0.5
    }

    /**
     * Corta o bitmap verticalmente em 3 partes,
     * retornando o terço de cima caso <b>cropTop</b> seja true ou a parte de baixo caso contrário.
     */
    private fun cropBitmap(source: Bitmap, cropTop: Boolean = true): Bitmap {
        val width = source.width
        val height = source.height
        val cropHeight = height / 3

        return Bitmap.createBitmap(
            source,
            0,
            if (cropTop) 0 else height - cropHeight,
            width,
            cropHeight,
            null,
            true
        )
    }

}