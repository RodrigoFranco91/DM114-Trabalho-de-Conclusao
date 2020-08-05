package br.com.rodrigo.dm114.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import br.com.rodrigo.dm114.persistence.Order
import br.com.rodrigo.dm114.persistence.OrderRepository

class OrderDetailViewModel(private val id: String?) : ViewModel(){

    lateinit var order: MutableLiveData<Order>

    init {
        if (id != null) {
            getProduct(id)
        } else {
            order = MutableLiveData<Order>()
            order.value = Order()
        }
    }

    private fun getProduct(id: String) {
        order = OrderRepository.getProductById(id)
    }


}