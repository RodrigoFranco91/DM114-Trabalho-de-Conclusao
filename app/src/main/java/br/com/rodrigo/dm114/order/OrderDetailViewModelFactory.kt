package br.com.rodrigo.dm114.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OrderDetailViewModelFactory(private val id: String?) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderDetailViewModel::class.java)) {
            return OrderDetailViewModel(id) as T
        }
        throw IllegalArgumentException("The OrderDetailViewModel class is required")
    }
}