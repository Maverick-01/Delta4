package com.maverick.deltafour.observer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.deltafour.data.ImageURL
import com.maverick.deltafour.repo.FeedRepository
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val repo = FeedRepository()
    //mutable and livedata variable for each type of image. livedata used for observing changes in data.
    private val _getWaifuObservable = MutableLiveData<ImageURL?>()
    val getWaifuObservable: LiveData<ImageURL?> = _getWaifuObservable

    private val _getNekoObservable = MutableLiveData<ImageURL?>()
    val getNekoObservable: LiveData<ImageURL?> = _getNekoObservable

    private val _getShinobuObservable = MutableLiveData<ImageURL?>()
    val getShinobuObservable: LiveData<ImageURL?> = _getShinobuObservable

    //handling of loading states
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getWaifuImage() {
        _loading.value = true
        viewModelScope.launch {
            repo.fetchWaifuImage().let { response ->
                if (response.isSuccessful){
                    _getWaifuObservable.postValue(response.body())
                }
                _loading.postValue(false)
            }
        }
    }

    fun getNekoImage() {
        _loading.value = true
        viewModelScope.launch {
            repo.fetchNekoImage().let { response ->
                if (response.isSuccessful){
                    _getNekoObservable.postValue(response.body())
                }
                _loading.postValue(false)
            }
        }
    }

    fun getShinobuImage() {
        _loading.postValue(true)
        viewModelScope.launch {
            repo.fetchShinobuImage().let { response ->
                if (response.isSuccessful){
                    _getShinobuObservable.postValue(response.body())
                }
                _loading.postValue(false)
            }
        }
    }
}