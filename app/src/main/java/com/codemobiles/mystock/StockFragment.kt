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


class StockFragment : Fragment() {

    private lateinit var binding: FragmentStockBinding
    private lateinit var customAdapter : CustomStockListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStockBinding.inflate(layoutInflater)

        customAdapter = CustomStockListAdapter(arrayListOf("AAA"))   // มีให้เห็นง่ายๆก่อน ถ้าเราจะติดต่อ Network ค่อยมา Binding ให้มันใหม่ set รับค่าจากส่วนนี้ ไม่ต้อง dummy มาอย่างตอนแรก
        binding.stockRecyclerview.apply {
            adapter = customAdapter

            //important // RecycleView ข้อดี = เราสามารถ รีเวิด ให้มันไปซ้ายขวา สามารถเลื่อนไปซ้ายขวา RecycleView.HORIZONTAL , false , GridLayoutManager() ใส่จำนวณที่จะตี Row
            layoutManager = LinearLayoutManager(context) // เป็นการบอกว่า RecycleView จะทำการ Scro แบบแนวตั้ง (Varticle) ถ้าไม่มีจะไม่มีข้อมูลมาแน่ๆ

        }.also {
            it.addItemDecoration(DividerItemDecoration(context , DividerItemDecoration.VERTICAL)) // การตีเส้นและการตั้งค่าแนวตั้งหรือแนวนอนต้องดูด้วย RecycleView มีให้อยู่แล้ว
            it.setHasFixedSize(true) // กรณีข้อมูล Fixsize มันจะได้ไม่คำนวณตลอดเวลา
        }
        return binding.root
    }


}