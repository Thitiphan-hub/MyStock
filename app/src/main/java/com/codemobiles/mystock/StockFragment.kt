package com.codemobiles.mystock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemobiles.mystock.adapter.CustomProductListAdapter
import com.codemobiles.mystock.adapter.CustomStockListAdapter
import com.codemobiles.mystock.databinding.FragmentProductBinding
import com.codemobiles.mystock.databinding.FragmentStockBinding
import com.codemobiles.mystock.models.JsonPhotoResult
import com.codemobiles.mystock.services.stock.APIStockClient
import com.codemobiles.mystock.services.stock.APIStockService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StockFragment : Fragment() {

    private lateinit var binding: FragmentStockBinding
    private lateinit var customAdapter: CustomStockListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentStockBinding.inflate(layoutInflater)

        customAdapter = CustomStockListAdapter(null)  // มีให้เห็นง่ายๆก่อน ถ้าเราจะติดต่อ Network ค่อยมา Binding ให้มันใหม่ set รับค่าจากส่วนนี้ ไม่ต้อง dummy มาอย่างตอนแรก
        setupWidget()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        feedNetwork()
    }

    private fun feedNetwork() {
        binding.swipeRefresh.isRefreshing = true
        val call =  APIStockClient.getClient()?.create(APIStockService::class.java)?.getDemoPhoto()

             call?.enqueue(object : Callback<List<JsonPhotoResult>> {
                 override fun onFailure(call: Call<List<JsonPhotoResult>>, t: Throwable) {
                     context?.showToast(t.message.toString())
                     binding.swipeRefresh.isRefreshing = false
                 }
                 override fun onResponse(
                     call: Call<List<JsonPhotoResult>>,
                     response: Response<List<JsonPhotoResult>>
                 ) {
                     if (response.isSuccessful){
                         binding.stockRecyclerview.adapter = CustomStockListAdapter(response.body())
                     }else{
                         context?.showToast(response.message().toString())
                     }
                     binding.swipeRefresh.isRefreshing = false
                 }


             })


    }

    private fun setupWidget() {
        binding.stockRecyclerview.apply {
            adapter = customAdapter
            //important // RecycleView ข้อดี = เราสามารถ รีเวิด ให้มันไปซ้ายขวา สามารถเลื่อนไปซ้ายขวา RecycleView.HORIZONTAL , false , GridLayoutManager() ใส่จำนวณที่จะตี Row
            layoutManager = LinearLayoutManager(context) // เป็นการบอกว่า RecycleView จะทำการ Scro แบบแนวตั้ง (Varticle) ถ้าไม่มีจะไม่มีข้อมูลมาแน่ๆ

        }.also {
            it.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL
                )
            ) // การตีเส้นและการตั้งค่าแนวตั้งหรือแนวนอนต้องดูด้วย RecycleView มีให้อยู่แล้ว
            it.setHasFixedSize(true) // กรณีข้อมูล Fixsize มันจะได้ไม่คำนวณตลอดเวลา
        }

        binding.swipeRefresh.setOnRefreshListener {
            feedNetwork()
        }
    }



}