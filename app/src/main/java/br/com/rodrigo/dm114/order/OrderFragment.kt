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
import br.com.rodrigo.dm114.databinding.FragmentOrderBinding
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import br.com.rodrigo.dm114.R

private const val TAG = "ProductsListFragment"
class OrderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOrderBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val itemDecor = DividerItemDecoration(getContext(), VERTICAL);
        return binding.root
    }

    //inflater.inflate(R.layout.fragment_order, container, false )
}