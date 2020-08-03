package br.com.rodrigo.dm114.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rodrigo.dm114.persistence.Order
import br.com.rodrigo.dm114.persistence.OrderRepository

class OrderListViewModel : ViewModel() {

    private lateinit var _orders: MutableLiveData<List<Order>>
    val orders: LiveData<List<Order>>

        get() = _orders

    init {
        getOrders()
    }

    private fun getOrders() {
        _orders = OrderRepository.getOrders()
    }

}