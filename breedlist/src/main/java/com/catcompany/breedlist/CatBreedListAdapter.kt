package com.catcompany.breedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.catcompany.breedlist.databinding.ItemBreedLayoutBinding
import com.catcompany.breedlist.model.CatBreed

class CatBreedListAdapter(val link: Link) :
    ListAdapter<CatBreed, CatBreedListAdapter.CatBreedViewHolder>(CatBreed.DiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        return CatBreedViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_breed_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class CatBreedViewHolder(private val binding: ItemBreedLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(breed: CatBreed) {
            binding.breed = breed
            binding.root.setOnClickListener {
                link.onItemClick(breed)
            }
        }
    }

    interface Link {
        fun onItemClick(breed: CatBreed)
    }
}