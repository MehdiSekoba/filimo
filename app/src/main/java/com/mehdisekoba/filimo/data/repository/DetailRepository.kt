package com.mehdisekoba.filimo.data.repository

import com.mehdisekoba.filimo.data.network.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getDetailMovies(id: Int) = api.getDetailMovies(id)
    suspend fun getDetailSimilarMovies(id: Int) = api.getDetailSimilarMovies(id)

}