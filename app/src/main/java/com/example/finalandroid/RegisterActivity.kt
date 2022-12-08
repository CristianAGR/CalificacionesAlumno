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
         val id = asignarValores()
        // Envia valores a la siguiente activity
        btnCalcular.setOnClickListener {
            if(validaciones()){
               loadActivity(id)
            }
        }
    }

    private fun loadActivity(id: Int?) {
        val intent = Intent(this,RequiredGradesActivity::class.java)
        val nombre = txtNombre.text.toString()
        val materia = txtMateria.text.toString()
        val parcial1 = (txtCal1.text.toString().toDouble())
        val parcial2 = (txtCal2.text.toString().toDouble())
        var calificacion6 = calcular(parcial1,parcial2).get(0)
        var calificacion10 = calcular(parcial1,parcial2).get(1)
        intent.putExtra("nombre", nombre)
        intent.putExtra("materia", materia)
        intent.putExtra("cal1er", parcial1)
        intent.putExtra("cal2do", parcial2)
        intent.putExtra("cal6", calificacion6)
        intent.putExtra("cal10", calificacion10)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun validaciones():Boolean {
        var validado = false
        var validados= 0
        if(txtNombre.text.toString() != "") {
            validados+= 1
        } else txtNombre.error="Ingrese un nombre valido"

        if(txtMateria.text.toString() != "") {
            validados += 1
        } else txtMateria.error="Ingrese una materia valida"

        if(txtCal1.text.toString() != "") {
            validados += 1
        } else txtCal1.error="Ingrese una Calificacion de 1er parcial"

        if(txtCal2.text.toString() != "") {
            validados += 1
        } else txtCal2.error="Ingrese una Calificacion de 2do parcial"

        if(validados == 4) {
            validado = true
        }
        return validado
    }

    private fun asignarValores(): Int? {
        // recibe valores de MainActivity
        val bundle = intent.extras;
        val id = bundle?.getInt("id")
        val nombre = bundle?.getString("nombre")
        val materia = bundle?.getString("materia")
        val cal1er = bundle?.getDouble("cal1er")
        val cal2do = bundle?.getDouble("cal2do")
        txtNombre.setText(nombre)
        txtMateria.setText(materia)
        txtCal1.setText(cal1er.toString())
        txtCal2.setText(cal2do.toString())
        if (txtCal1.text.toString() == "null") {
            txtCal1.setText("")
            txtCal2.setText("")
        }
        return id
    }

    // calcula cuanta calificacion se necesita para pasar la materia con 6 y con 10
    private fun calcular(parcial1: Double, parcial2: Double): ArrayList<Double> {
        val promedio= (parcial1*.2) + (parcial2*.2)
        var calificacion6 = (6 - promedio)/.6
        var calificacion10 = (10 - promedio)/.6
        var calificaciones = ArrayList<Double>()
        calificaciones.add(calificacion6)
        calificaciones.add(calificacion10)
        return calificaciones
    }
}