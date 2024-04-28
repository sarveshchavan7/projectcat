package com.catcompany.projectcat.breed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.paging.map
import com.catcompany.breedlist.CatBreedListAdapter
import com.catcompany.projectcat.R
import com.catcompany.projectcat.databinding.ActivityCatBreedBinding
import com.catcompany.projectcat.detail.CatDetailActivity
import com.catcompany.base.utils.safeLaunchWithRepeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CatBreedActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: CatBreedViewModel

    @Inject
    lateinit var catBreedListAdapter: CatBreedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCatBreedBinding>(
            this,
            R.layout.activity_cat_breed
        )
        binding.rvCatBreed.adapter = catBreedListAdapter
        safeLaunchWithRepeatOnLifecycle(state = Lifecycle.State.RESUMED) {
            viewModel.catBreedsFlow.collect { it ->
                catBreedListAdapter.submitData(it.map { it.createCatBreed() })
            }
        }
        safeLaunchWithRepeatOnLifecycle(state = Lifecycle.State.RESUMED) {
            val breedId = viewModel.onCatBreedClickEvent.receive()
            CatDetailActivity.startActivity(this@CatBreedActivity, breedId)
        }
    }
}