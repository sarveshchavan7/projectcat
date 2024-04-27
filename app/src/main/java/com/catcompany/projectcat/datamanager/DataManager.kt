package com.catcompany.projectcat.datamanager

import com.catcompany.analytics.AnalyticsHelper
import com.catcompany.projectcat.datamanager.network.ApiHelper

class DataManager(
    val analyticsHelper: AnalyticsHelper,
    apiHelper: ApiHelper
) : ApiHelper by apiHelper