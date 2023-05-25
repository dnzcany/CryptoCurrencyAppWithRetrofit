package com.denobaba.kriptoparauygulamasi.service

import android.telecom.Call
import retrofit2.http.GET
import com.denobaba.kriptoparauygulamasi.model.CryptoModel as CryptoModel

interface CryptoAPI {


    //normalde link https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json bu
    //fakat başını kestik,başını başka yerde kullanacaz
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): retrofit2.Call<List<CryptoModel>>
}