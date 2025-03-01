
package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmi.databinding.ActivityMainBinding
import android.widget.Toast

public class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    
    private val binding: ActivityMainBinding
      get() = checkNotNull(_binding) { "Activity has been destroyed" }
    
    private var heightValue: Double = 0.0 
    private var weightValue: Double = 0.0 
    private var bmi: Double = 0.0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate and get instance of binding
        _binding = ActivityMainBinding.inflate(layoutInflater)

        // set content view to binding's root
        setContentView(binding.root)
        
        val weight = binding.w
        val height = binding.h
        
        binding.wp.setOnClickListener{
            if(weight.text.toString().isNotEmpty() && weight.text.toString().toDouble() > 0){
                weightValue = weight.text.toString().toDouble()
            }
            
            weightValue = maxOf(0.0, weightValue + 0.1)
            weight.setText(String.format("%.2f", weightValue))
        }

        binding.wm.setOnClickListener{
            if(weight.text.toString().isNotEmpty() && weight.text.toString().toDouble() > 0){
                weightValue = weight.text.toString().toDouble()
            }
            
            weightValue = maxOf(0.0, weightValue - 0.1)
            weight.setText(String.format("%.2f", weightValue))
        }

        binding.hp.setOnClickListener{
            if(height.text.toString().isNotEmpty() && height.text.toString().toDouble() > 0){
                heightValue = height.text.toString().toDouble()
            }
            
            heightValue = maxOf(0.0, heightValue + 0.1)
            height.setText(String.format("%.2f", heightValue))
        }

        binding.hm.setOnClickListener{
            
            if(height.text.toString().isNotEmpty() && height.text.toString().toDouble() > 0){
                heightValue = height.text.toString().toDouble()
            }
            
            heightValue = maxOf(0.0, heightValue - 0.1)
            height.setText(String.format("%.2f", heightValue))
        
        }

        binding.calcBtn.setOnClickListener {
            val ht = height.text.toString().toDouble()
            val wt = weight.text.toString().toDouble()
            
            bmi =(wt/ht.times(ht))
            
            Toast.makeText(this,"$bmi",Toast.LENGTH_LONG).show()
            
            displayBMIResult(bmi)
        }
    }

    private fun displayBMIResult(bmi: Double) {

        var bmiLabel =""
        var bmiDescription  = ""

        when {
            bmi < 18.5 -> {
                bmiLabel = "Underweight"
                bmiDescription = "You should take better care of yourself! Eat more!"
            }
            bmi in 18.5..24.9 -> {
                bmiLabel = "Normal"
                bmiDescription = "Congratulations! You are in a good shape!"
            }
            bmi in 25.0..29.9 -> {
                bmiLabel = "Overweight"
                bmiDescription = "You really need to take care of yourself! Workout maybe!"
            }
            bmi >= 30.0 -> {
                bmiLabel = "Obese Class"
                bmiDescription = "You might be in a dangerous condition! Act now!"
            }
        }

        binding.result.text = resources.getString(R.string.bmi_result,bmi,bmiLabel,bmiDescription)
    }
        
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
