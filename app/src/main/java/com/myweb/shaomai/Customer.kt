package com.myweb.shaomai

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Customer (
    @Expose
    @SerializedName("customer_id") val customer_id: String,

    @Expose
    @SerializedName("customer_firstname") val customer_firstname: String,

    @Expose
    @SerializedName("customer_lastname") val customer_lastname: String){}
