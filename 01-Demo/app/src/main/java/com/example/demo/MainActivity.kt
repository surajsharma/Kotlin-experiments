package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.demo.databinding.ActivityMainBinding

import android.util.Log
import android.widget.Switch
import android.widget.Toast
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.net.Uri

public class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
      get() = checkNotNull(_binding) { 
        "Activity has been destroyed" 
      }
    
    //In any activity we first need to override the onCreate function
    //by default this simply calls the parent class' onCreate
    //each activity should have a corresponding layout,
    //here in ../../../layouts/activity_main.xml
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate and get instance of binding
        //set content view to binding's root
        
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //setContentView(R.layout.activity_main)
        //val sw1 = findViewById<Switch>(R.id.sw1)
        
        val sw1 = _binding?.sw1
        val sw2 = _binding?.sw2
        val sw3 = _binding?.sw3
        val sw4 = _binding?.sw4
        
        val t1 = Toast.makeText(this, "sw1 is on", Toast.LENGTH_SHORT)
        val t2 = Toast.makeText(this, "sw1 is off", Toast.LENGTH_SHORT)
        
        
        sw2?.setOnCheckedChangeListener {
            _, isChecked ->
            //start activity with receptiveness
            val intent = Intent("com.demo.secondactivity.ACTION_START")
            startActivityForResult(intent, 1)
        }
        
        sw3?.setOnCheckedChangeListener {
            _, isChecked -> 
            if (isChecked) {
                if(sw1?.isChecked == true){
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:10086")
                    startActivity(intent)
                } else {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://evenzero.in")
                    startActivity(intent)
                }
            }

        }
        
        sw4?.setOnCheckedChangeListener {
            _, isChecked -> 
            if (isChecked) {
                if(sw1?.isChecked == true){
                    finish()
                } else {
                    //EXPLICIT intent because we are naming the activity 
                    //val intent = Intent(this, SecondActivity::class.java)
                    
                    //IMPLICIT intent actions the activity is registered 
                    //to respond to
                    val intent = Intent("com.demo.secondactivity.ACTION_START")
                    
                    //send data to activity
                    val data = "Hello SecondActivity"
                    intent.putExtra("extra_data", data)
                    startActivity(intent)
                }
            }
        }
        
        sw1?.setOnCheckedChangeListener { 
            _, isChecked ->
            // isChecked will be true if the switch is checked
            // Perform actions based on the switch state here
            if (isChecked) {
                t1.show()
            } else {
                t2.show()
            }
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean { 
        menuInflater.inflate(R.menu.main, menu) 
        return true 
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean { 
        when (item.itemId) { 
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show() 
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove",Toast.LENGTH_SHORT).show() 
        } 
        return true 
    }    
    
    override fun onActivityResult(requestCode: Int, resultCode:Int, data: Intent?) { 
        super.onActivityResult(requestCode, resultCode, data) 
            when (requestCode) { 
                1 -> if (resultCode == RESULT_OK) { 
                    val returnedData = data?.getStringExtra("data_return") 
                    Log.d("🌎🌎🌎", "returned data is $returnedData") 
                }
            }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        //_binding = null
        // merely switching activity does not destroy previous
        
        Log.d("▶️▶️▶️onDestroy", "bye bye")

        //val sw4 = _binding?.sw4
        //sw4?.setChecked(false)

    }
}
