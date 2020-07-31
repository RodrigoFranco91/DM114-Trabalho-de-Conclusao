package br.com.rodrigo.dm114.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.rodrigo.dm114.databinding.FragmentOrderBinding
import br.com.rodrigo.dm114.persistence.Order
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi



class OrderFragment : Fragment() {


    private val orderViewModel: OrderViewModel by lazy {
        ViewModelProviders.of(this).get(OrderViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOrderBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.orderViewModel = orderViewModel

        if (this.arguments != null) {

            if (this.arguments!!.containsKey("orderInfo")) {

                val moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<Order> =
                    moshi.adapter<Order>(Order::class.java)
                jsonAdapter.fromJson(this.arguments!!.getString("orderInfo")!!).let {
                    orderViewModel.order.value = it
                }
            }
        }
        return binding.root
    }
}