package br.com.rodrigo.dm114.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rodrigo.dm114.persistence.Order

class OrderViewModel : ViewModel() {

    val order = MutableLiveData<Order>()

}