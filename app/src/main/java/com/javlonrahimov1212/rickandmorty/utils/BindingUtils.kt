package com.javlonrahimov1212.rickandmorty.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.javlonrahimov1212.rickandmorty.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.load(url)
    }
}

@BindingAdapter("bind:status", "bind:species")
fun myText(view: TextView, status: String?, species: String?) {
    if (!status.isNullOrEmpty() and !species.isNullOrEmpty()) {
        view.text = "$status - $species"
    }
}

@BindingAdapter("statusDrawable")
fun setStatusDrawable(view: TextView, status: String?) {
    if (status != null)
        when (status.lowercase()) {
            "alive" -> {
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_round_check_circle,
                    0,
                    0,
                    0
                )
            }
            "dead" -> {
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_round_cancel,
                    0,
                    0,
                    0
                )
            }
            "unknown" -> {
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_round_info,
                    0,
                    0,
                    0
                )
            }
        }
}