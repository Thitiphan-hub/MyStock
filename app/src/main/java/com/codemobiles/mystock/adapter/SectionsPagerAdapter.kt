package com.codemobiles.mystock.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codemobiles.mystock.ProductFragment
import com.codemobiles.mystock.R
import com.codemobiles.mystock.StockFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    val tabIcon: Array<Int> = arrayOf(R.drawable.ic_product, R.drawable.ic_stock)      // ใช้ R. เป็นการเข้าถึงเพราะ R เบื้องหลังมันคือตัวเลขที่ระบบ gen มาให้       // arrayOf ลักษณะ type อะไรก็ได้
    val tabText: Array<String> = arrayOf(*context.resources.getStringArray(R.array.tab_title))   //SetTabText // * = เป็นเหมือนการทำ space javascp แต่นี่คือ Kotlin
    // *array2 = xxxx , xxxxxxx , xxxxx  จะทำการถอดค่าทุกตัวออกมา
    override fun getItemCount() = tabIcon.size  // เป็นการ Return ค่าไป เป็นการ ShortForm


    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
                ProductFragment()
            }
            else -> {
                StockFragment()
            }
        }
    }
}



//
//if (position == 0 ){
//    return ProductFragment()
//}
//return StockFragment()




