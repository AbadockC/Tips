package com.abadock.tips

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.abadock.tips.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val buttonCalcular : Button = binding.button

        buttonCalcular.setOnClickListener{

            if (binding.editTextNumber.text.toString().isEmpty()) {
                val text = "Falta valor para x"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(this, text, duration) // in Activity
                toast.show()
            }
            else {

                val radioButton: RadioButton = findViewById(binding.tipOptions.checkedRadioButtonId)

                val selectedCalc: Double = when (radioButton.text) {
                    "Perfecte (20%)" -> 1.2
                    "Bona (18%)" -> 1.8
                    "OK (15%)" -> 1.5
                    else -> 1.0
                }

                val number : Double = binding.editTextNumber.text.toString().toDouble() * selectedCalc

                if (binding.switch1.isChecked) {

                    val outuput : String = "Total: " + Math.round(number)
                    binding.textView2.text = outuput

                } else {
                    val outuput : String = "Total: " + Math.round(number * 100.0) / 100.0
                    binding.textView2.text = outuput
                }
            }
        }
    }
}