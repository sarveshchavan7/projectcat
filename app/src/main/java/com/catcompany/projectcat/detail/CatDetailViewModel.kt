package com.catcompany.projectcat.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.catcompany.analytics.AnalyticsConstants
import com.catcompany.breedlist.model.CatBreed
import com.catcompany.projectcat.datamanager.DataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(
    private val dataManager: DataManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val breed = savedStateHandle.get<CatBreed>(BREED)

    init {
        trackCatDetailScreenVisitedEvent()
    }

    private fun trackCatDetailScreenVisitedEvent() {
        dataManager.analyticsHelper.trackScreenVisitedEvent(
            AnalyticsConstants.Event.CAT_BREED_DETAIL_SCREEN,
            mapOf(
                AnalyticsConstants.Property.CAT_BREED_ID to breed?.id,
                AnalyticsConstants.Property.CAT_BREED_NAME to breed?.name,
                AnalyticsConstants.Property.CAT_BREED_ORIGIN to breed?.origin
            )
        )
    }

    companion object {
        const val BREED = "breed"
    }
}

