package com.catcompany.projectcat.breed.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.catcompany.breedlist.CatBreedListAdapter
import com.catcompany.projectcat.breed.CatBreedActivity
import com.catcompany.projectcat.breed.CatBreedViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class CatBreedModule {

    // Hilt doesn't require ViewModel to be provided explicitly unless required as a dependency
    @Provides
    fun provideViewModel(@ActivityContext context: Context): CatBreedViewModel {
        return ViewModelProvider(context as CatBreedActivity)[CatBreedViewModel::class.java]
    }

    @Provides
    fun provideLink(viewModel: CatBreedViewModel): CatBreedListAdapter {
        return CatBreedListAdapter(viewModel)
    }
}