package com.codemobiles.mystock.services.product

import com.codemobiles.mystock.models.JsonDemoResult
import com.codemobiles.mystock.models.JsonPhotoResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


// จัดการตัวข้อมูล Interface เป็นการประกาศ Function อย่างเดียว
interface APIService {

//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String?): Call<List<Repo?>?>?
    // ขึ้นอยู่กับโจทย์ ว่าเราจะเชื่อมต่อ Path แบบใด ไปที่ Http Method อะไร // กลับไป  URL และ Path ตามนี้ และมีข้อมูลกลับมาแปลว่า Get ถ้าเป็น Post จะไม่มีข้อมูลแสดงผลแน่นอน
   // เราจะต้องติดต่อที่ interface ตรงนี้ก่อน จะต้องเรียกใช้ ApiClient ก่อน และค่อยเรียกใช้ .Function // ให้สังเกต Fun ไม่มี param มาเพราะเราติดต่อ path ตรงๆเลย

    // แต่ถ้าทำงานจริงๆ จะมีการ call param เช่น พาร์ทชื่อเราเข้าไป , value 1234 หรือเราทำลักษณะ Query String เพราะ  Api แค่ตัว Test มาติดต่อ Network
    // ในส่วน return call จะลักษณะ array ่อน และค่อยมา array object Json อาจจะมีค่า Null ก็ได้
    // @POST ,@PUT
    //ต่อไป อาจจะมีการดึง Query String
    @GET("users")
    fun getDemoUser(): Call<List<JsonDemoResult>>   // ประกาศ Fun return call<List<ตัว Model ของเราที่แปลงมา>> alt + Enter = call(Retrofit2)

    @GET("photos")
    fun getDemoPhoto(): Call<List<JsonPhotoResult>>
}