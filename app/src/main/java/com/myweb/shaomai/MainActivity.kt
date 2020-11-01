package com.myweb.shaomai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_check__customers__list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var customersList : ArrayList<Customer> = arrayListOf<Customer>()
    val createClient : CustomerAPI = CustomerAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.addItemDecoration(
            DividerItemDecoration(recycler_view.context,
            DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume(){
        super.onResume()
        callCustomer()
    }

    fun callCustomer(){
        customersList.clear()
        createClient.retrieveCustomer()
            .enqueue(object : Callback<List<Customer>> {
                override fun onResponse(
                    call: Call<List<Customer>>,
                    response: Response<List<Customer>>
                ){
                    response.body()?.forEach{
                        customersList.add(Customer(it.customer_id, it.customer_firstname, it.customer_lastname))
                    }
                }

                override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext,"Error2",Toast.LENGTH_LONG).show()
                }
            })
    }

    fun ClickSearch(v: View){
        customersList.clear()
        if(Search_Customer.text.isEmpty()){
            callCustomer()
        }else{
            createClient.retrieveCustomerID(Search_Customer.text.toString())
                .enqueue(object :Callback<Customer>{
                    override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                        if(response.isSuccessful){
                            customersList.add(Customer(response.body()?.customer_id.toString(),
                                response.body()?.customer_firstname.toString(),
                                response.body()?.customer_lastname.toString()
                            ))
                        }else{
                            Toast.makeText(applicationContext,"Student ID Not found", Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<Customer>, t: Throwable) = t.printStackTrace()
                })
        }
    }
}

