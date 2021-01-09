package com.codemobiles.mystock

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.codemobiles.mystock.adapter.CustomProductListAdapter
import com.codemobiles.mystock.databinding.FragmentProductBinding
import com.codemobiles.mystock.models.JsonDemoResult
import com.codemobiles.mystock.services.APIClient
import com.codemobiles.mystock.services.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var customAdapter: CustomProductListAdapter // ประกาศแบบนี้เพื่อให้มันรู้จักข้อมูลที่จะเข้ามา lateinit เพราะมันอาจจะมีค่าเข้ามา

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductBinding.inflate(layoutInflater)
        customAdapter = CustomProductListAdapter(null)       // เราสามารถใส่ Null ได้เพราะเราตั้งค่า param ให้ Null ได้      // ประกาศตัวแปรและ Set adapter ให้มันก่อน โดยทำการ New obj และทำการโยน Array เข้าไปรับมัน
        setupWidget()
        feedNetwork()
        return binding.root
    }


    //ต้องาร้าง Function ในการ FeedNetwork
    // เริ่มทำการ Call เรียกตัว obj ที่เราสร้างและ .get รูปแบบ singleton pattern Create ตาม interface ที่เราต้องการจะใช้ ให้าังเกตด้วยว่าบล็อคนี้จะ Return ออกมาเป็น null ไหม
    // .get Function ที่อยู่ใน Interface สังเกตด้วยก้อนทั้งก้อนมีค่า Null ไหม
    // Code ดักจับ Event จะมีตัว callback กลับมา สังเกตด้วยว่าตัวแปรนี้มีโอกาศเป็น Null
    // .enq ก็ใส่ obj เข้าไป : CallBack Retrofit2 แล้วใส่ List แล้วดูว่าตัวบนมัน return อะไรออกมา
    // และใส่ {} alt + enter obj และ implement override onResponse , onFailure
// รูปแบบด้านล่างเป็น Anorymouch object extension ต้อง this@ ข้างนอกถ ถ้าต้องการ showtoast ต้อง ใส่ context
    // เขียน logic ใน Function if(response.isSuccessful) ถ้ามันโชว์ได้ ก็ response.body()
    //ทำการเช็ค log ด้วยว่าติดปัญหาไรไหม Debug
    // ทำการเขียนเมื่อมีค่าเข้ามาแล้ว binding.recycleView.adapter = Custom_list ตัวนั้น


    private fun feedNetwork() {
        binding.swipeRefresh.isRefreshing = true  // binding refresh แสดงสถานะการโหลด
        APIClient.getClient().create(APIService::class.java).getDemoUser()
            .let { call -> // เปลี่ยนชื่อ ไม่อยากให้มัน it

                Log.d("bp_network", call.request().url().toString())

                call.enqueue(object : Callback<List<JsonDemoResult>> {
                    override fun onFailure(call: Call<List<JsonDemoResult>>, t: Throwable) {
                        context?.showToast(t.message.toString())
                        binding.swipeRefresh.isRefreshing = false // เป็นการ Stop refresh
                    }
                    override fun onResponse(
                        call: Call<List<JsonDemoResult>>,
                        response: Response<List<JsonDemoResult>>
                    ) {
                        if (response.isSuccessful) {
                            binding.productRecyclerview.adapter =
                                CustomProductListAdapter(response.body())
                        } else {
                            context?.showToast(response.message())
                        }
                        binding.swipeRefresh.isRefreshing = false // เป็นการ Stop refresh
                    }
                })
            }
    }

    private fun setupWidget() {
        binding.productRecyclerview.apply {
            adapter = customAdapter
            //important // RecycleView ข้อดี = เราสามารถ รีเวิด ให้มันไปซ้ายขวา สามารถเลื่อนไปซ้ายขวา RecycleView.HORIZONTAL , false ,
            // GridLayoutManager() ใส่จำนวณที่จะตี Row
            layoutManager = GridLayoutManager(context, 2) // เป็นการบอกว่า RecycleView จะทำการ Scro แบบแนวตั้ง (Varticle) ถ้าไม่มีจะไม่มีข้อมูลมาแน่ๆ
        }.also {
            it.addItemDecoration(GridSpacingItemDecoration(2, 20, true))
            // !! อย่าลืมคำนึงถึง performance ที่จะ Dynamic ตลอดเวลา เพราะ RecycleView มีการคำนวณอยู่ตลอด ถ้าเกิด Content Fix size ไว้หมดแล้ว เราก็ทำตามด้านล่างนี้ poperties กรณีจะไปในทางทิศเดียวกันขนาด
            it.setHasFixedSize(true) // กรณีข้อมูล Fixsize มันจะได้ไม่คำนวณตลอดเวลา
        }
        binding.swipeRefresh.setOnRefreshListener {  // การ scroll refresh
            feedNetwork()
        }

    }

}

