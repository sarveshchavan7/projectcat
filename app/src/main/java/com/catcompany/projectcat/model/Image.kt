package com.catcompany.projectcat.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(val id: String, val url: String?, val width: Int, val height: Int) : Parcelable