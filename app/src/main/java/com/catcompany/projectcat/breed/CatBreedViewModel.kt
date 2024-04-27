package com.catcompany.projectcat.breed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.catcompany.analytics.AnalyticsConstants
import com.catcompany.base.apiwrapper.unWrap
import com.catcompany.base.utils.safeLaunch
import com.catcompany.breedlist.CatBreedListAdapter
import com.catcompany.breedlist.model.CatBreed
import com.catcompany.projectcat.datamanager.DataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CatBreedViewModel @Inject constructor(
    application: Application,
    private val dataManager: DataManager
) : AndroidViewModel(application),
    CatBreedListAdapter.Link {

    val catBreedsFlow = MutableStateFlow<List<CatBreed>?>(emptyList())
    val onCatBreedClickEvent = Channel<CatBreed?>(Channel.CONFLATED)

    init {
        loadCatBreeds()
        trackCatBreedScreenVisitedEvent()
    }

    private fun loadCatBreeds() {
        safeLaunch(showToastOnError = true) {
            catBreedsFlow.value = dataManager.getCatBreeds().unWrap().map {
                it.createCatBreed()
            }
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