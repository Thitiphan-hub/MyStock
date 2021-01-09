package com.codemobiles.mystock.models

//สร้าง Data Class แต่มี Type ไม่เวิคนะ ให้เรามองว่า JsonDemo เป็น obj เราค่อยไปเขียน List ทีหลังก็ได้ เพาะนี่คือตัวแปร เราจะเอาตัวล่างเป็นตัวหลัก แล้วเดี๋ยวเราค่อยเอา List<JsonDemoResult> มาใส่  ทีหลัง
//Dataclass มันเป็น List เพราะใน Format json นั้นมันอยู่ใน array สร้างตัวแปรเพื่อครอบ obj แต่ละตัว และ obj แต่ละตัวก็มีคลาสขึ้นไปอีก

// obj1 == obj2 = false
// Data class obj1 == obj2 = true มันไม่ต้องเช็คด้วย .equed มันเช็คเลย ไม่ได้เช็คค่าจาก เมมนะ มันไม่สนใจ instanc

data class JsonDemoResult(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)

data class Geo(
    val lat: String,
    val lng: String
)