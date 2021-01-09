package com.codemobiles.mystock


import android.content.ContextWrapper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codemobiles.mystock.databinding.ActivityMainBinding
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // มันจะมี Value แน่นอนแต่ตอนนี้มันยังไม่มีนะ (การระบุพอร์ปเปอตี้ค้องอินทิเชียลค่า) เพื่อที่จะไปเรียกใช้ที่อื่นได้

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()  // easyPrefs

        if (Prefs.getBoolean(PREFS_KEY_IS_LOGIN, false)) {   // ตอน Save ตอน Get ใช้ key เดียวกัน //ซึ่งถ้ามันไม่มีค่าก็เป็น return false แน่นอน // ถ้าสมมุติเราเคย login จะเข้า then ไม่เคยจะเข้า else จะโชว์หน้า login
            openHomePage()
        } else {
            binding = ActivityMainBinding.inflate(layoutInflater)  // ยัดค่าให้มันแล้ว // สร้างให้มันเป็น obj การยัดค่า Activity ไปเป็น Class และให้มันเกิดมาเป็น obj
            setContentView(binding.root)
            setupWidget()
        }

    }

    private fun openHomePage() { // scope function อะไรที่เป็นลักษณะ obj มันสามารถ . scope function ได้เลย
        Intent(this@MainActivity, HomeActivity::class.java).run {
            startActivity(this) // this in scope
            finish()
        }

    }

    private fun setupWidget() {

        binding.loginButtonLogin.setOnClickListener {
            validDate()
        }

        binding.loginScrollview.apply {
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false    // มองง่ายเข้าใจ รู้เลยว่าข้างในเกี่ยวกับเรื่องอะไร // apply เหมือนจะบอกว่าให้เรา config Poperty ตัวนี้นะ เช่น ตัวนี้มี fig เรื่องอะไรบ้าง
        } //.run { // ถ้า Run เราจะ Run คำสั่งอะไรก็ได้ ใน Scope และให้มันจบที่ scope }

    }

    private fun validDate() {
        val username = binding.loginEdittextUsername.text.toString()
        val password = binding.loginEdittextPassword.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            showToast("Username or Password is Empty")
            return
        }

        if (username == "boss@gmail.com" && password == "password") { // ทดสอบใช้งานจริงไม่ได้ ควรตรวจสอบและส่งรหัสไปจัดการแล้วทำการ send Token กลับมา save
            Prefs.putBoolean(PREFS_KEY_IS_LOGIN, true) // ฉันจะเก็บ value true ใน Key is_Login
            Prefs.putString(PREFS_KEY_IS_USERNAME, username) // เก็บ username ในลักษณะ String ใว้ที่ Application
            openHomePage()     //เรียกใช้ Function
            return
        }
            showToast("Username or Password in correct")
        }

    }







