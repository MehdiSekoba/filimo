package com.mehdisekoba.filimo.data.repository

import com.mehdisekoba.filimo.data.network.ApiServices
import javax.inject.Inject

class ExploreRepository
@Inject
constructor(private val api: ApiServices) {
    suspend fun getExploreData() = api.getExploreData()


}
