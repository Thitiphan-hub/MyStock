package com.codemobiles.mystock

import android.content.Context
import android.widget.Toast

// เป็นการสร้าง Extensions เพื่อขยาย Activity
fun Context.showToast(message : String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun String.covertToBath() : String{
    return "$this To Bath"
}