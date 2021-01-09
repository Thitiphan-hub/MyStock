package com.codemobiles.mystock.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.codemobiles.mystock.R
import com.codemobiles.mystock.databinding.CustomProductListBinding
import com.codemobiles.mystock.databinding.CustomStockListBinding

class CustomStockListAdapter(var stockList : ArrayList<String>?) : RecyclerView.Adapter<CustomStockListAdapter.ViewHolder>() {  // พิม ViewHolder เองดีกว่า และกด alt + enter


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomStockListAdapter.ViewHolder {  // ใช้ layout อะไรในการแสดงผลของส่วน RecycleView
        val binding = CustomStockListBinding.inflate(LayoutInflater.from(parent.context), parent, false) //parent คือตัวแปรที่เป็น parameter ใส่ false แนบอะไรใส่ยัง แต่ false ไปเพราะเราไม่ต้องการ Attach // กำลังทำ ViewBinding ลักษณะแบบ Adapter
              return ViewHolder(binding.root, binding)  // CustomProductListBinding จะมีการ binding กับพวก id ต่างๆ เพราะมันมาจาก Layout แล้วมันจะส่งมาที่ ViewHolder และตัวนี้จะเป็นตัวถือ binding มันถือเพื่อ เอา binding มา .price.text เราจึงต้องเปลี่ยน content
    }


    // จำนวณแถวใน RecycleView // การเขียนแบบนี้เป็นการเขียนแบบ Defailt Value ถ้าฝั่งนี้ไม่มีแล้วจะเอาอะไรมาแสดงผลละ

    override fun getItemCount() = stockList?.size ?: 0




    override fun onBindViewHolder(holder: CustomStockListAdapter.ViewHolder, position: Int) {  // เปลี่ยน Content แถวที่ 1 ใช้ content แบบนี้ #Example ตัว Holder นี้ มันจะมากับ viewHolder จึงสามารถเข้าถึงทุกอย่างได้แล้ว ระวังด้วยว่ามันเป็น RecycleView อยู่หรือเปล่า?
        val binding = holder.binding

        val lorem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        binding.imageviewProduct.setImageResource(R.drawable.banner)
        binding.textviewName.text = lorem
        binding.textviewDetail.text = lorem
        binding.textviewPrice.text = "฿ 20,000"
        binding.textviewStock.text = "223 piece"
    }


  inner  class ViewHolder(view: View , val binding : CustomStockListBinding) : RecyclerView.ViewHolder(view) {  // ตัว Class ที่เชื่อมกับตัว ViewGroup ตัวกำ Viewbinding ข้างล่าง

    }
}