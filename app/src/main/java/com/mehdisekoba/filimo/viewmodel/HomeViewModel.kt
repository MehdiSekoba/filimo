package com.mehdisekoba.filimo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mehdisekoba.filimo.data.model.home.ResponseHome
import com.mehdisekoba.filimo.data.repository.HomeRepository
import com.mehdisekoba.filimo.utils.CATEGORY_SPECIAL
import com.mehdisekoba.filimo.utils.MOVIES_TAG
import com.mehdisekoba.filimo.utils.MoviesCategories
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
     //call api
    init {
        viewModelScope.launch {
            delay(200)
            callMoviesHomeApi()
        }
    }
    //header
    private val _moviesHomeData = MutableLiveData<NetworkRequest<ResponseHome>>()
    val moviesHomeData: LiveData<NetworkRequest<ResponseHome>> = _moviesHomeData

   private fun callMoviesHomeApi() = viewModelScope.launch {
        _moviesHomeData.value = NetworkRequest.Loading()
        val response = repository.getBannerHomeData()
        _moviesHomeData.value = NetworkResponse(response).generateResponse()

    }


    private fun moviesQueries(): Map<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[MOVIES_TAG] = CATEGORY_SPECIAL.toString()
        return queries
    }
    private fun callMoviesApi(category: MoviesCategories) = liveData {
        val cats = category.item
        emit(NetworkRequest.Loading())
        val response = repository.getLoadByTag(cats, moviesQueries())
        emit(NetworkResponse(response).generateResponse())
    }

    private val categoriesName = MoviesCategories.entries
        .associateWith {
            callMoviesApi(it)
        }

    fun getMoviesData(category: MoviesCategories) = categoriesName.getValue(category)

}