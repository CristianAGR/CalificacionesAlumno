package com.example.finalandroid

import Modelo.AlumnosDataSource
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
        val nombre = bundle?.getString("nombre")
        val materia = bundle?.getString("materia")
        val cal1er = bundle?.getDouble("cal1er")
        val cal2do = bundle?.getDouble("cal2do")
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
            try {
                guardar(nombre!!, materia!!, cal1er!!, cal2do!!, cal6!!, cal10!!)
            } catch (ex: Exception) {

            }

            val intent = Intent(this,MainActivity::class.java)
            //intent.putExtra("cal6", txtMonto.text.toString())
            //intent.putExtra("cal10", txtMonto.text.toString())
            startActivity(intent)
        }
    }

    fun guardar(nombre: String, materia: String, cal1er: Double, cal2do: Double, cal6: Double, cal10:Double) {
        val datasource = AlumnosDataSource(this)
        datasource.guardarAlumno(nombre,materia,cal1er,cal2do,cal6,cal10)
    }
}