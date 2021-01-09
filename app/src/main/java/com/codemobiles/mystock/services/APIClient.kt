package com.codemobiles.mystock.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// singleton pattern คือ การสร้าง instance เเดียว และสามารถเรียกไปใช้ได้หลายที่ ข้อดี ประหยัดพื้นที่ Memory เพราะมันถูกสร้างแค่ตัวเดียว
// java = static
// kotlin = object
// keyword Object || Class คุณสมบัติเหมือนกัน
// Object จะเป็น Singleton Instance เรียกใช้ได้เลยตัวเดียว Static , lazy

object APIClient {
   private  var retrofit: Retrofit? = null // ? = null จะเอาเป็นลักษณะ Null ไว้ก่อน
// ลักษณะแบบนี้เรียก Factory สร้าง Function เพื่อมา Assign value เดี๋ยว Func ตัวนี้จะ New instance ของ obj ตัวนี้ขึ้นมาเองด้านบนตัวแปร
    fun getClient(): Retrofit{   //จะใช้ตัวแปรนี้เรียก Network ลบ ? เพราะเราไม่รู็มันจะมีค่าเมื่อไหร่ ตอนแรกที่ใส่เพราะไม่มีข้อมูล
         if (retrofit == null){ // ถ้ามัน == null จะ new value ให้
             retrofit = Retrofit.Builder()
                 .baseUrl("https://jsonplaceholder.typicode.com/") // คือ URL จะไม่มี /users หรืออะไรด้านหลัง ส่วนที่ตัดไปเราจะเอาไปเรียกใช้ตอน Query StringInterface
                 .addConverterFactory(GsonConverterFactory.create())// add ลักษณะ mappingJson ให้มัน Model ไม่ใช่การติดต่อ Network แค่การแปลง
                 .build()
         }
        return retrofit!!  //confirm ว่ามันมีค่าแน่ๆ  // เหมือนกันสร้าง Function ขึ้นมาเพื่อ New popertes Return ถ้ามันไม่มีค่านะ // ถ้าตัวข้างน null เราจะร Return ในลักษณะ ?

    }
//เราจะเรียกใช้ Fun นี้ตอน ติดต่อ Network
}