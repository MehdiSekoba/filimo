package com.mehdisekoba.filimo.data.network

import com.mehdisekoba.filimo.data.model.category.ResponseCategory
import com.mehdisekoba.filimo.data.model.category.ResponseCategoryDetails
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail
import com.mehdisekoba.filimo.data.model.explore.ResponseExplore
import com.mehdisekoba.filimo.data.model.home.ResponseHome
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface ApiServices {

    @GET("")
    suspend fun getBannerHomeData(): Response<ResponseHome>

    @GET("")
    suspend fun getLoadByTag(
        @Path("tag_id") id: Int,
        @QueryMap queries: Map<String, String>
    ): Response<ResponseHome>


    @GET("")
    suspend fun getCategoryData(): Response<ResponseCategory>

    @GET("")
    suspend fun getCategoryDetailsData(@Path("tagid") tagId: String): Response<ResponseCategoryDetails>

    @GET("")
    suspend fun getDetailMovies(@Path("id") id: Int): Response<ResponseDetail>

    @GET("")
    suspend fun getExploreData(): Response<ResponseExplore>

    @GET("")
    suspend fun getDetailSimilarMovies(@Path("id") id: Int): Response<ResponseHome>
}
