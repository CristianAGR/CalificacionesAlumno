package com.example.finalandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_required_grades.*

class RequiredGradesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_required_grades)
        logica()
    }

    fun logica() {
        // recibe valores de MainActivity
        val bundle = intent.extras;
        val cal6 = bundle?.getDouble("cal6")
        val cal10 = bundle?.getDouble("cal10")
        val txtCali6 = findViewById<TextView>(R.id.txtCal6)
        val txtCali10 = findViewById<TextView>(R.id.txtCal10)
        txtCali6.text = cal6.toString()
        txtCali10.text = cal10.toString()
        if(txtCali10.text.toString().toDouble() <= 10.0) {
            lblResultado.text = "Aún se puede"
        } else {
            lblResultado.text = "Ya no se logro"
        }



        btnGuardar.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            //intent.putExtra("cal6", txtMonto.text.toString())
            //intent.putExtra("cal10", txtMonto.text.toString())
            startActivity(intent)
        }
    }
}