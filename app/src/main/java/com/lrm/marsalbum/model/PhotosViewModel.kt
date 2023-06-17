package com.lrm.marsalbum.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lrm.marsalbum.network.MarsApi
import kotlinx.coroutines.launch

enum class MarsApiStatus {LOADING, ERROR, DONE}

class PhotosViewModel: ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus> = _status

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    val photos: LiveData<List<MarsPhoto>> = _photos

    private val _listSize = MutableLiveData<String>()
    val listSize: LiveData<String> = _listSize

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        _listSize.value = ""

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _listSize.value = "${_photos.value!!.size}"
                _status.value = MarsApiStatus.DONE
            }
            catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
                _listSize.value = ""
            }
        }
    }
}