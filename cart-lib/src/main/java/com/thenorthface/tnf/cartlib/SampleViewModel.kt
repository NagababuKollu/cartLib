package com.thenorthface.tnf.cartlib

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thenorthface.tnf.cartlib.api.Resource
import com.thenorthface.tnf.cartlib.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    sealed class QuotesEvent{
        class Success(val resultText:String):QuotesEvent()
        class Failure(val errorText:String):QuotesEvent()
        object Loading:QuotesEvent()
        object Empty:QuotesEvent()
    }

    private val _conversion = MutableStateFlow<QuotesEvent>(QuotesEvent.Empty)
    val conversion: StateFlow<QuotesEvent> = _conversion

    fun getQuotesViewModel(){
        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = QuotesEvent.Loading
            when(val quotesResponse = repository.getQuotes()) {
                is Resource.Error -> _conversion.value = QuotesEvent.Failure(quotesResponse.message!!)
                is Resource.Success -> {
                    val quote = quotesResponse.data!!.quote
                    _conversion.value = QuotesEvent.Success(
                        "$quote"
                    )
                }
            }
        }
    }
}