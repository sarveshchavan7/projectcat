package com.catcompany.projectcat.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.catcompany.breedlist.model.CatBreed
import com.catcompany.projectcat.R
import com.catcompany.projectcat.databinding.ActivityCatDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatDetailActivity : AppCompatActivity() {

    private val viewModel: CatDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCatDetailBinding>(
            this,
            R.layout.activity_cat_detail
        )
        binding.vm = viewModel
    }

    companion object {
        fun startActivity(context: Context, breed: CatBreed?) {
            return context.startActivity(Intent(context, CatDetailActivity::class.java).apply {
                this.putExtra(CatDetailViewModel.BREED, breed)
            })
        }
    }
}