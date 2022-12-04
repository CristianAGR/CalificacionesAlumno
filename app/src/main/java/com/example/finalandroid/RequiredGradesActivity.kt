package com.example.finalandroid

import Modelo.AlumnosDataSource
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
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
        val id = bundle?.getInt("id")
        val txtCali6 = findViewById<TextView>(R.id.txtCal6)
        val txtCali10 = findViewById<TextView>(R.id.txtCal10)
        txtCali6.text = cal6.toString()
        txtCali10.text = cal10.toString()

        if(txtCali10.text.toString().toDouble() <= 10.0) {
            lblResultado.text = "Aún se puede"
        } else {
            lblResultado.text = "Ya no se logro"
        }

        // mostrar boton eliminar si recibe un id
        showButton(id!!)

        // listeners de botones
        btnGuardar.setOnClickListener {
            seleccionarFuncion(nombre!!, materia!!, cal1er!!, cal2do!!, cal6!!, cal10!!, id!!)
        }
        btnEliminar.setOnClickListener {
            eliminar(id!!)
        }
    }

    private fun showButton(id: Int) {
        btnEliminar.isVisible = id != 0
    }
    private fun eliminar(id:Int) {
        val datasource = AlumnosDataSource(this)
        try {
            // eliminar
            datasource.eliminarAlumno(id)
            val toast = Toast.makeText(applicationContext, "Se elimino correctamente", Toast.LENGTH_SHORT)
            toast.show()
        } catch (ex: Exception) {
            val toast = Toast.makeText(applicationContext, "Fallo al eliminar"+ ex, Toast.LENGTH_SHORT)
            toast.show()
        }

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }

    private fun seleccionarFuncion(nombre: String, materia: String, cal1er: Double, cal2do: Double, cal6: Double, cal10:Double, id:Int) {
        val datasource = AlumnosDataSource(this)
        try {
            if (id != 0) {
                // modificar
                datasource.modificarAlumno(nombre,materia,cal1er,cal2do,cal6,cal10, id)
                val toast = Toast.makeText(applicationContext, "Se modificó correctamente", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                // guardar
                datasource.guardarAlumno(nombre,materia,cal1er,cal2do,cal6,cal10)
                val toast = Toast.makeText(applicationContext, "Se guardo correctamente", Toast.LENGTH_SHORT)
                toast.show()
            }

        } catch (ex: Exception) {
            val toast = Toast.makeText(applicationContext, "Fallo al guardar/modificar"+ ex, Toast.LENGTH_SHORT)
            toast.show()
        }

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
