package com.mehdisekoba.filimo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail
import com.mehdisekoba.filimo.data.model.home.ResponseHome
import com.mehdisekoba.filimo.data.repository.DetailRepository
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository):ViewModel(){
    //Detail
    private val _detailData = MutableLiveData<NetworkRequest<ResponseDetail>>()
    val detailData: LiveData<NetworkRequest<ResponseDetail>> = _detailData

    fun callDetailApi(id: Int) = viewModelScope.launch {
        _detailData.value = NetworkRequest.Loading()
        val response = repository.getDetailMovies(id)
        _detailData.value = NetworkResponse(response).generateResponse()
    }
   //similar
   private val _similarDetailData = MutableLiveData<NetworkRequest<ResponseHome>>()
    val similarDetailData: LiveData<NetworkRequest<ResponseHome>> = _similarDetailData

    fun callSimilarDetailApi(id: Int) = viewModelScope.launch {
        _similarDetailData.value = NetworkRequest.Loading()
        val response = repository.getDetailSimilarMovies(id)
        _similarDetailData.value = NetworkResponse(response).generateResponse()
    }
}