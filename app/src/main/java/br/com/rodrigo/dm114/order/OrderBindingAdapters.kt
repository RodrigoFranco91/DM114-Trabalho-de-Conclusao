package br.com.rodrigo.dm114.order


import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigo.dm114.persistence.Order


@BindingAdapter("orderId")
    fun bindOrderId(TxtOrderId: TextView, orderId: Long?) {
        orderId?.let {
            TxtOrderId.text = orderId.toString()
        }
    }

@BindingAdapter("ordersList")
fun bindOrderList(recyclerView: RecyclerView, orders: List<Order>?) {
    val adapter = recyclerView.adapter as OrderAdapter
    adapter.submitList(orders)
}
