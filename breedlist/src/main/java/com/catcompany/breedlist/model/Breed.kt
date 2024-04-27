package com.catcompany.breedlist.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatBreed(
    val id: String?,
    val name: String?,
    val description: String?,
    val imageUrl: String?,
    val origin: String?
) : Parcelable {
    object DiffUtils : DiffUtil.ItemCallback<CatBreed>() {
        override fun areItemsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean =
            oldItem.description == newItem.description &&
                    oldItem.name == newItem.name &&
                    oldItem.imageUrl == newItem.imageUrl

    }
}