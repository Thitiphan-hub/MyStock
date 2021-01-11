package com.codemobiles.mystock

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.codemobiles.mystock.databinding.ActivityHomeBinding
import com.codemobiles.mystock.databinding.CustomTabMenuBinding
import com.codemobiles.mystock.adapter.SectionsPagerAdapter

import com.google.android.material.tabs.TabLayoutMediator


class HomeActivity : AppCompatActivity() {

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter // เราจะมีการ Assign value ให้มันทีหลัง อย่างไงก็ต้องมีค่าเพราะจะเอาตัว Adapter มาแสดงผล
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, lifecycle)
        setupViewPager()
        setupWidget()
        setupTab()

    }

    private fun setupTab() {    // ทำการโยน tabs,viewPager
        TabLayoutMediator(binding.tabs, binding.viewPager, TabLayoutMediator.TabConfigurationStrategy // binding อีกตัว เพราะคือ Function call back กลับมา
        { tab, position ->
                val binding = CustomTabMenuBinding.inflate(layoutInflater)               // สร้าง xml และ ฺBinding ผ่าน Logic Programming และ ทำข้อมูล แบบ Array ได้ เพราะมันโยน Position เข้าไปได้และอ้างอิง ที่ Index
                binding.iconTab.setImageResource(sectionsPagerAdapter.tabIcon[position]) // ตัวแปรของ Section และยัด array เข้าไปบอก position
                binding.textTab.text = sectionsPagerAdapter.tabText[position]
                tab.customView = binding.root  // binding CustomTabMenu และเรียก CustomView
            }).attach()

    }

    private fun setupWidget() {
        binding.fab.setOnClickListener { view ->
            Intent(applicationContext,CreateActivity::class.java).run {
                startActivity(this)
            }

        }
    }

    private fun setupViewPager() {   // การเปลี่ยนหน้า Tab ทำแยกออกมาเป็น Func หนึ่ง // เป็น Func call Back เเป็นการตกแต่ง // Apply = set poperties ต่างๆ จะมี this
        binding.viewPager.apply {
            adapter = sectionsPagerAdapter   // อันล่าง also เหมือนเราทำด้านบนเสร็จแล้ว แล้วจะให้มันทำข้างล่างต่อ
        }.also {    // xyz -> จะตั้งหรือไม่ก็ได้  // ถ้าต้องการเปลี่ยนตัวแปร It xyz -> ซึ่งเราจะเปลี่ยน การเขียนแบบ Lambda // คือตัวนี้เป็น Param ที่ส่งมาและเอามาด้านล่างแสดงผล
                it.setPageTransformer(HorizontalFlipTransformation())    // ตอนแรกมันจะแดง ให้เรา new obj ก่อน () // it ตัวแปรที่มัน Return ออกมาจากตัวข้างบน
                it.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {    // Kotlin จะประกาศ Interface ลักษณะแบบ Anonymous
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        if (position == 0) {  // ต้องโยน position เข้ามาเพระาตอนปัดซ้ายขวามันมีการโยน  == 0 ก็คือหน้าแรก
                            binding.fab.visibility = View.INVISIBLE   // เอา binding.fab visibility = Invisible คือการเอามันมาซ่อน ถ้าเท่ากับ 0 ก็คือการซ่อน
                        } else {
                            binding.fab.visibility = View.VISIBLE   // VISIBLE คือการโชว์มัน
                        }
                    }
                })
            }
    }
}