package com.codemobiles.mystock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.codemobiles.mystock.databinding.ActivityCreateBinding
import com.codemobiles.mystock.databinding.ActivityEditBinding
import com.codemobiles.mystock.services.product.APIClient
import com.codemobiles.mystock.services.product.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {



    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setupWidget()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupWidget() {

//        stock?.let { product ->
//            binding.edittextName.setText(product.name)
//            binding.edittextPrice.setText(product.price.toString())
//            binding.edittextStock.setText(product.stock.toString())

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




