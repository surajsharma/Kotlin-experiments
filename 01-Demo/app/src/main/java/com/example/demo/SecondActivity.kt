package com.example.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.demo.databinding.ActivitySecondBinding

import android.util.Log
import android.content.Intent

public class SecondActivity : AppCompatActivity() {

    private var _binding: ActivitySecondBinding? = null
    private val binding: ActivitySecondBinding
      get() = checkNotNull(_binding) { 
        "Activity has been destroyed" 
      }
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extraData = intent.getStringExtra("extra_data") 
        Log.d("SecondActivity 🍎🍎🍎", "extra data is $extraData")
        val t1 = _binding?.text1?.setText(extraData)
    }
    
    override fun onBackPressed() { 
        val intent = Intent() 
        intent.putExtra("data_return", "Hello FirstActivity")
        setResult(RESULT_OK, intent) 
        finish() 
    }    
}