package br.com.rodrigo.dm114.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rodrigo.dm114.network.Product
import br.com.rodrigo.dm114.network.SalesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductViewModel(private val code: String?) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    init {
        getProduct()
    }

    private fun getProduct() {
        coroutineScope.launch {
            var getProductDeferred = SalesApi.retrofitService.getProductByCode(code!!)
            try {
                var productByCode = getProductDeferred.await()
                _product.value = productByCode
            } catch (e: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}