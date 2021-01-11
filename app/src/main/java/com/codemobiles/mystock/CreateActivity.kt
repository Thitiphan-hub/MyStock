package com.codemobiles.mystock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.codemobiles.mystock.databinding.ActivityCreateBinding

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWidget()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupWidget() {
        binding.buttonSubmit.setOnClickListener {
            val name = binding.edittextName.text.toString()
            val price = binding.edittextPrice.text.toString()
            val stock = binding.edittextStock.text.toString()

            val textToNumber: (String) -> Int = { text ->
                if (text.isEmpty()) {
                    0
                } else {
                    text.toInt()
                }
            }



        }
    }


}