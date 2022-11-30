package com.test.task.ui.companyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.task.data.repository.companyList.CompanyListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CompanyListRepository): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val companyList = repository.companyList

    fun isListEmpty(): Boolean {
        return companyList.value?.isEmpty() ?: true
    }

    fun loadCompanyListFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            repository.loadCompanyList()
            _isLoading.postValue(false)
        }
    }
}