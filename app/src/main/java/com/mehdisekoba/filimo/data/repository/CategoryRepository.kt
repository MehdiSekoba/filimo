package com.mehdisekoba.filimo.data.repository

import com.mehdisekoba.filimo.data.network.ApiServices
import javax.inject.Inject

class CategoryRepository
@Inject
constructor(private val api: ApiServices) {
    suspend fun getCategoryData() = api.getCategoryData()

    suspend fun getCategoryDetailsData(tagid: String) = api.getCategoryDetailsData(tagid)

}
