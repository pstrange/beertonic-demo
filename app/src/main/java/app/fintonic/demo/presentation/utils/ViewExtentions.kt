package app.fintonic.demo.presentation.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import app.fintonic.demo.R
import com.bumptech.glide.Glide

@BindingAdapter(value = ["imageUrl", "placeHolder", "imageLocal"], requireAll = false)
fun loadImage(view: ImageView, imageUrl: String?, placeHolder: Drawable?, imageLocal: Int) {
    if (imageUrl?.isNotEmpty() == true) {
        view.loadImage(url = imageUrl, placeholder = placeHolder)
    } else {
        view.loadImage(imageLocal)
    }
}

fun ImageView.loadImage(
    url: String,
    placeholder: Drawable? = null
) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.loadImage(@DrawableRes url: Int, placeholder: Int = R.drawable.beer_logo) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}