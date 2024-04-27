package com.catcompany.base.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.catcompany.base.R

@BindingAdapter("imageUrl", "cornerRadius", requireAll = false)
fun ImageView.loadImage(imageUrl: String?, cornerRadius: Int? = null) {
    if (imageUrl.isNullOrBlank()) return
    val requestManager = Glide.with(this.context).load(imageUrl)
    cornerRadius?.let {
        requestManager
            .transform(RoundedCorners(cornerRadius))
    }
    requestManager
        .placeholder(R.drawable.ic_place_holder)
        .error(R.drawable.ic_baseline_error)
        .into(this)
}