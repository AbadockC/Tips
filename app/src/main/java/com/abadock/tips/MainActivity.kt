package com.abadock.tips

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
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
        val textoOutput : TextView = binding.textView2

        buttonCalcular.setOnClickListener{


            val inputCoste : String = binding.editTextNumber.text.toString()

            if (inputCoste.isEmpty()) {
                val text = "DALE VALOR A COSTE DLE SERVICIO IMBECIL"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(this, text, duration) // in Activity
                toast.show()
            }
            else {

                val radioGroup: RadioGroup = binding.tipOptions
                val selectedId: Int = radioGroup.checkedRadioButtonId
                val radioButton: RadioButton = findViewById(selectedId)

                val roundCheck: Switch = binding.switch1

                val selectedCalc: Double = when (radioButton.text) {
                    "Perfecte (20%)" -> 1.2
                    "Bona (18%)" -> 1.8
                    "OK (15%)" -> 1.5
                    else -> 1.0
                }

                val number : Double = inputCoste.toDouble() * selectedCalc

                if (roundCheck.isChecked) {

                    val outuput : String = "Total: " + Math.round(number)
                    textoOutput.text = outuput

                } else {
                    val outuput : String = "Total: " + Math.round(number * 10.0) / 10.0
                    textoOutput.text = outuput
                }
            }

        }
    }
}