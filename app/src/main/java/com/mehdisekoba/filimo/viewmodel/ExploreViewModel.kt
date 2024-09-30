package com.mehdisekoba.filimo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehdisekoba.filimo.data.model.explore.ResponseExplore
import com.mehdisekoba.filimo.data.repository.ExploreRepository
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploreViewModel @Inject constructor(private val repository: ExploreRepository) :
    ViewModel() {

    //category
    private val _exploreData = MutableLiveData<NetworkRequest<ResponseExplore>>()
    val exploreData: LiveData<NetworkRequest<ResponseExplore>> = _exploreData

     fun callExploreData() = viewModelScope.launch {
         _exploreData.value = NetworkRequest.Loading()
        val response = repository.getExploreData()
         _exploreData.value = NetworkResponse(response).generateResponse()

    }



}