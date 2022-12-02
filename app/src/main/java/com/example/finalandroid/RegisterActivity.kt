package com.example.finalandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_required_grades.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        logica()
    }

    fun logica() {
        btnCalcular.setOnClickListener {
            val intent = Intent(this,RequiredGradesActivity::class.java)
            //intent.putExtra("cal6", txtMonto.text.toString())
            //intent.putExtra("cal10", txtMonto.text.toString())
            startActivity(intent)
        }
    }
}