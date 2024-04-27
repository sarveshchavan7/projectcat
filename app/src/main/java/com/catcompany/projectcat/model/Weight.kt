package com.catcompany.projectcat.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weight(
    @SerializedName("imperial") var imperial: String? = null,
    @SerializedName("metric") var metric: String? = null
) : Parcelable