package com.study.crawling.ui.grid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.crawling.data.DataRepositorySource
import com.study.crawling.data.model.ImageCrawling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(private val dataRepository: DataRepositorySource): ViewModel() {

    private var _crawlingImageData = MutableLiveData<ArrayList<ImageCrawling>>()
    val crawlingImageData: LiveData<ArrayList<ImageCrawling>> get() = _crawlingImageData

    //ProgressBar Binding
    val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            postValue(false)
        }
    }
    
    //requestCrawlingImage 실패시 Toast Message 를 보여주기
    private var _toastMessage = MutableLiveData<Boolean>()
    val toastMessage: LiveData<Boolean> get() = _toastMessage


    // Jsoup 이용한 크롤링 데이터를 넣어준다
    // Flow 결과를 수집
    // 최대 페이지는 100
    fun getCrawlingImages(page : Int) {
        viewModelScope.launch {
            isLoading.postValue(true)

            dataRepository.requestCrawlingImage(page).collect {

                if(it.last().Error == 1) {
                    _toastMessage.value = true
                }
                else
                    _crawlingImageData.value = it
            }
            isLoading.postValue(false)
        }
    }
}