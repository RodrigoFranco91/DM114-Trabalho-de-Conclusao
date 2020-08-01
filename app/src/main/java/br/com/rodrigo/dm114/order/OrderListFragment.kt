package br.com.rodrigo.dm114.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.dm114.databinding.FragmentListOrderBinding
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import br.com.rodrigo.dm114.R
import com.google.firebase.analytics.FirebaseAnalytics


class OrderListFragment : Fragment(){

    private val orderListViewModel: OrderListViewModel by lazy {
        ViewModelProviders.of(this).get(OrderListViewModel::class.java)
    }
    private lateinit var binding: FragmentListOrderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentListOrderBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.orderListViewModel = orderListViewModel
        val itemDecor = DividerItemDecoration(getContext(), VERTICAL);

        binding.rcvOrders.addItemDecoration(itemDecor)
        binding.rcvOrders.adapter = OrderAdapter(OrderAdapter.OrderClickListener {
            this.findNavController().navigate(OrderFragmentDirections.actionShowList())
        })
        return binding.root
    }
}

