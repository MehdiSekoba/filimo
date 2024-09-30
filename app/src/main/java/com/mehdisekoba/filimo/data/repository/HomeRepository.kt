package com.mehdisekoba.filimo.data.repository

import com.mehdisekoba.filimo.data.network.ApiServices
import javax.inject.Inject

class HomeRepository
@Inject
constructor(private val api: ApiServices) {
    suspend fun getBannerHomeData() = api.getBannerHomeData()
    suspend fun getLoadByTag(id: Int,queries: Map<String, String>) = api.getLoadByTag(id,queries)
}
