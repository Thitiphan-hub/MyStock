package com.codemobiles.mystock.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codemobiles.mystock.R
import com.codemobiles.mystock.databinding.CustomProductListBinding
import com.codemobiles.mystock.models.JsonDemoResult

// เพิ่ม Constactor ต่อท้ายและประกาศตัวแปร ที่เก็บแบบ ArrayList<> หรือ Type อื่นๆ เพื่อจะทำการรองรับข้อมูล Database ที่จะผ่านเข้ามาผ่านทาง API แต่ต้องระวังข้อมูลที่เข้ามาว่าอาจจะเป็นค่า Null ได้ ?
class CustomProductListAdapter(private var productList: List<JsonDemoResult>?) :
    RecyclerView.Adapter<CustomProductListAdapter.ViewHolder>() {  // พิม ViewHolder เองดีกว่า และกด alt + enter


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomProductListAdapter.ViewHolder {  // ใช้ layout อะไรในการแสดงผลของส่วน RecycleView
        val binding = CustomProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false) //parent คือตัวแปรที่เป็น parameter ใส่ false แนบอะไรใส่ยัง แต่ false ไปเพราะเราไม่ต้องการ Attach // กำลังทำ ViewBinding ลักษณะแบบ Adapter
        return ViewHolder(binding.root, binding)  // CustomProductListBinding จะมีการ binding กับพวก id ต่างๆ เพราะมันมาจาก Layout แล้วมันจะส่งมาที่ ViewHolder และตัวนี้จะเป็นตัวถือ binding มันถือเพื่อ เอา binding มา .price.text เราจึงต้องเปลี่ยน content
    }


    override fun getItemCount() = productList?.size ?: 0  // จำนวณแถวใน RecycleView // ทำการเช็ค Logic ก่อนอย่าฝืน !! เพราะอาจมีค่า Null จริงๆ เราจึงทำการ If(){ หรือ ?. = ถ้ามัน case ที่ null เราก็จะทำการ return 0 เข้าไป แต่ถ้ามันมีค่ามันก็จะส่งค่าใน Size ไป


    override fun onBindViewHolder(holder: CustomProductListAdapter.ViewHolder, position: Int) {  // เปลี่ยน Content แถวที่ 1 ใช้ content แบบนี้ #Example ตัว Holder นี้ มันจะมากับ viewHolder จึงสามารถเข้าถึงทุกอย่างได้แล้ว ระวังด้วยว่ามันเป็น RecycleView อยู่หรือเปล่า?
        val binding = holder.binding

        with(binding) {



//            Glide.with(imageviewProduct.context)
//                .load("https://secure.ap-tescoassets.com/assets/TH/537/8858891301537/ShotType1_540x540.jpg")
//                .into(imageviewProduct)

          imageviewProduct.setImageResource(R.drawable.ad)
            // ใส่ข้อมูล list position อย่างไงก็เข้ามาเรื่อยๆ

// check Null โดย let ตัวนี้ไม่เท่ากับ null หรือเปล่าถ้าไม่เท่ากับ ส่วนต่อไปจะมีค่า
// if (productList != null) ประกาศแบบด้านล่างแปลว่าตัวนี้มันเป็นและต่อไปจะไม่เท่ากับ null
            productList?.let { list ->
                val item = list[position]
                val lorem = item.name
                textviewName.text = lorem
                textviewDetail.text = lorem
                textviewPrice.text = "฿ 140"
                textviewStock.text = "16 piece"
            }

        }


    }

    inner class ViewHolder(view: View, val binding: CustomProductListBinding) :
        RecyclerView.ViewHolder(view) {  // ตัว Class ที่เชื่อมกับตัว ViewGroup ตัวกำ Viewbinding ข้างล่าง

    }
}