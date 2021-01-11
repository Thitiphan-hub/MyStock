package com.codemobiles.mystock.services.stock

import com.codemobiles.mystock.models.JsonDemoResult
import com.codemobiles.mystock.models.JsonPhotoResult
import retrofit2.Call
import retrofit2.http.*


// จัดการตัวข้อมูล Interface เป็นการประกาศ Function อย่างเดียว
interface APIStockService {

    @GET("photos")
    fun getDemoPhoto(): Call<List<JsonPhotoResult>>
//
//    @POST(API_PRODUCT)
//    fun addProduct(@Body product: ProductRequest): Call<Any>
//
//    @PUT("$API_PRODUCT/{$API_PRODUCT_PARAMS_ID}")
//    fun editProduct(@Path(API_PRODUCT_PARAMS_ID) id: Int, @Body product: ProductRequest): Call<Any>
//
//    @DELETE("$API_PRODUCT/{$API_PRODUCT_PARAMS_ID}")
//    fun deleteProduct(@Path(API_PRODUCT_PARAMS_ID) id: Int): Call<Any>
}