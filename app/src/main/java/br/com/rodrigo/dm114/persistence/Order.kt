package br.com.rodrigo.dm114.persistence

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Order(
    @Exclude var id: String? = null,
    var userId: String? = null,
    var userName: String? = null,
    var Status: String? = null,
    var productCode: String? = null,
    var data: Date? = null,
    var orderId: Long? = null
)