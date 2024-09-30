package com.mehdisekoba.filimo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehdisekoba.filimo.data.model.category.ResponseCategory
import com.mehdisekoba.filimo.data.model.category.ResponseCategoryDetails
import com.mehdisekoba.filimo.data.model.home.ResponseHome
import com.mehdisekoba.filimo.data.repository.CategoryRepository
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) :
    ViewModel() {


    init {
        viewModelScope.launch {
            delay(200)
            callCategoryApi()
        }
    }

    //category
    private val _categoryData = MutableLiveData<NetworkRequest<ResponseCategory>>()
    val categoryData: LiveData<NetworkRequest<ResponseCategory>> = _categoryData

    private fun callCategoryApi() = viewModelScope.launch {
        _categoryData.value = NetworkRequest.Loading()
        val response = repository.getCategoryData()
        _categoryData.value = NetworkResponse(response).generateResponse()

    }

    //details
    private val _detailsCategoryData = MutableLiveData<NetworkRequest<ResponseCategoryDetails>>()
    val detailsCategoryData: LiveData<NetworkRequest<ResponseCategoryDetails>> =
        _detailsCategoryData

    fun callDetailsCategoryApi(tagId: String) = viewModelScope.launch {
        _detailsCategoryData.value = NetworkRequest.Loading()
        val response = repository.getCategoryDetailsData(tagId)
        _detailsCategoryData.value = NetworkResponse(response).generateResponse()

    }

}