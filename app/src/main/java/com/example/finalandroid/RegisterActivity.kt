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
            val nombre = txtNombre.text.toString()
            val materia = txtMateria.text.toString()
            val parcial1 = (txtCal1.text.toString().toDouble())*.2
            val parcial2 = (txtCal2.text.toString().toDouble())*.2
            var calificacion6 = calcular(parcial1,parcial2).get(0)
            var calificacion10 = calcular(parcial1,parcial2).get(1)
            intent.putExtra("nombre", nombre)
            intent.putExtra("materia", materia)
            intent.putExtra("cal1er", parcial1)
            intent.putExtra("cal2do", parcial2)
            intent.putExtra("cal6", calificacion6)
            intent.putExtra("cal10", calificacion10)
            startActivity(intent)
        }
    }

    // calcula cuanta calificacion se necesita para pasar la materia con 6 y con 10
    private fun calcular(parcial1: Double, parcial2: Double): ArrayList<Double> {
        val promedio= parcial1 + parcial2
        var calificacion6 = (6 - promedio)/.6
        var calificacion10 = (10 - promedio)/.6
        var calificaciones = ArrayList<Double>()
        calificaciones.add(calificacion6)
        calificaciones.add(calificacion10)
        return calificaciones
    }
}