package com.codemobiles.mystock.services.stock

import com.codemobiles.mystock.services.stock.APIStockClient.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIStockClient {
    private  var retrofit: Retrofit? = null


    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        return retrofit!!
    }
    fun getImageURL() = "https://jsonplaceholder.typicode.com/url/"
}