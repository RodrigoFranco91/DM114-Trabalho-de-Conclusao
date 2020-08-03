package br.com.rodrigo.dm114.order

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.rodrigo.dm114.R
import br.com.rodrigo.dm114.databinding.FragmentOrderBinding
import br.com.rodrigo.dm114.persistence.Order
import br.com.rodrigo.dm114.persistence.OrderRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_order_menu, menu)
    }

    private val orderViewModel: OrderViewModel by lazy {
        ViewModelProviders.of(this).get(OrderViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrderBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.orderViewModel = orderViewModel

        if (this.arguments != null) {

            if (this.arguments!!.containsKey("orderInfo")) {

                val moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<Order> =
                    moshi.adapter<Order>(Order::class.java)
                jsonAdapter.fromJson(this.arguments!!.getString("orderInfo")!!).let {
                    orderViewModel.order.value = it
                    OrderRepository.saveOrder(it!!)
                }
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }
}