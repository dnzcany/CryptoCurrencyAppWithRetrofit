package com.denobaba.kriptoparauygulamasi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denobaba.kriptoparauygulamasi.adapter.recycleviewadaptor
import com.denobaba.kriptoparauygulamasi.databinding.ActivityMainBinding
import com.denobaba.kriptoparauygulamasi.model.CryptoModel
import com.denobaba.kriptoparauygulamasi.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//normalde link https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json bu
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var recyclerViewAdapter: recycleviewadaptor?= null


    private val base_url= "https://raw.githubusercontent.com/"

    private var cryptomodels : ArrayList<CryptoModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_main)

        cryptomodels = ArrayList<CryptoModel>()
        recyclerViewAdapter = recycleviewadaptor(cryptomodels)
        binding.recycleview.adapter = recyclerViewAdapter

        binding.recycleview.layoutManager = LinearLayoutManager(this@MainActivity) //burdaki recycleview main aktivitedeki recycleviewin id si



        loadData()


    }





    private fun loadData(){


        val retrofit = Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(CryptoAPI::class.java) //serviceyle retrofiti bağladık

        val call =service.getData()

        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(


                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {

                if (response.isSuccessful){
                    response.body()?.let {
                        cryptomodels?.clear()
                        cryptomodels?.addAll(it)
                        Log.i("loadData", "Data received: ${cryptomodels?.size}")
                        recyclerViewAdapter?.updateData(it)
                        /*for (cryptoModel: CryptoModel in cryptomodels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        } */
                    }
                }

            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                Log.e("loadData", "Error loading data", t)
                t.printStackTrace()



            }

        })
    }

}