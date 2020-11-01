package com.myweb.shaomai


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CustomerAPI {
    @GET("allcustomer")
    fun  retrieveCustomer(): Call<List<Customer>>

    @GET("customer/{customer_name}")
    fun retrieveCustomerID(
        @Path("customer_name") customer_id: String
    ): Call<Customer>

    companion object {
        fun create(): CustomerAPI {
            val cusClient: CustomerAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CustomerAPI::class.java)
            return cusClient
        }
    }
}