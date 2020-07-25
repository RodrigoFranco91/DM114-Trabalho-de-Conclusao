package br.com.rodrigo.dm114.network

data class Order (
    var id: Long = 0,
    var userName: String,
    var orderId: Long = 0,
    var Status: String,
    var productCode: String
)
