package br.com.rodrigo.dm114.order

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


class OrderBindingAdapters {

    @BindingAdapter("orderId")
    fun bindProductPrice(TxtOrderId: TextView, orderId: Long?) {
        orderId?.let {

            TxtOrderId.text = orderId.toString()
        }
    }
}