package br.com.rodrigo.dm114.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.rodrigo.dm114.databinding.OrderBinding
import androidx.recyclerview.widget.RecyclerView.VERTICAL

private const val TAG = "ProductsListFragment"
class OrderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = OrderBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val itemDecor = DividerItemDecoration(getContext(), VERTICAL);
        return binding.root
    }
}