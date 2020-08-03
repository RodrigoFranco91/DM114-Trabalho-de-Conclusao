package br.com.rodrigo.dm114.persistence

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

private const val TAG = "OrderRepository"
private const val COLLECTION = "orders"
private const val FIELD_USER_NAME = "userName"
private const val FIELD_ID = "id"
private const val FIELD_USER_ID = "userId"
private const val FIELD_STATUS = "status"
private const val FIELD_PRODUCT_CODE = "productCode"
private const val FIELD_DATA = "data"
private const val FIELD_ORDER_ID = "orderId"

object  OrderRepository {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firebaseFirestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveOrder(order: Order): String {
        val document = if (order.id != null) {
            firebaseFirestore.collection(COLLECTION).document(order.id!!)
        } else {
            order.userId = firebaseAuth.currentUser!!.uid
            firebaseFirestore.collection(COLLECTION).document()
        }

        val currentDateTime = now()
        val dataEmTexto = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        order.data = dataEmTexto
        order.id = document.id
        document.set(order)
        return document.id
    }

    fun deleteOrder(orderId: String) {
        val document = firebaseFirestore.collection(COLLECTION).document(orderId)
        document.delete()
    }

    fun getOrders(): MutableLiveData<List<Order>> {
        val liveOrders = MutableLiveData<List<Order>>()
        firebaseFirestore.collection(COLLECTION)
            .whereEqualTo(FIELD_USER_ID, firebaseAuth.uid)
            .orderBy(FIELD_DATA, Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.w(TAG, "Listen failed.", firebaseFirestoreException)
                    return@addSnapshotListener
                }
                if (querySnapshot != null && !querySnapshot.isEmpty) {
                    val orders = ArrayList<Order>()
                    querySnapshot.forEach {
                        val order = it.toObject<Order>()
                        order.id = it.id
                        orders.add(order)}
                    liveOrders.postValue(orders)
                } else {
                    Log.d(TAG, "No order has been found")
                }
            }
        return liveOrders
    }

    fun getProductByCode(orderId: String, data: Date): MutableLiveData<Order> {
        val liveOrder: MutableLiveData<Order> = MutableLiveData()
        firebaseFirestore.collection(COLLECTION)
            .whereEqualTo(FIELD_DATA, data)
            .whereEqualTo(FIELD_ORDER_ID,orderId)
            .whereEqualTo(FIELD_USER_ID, firebaseAuth.uid)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.w(TAG, "Listen failed.", firebaseFirestoreException)
                    return@addSnapshotListener}
                if (querySnapshot != null && !querySnapshot.isEmpty) {
                    val orders = ArrayList<Order>()
                    querySnapshot.forEach {
                        val order = it.toObject<Order>()
                        order.id = it.id
                        orders.add(order)
                    }
                    liveOrder.postValue(orders[0])
                } else {
                    Log.d(TAG, "No order has been found")
                }
            }
        return liveOrder
    }

}

