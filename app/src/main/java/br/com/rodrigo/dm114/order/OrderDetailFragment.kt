package br.com.rodrigo.dm114.order

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import br.com.rodrigo.dm114.R
import br.com.rodrigo.dm114.databinding.FragmentOrderDetailBinding
import br.com.rodrigo.dm114.persistence.OrderRepository
import br.com.rodrigo.dm114.product.ProductViewModel
import br.com.rodrigo.dm114.product.ProductViewModelFactory


class OrderDetailFragment : Fragment() {

    private var id: String? = null
    private var code: String? = null
    private lateinit var binding : FragmentOrderDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentOrderDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        id = OrderDetailFragmentArgs.fromBundle(arguments!!).id
        code = OrderDetailFragmentArgs.fromBundle(arguments!!).code

        val orderDetailViewModelFactory = OrderDetailViewModelFactory(id)
        binding.orderDetailViewModel = ViewModelProviders.of(
            this, orderDetailViewModelFactory).get(OrderDetailViewModel::class.java)

        val productViewModelFactory = ProductViewModelFactory(code)
        binding.productViewModel= ViewModelProviders.of(
            this, productViewModelFactory).get(ProductViewModel::class.java)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_delete -> {
                binding.orderDetailViewModel?.deleteOrder()
                this.findNavController().navigate(OrderDetailFragmentDirections.actionShowList())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_order_menu, menu)
    }
}