package com.catcompany.projectcat.breed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.catcompany.analytics.AnalyticsConstants
import com.catcompany.base.apiwrapper.unWrap
import com.catcompany.base.utils.safeLaunch
import com.catcompany.breedlist.CatBreedListAdapter
import com.catcompany.breedlist.model.CatBreed
import com.catcompany.projectcat.breed.pagingsource.CatBreedPagingSource
import com.catcompany.projectcat.datamanager.DataManager
import com.catcompany.projectcat.model.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@HiltViewModel
class CatBreedViewModel @Inject constructor(
    application: Application,
    private val dataManager: DataManager
) : AndroidViewModel(application),
    CatBreedListAdapter.Link {

    val catBreedsFlow =
        Pager(PagingConfig(10)) { catBreedPagingSource }.flow.cachedIn(viewModelScope)
    val onCatBreedClickEvent = Channel<CatBreed?>(Channel.CONFLATED)
    private val catBreedPagingSource = CatBreedPagingSource(::getCatBreedList)

    init {
        trackCatBreedScreenVisitedEvent()
    }

    private suspend fun getCatBreedList(pageNumber: Int): List<Breed> {
        return try {
            dataManager.getCatBreeds(pageNumber, 10).unWrap()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun onItemClick(breed: CatBreed) {
        safeLaunch {
            onCatBreedClickEvent.send(breed)
        }
        trackBreedClickEvent(breed)
    }

    private fun trackBreedClickEvent(breed: CatBreed) {
        dataManager.analyticsHelper.trackClickEvent(
            AnalyticsConstants.Event.CAT_BREED_CLICK_EVENT,
            mapOf(
                AnalyticsConstants.Property.CAT_BREED_ID to breed.id,
                AnalyticsConstants.Property.CAT_BREED_NAME to breed.name,
                AnalyticsConstants.Property.CAT_BREED_ORIGIN to breed.origin
            )
        )
    }

    private fun trackCatBreedScreenVisitedEvent() {
        dataManager.analyticsHelper.trackScreenVisitedEvent(
            AnalyticsConstants.Event.CAT_BREED_LIST_SCREEN,
            emptyMap()
        )
    }
}