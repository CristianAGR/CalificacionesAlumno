package com.example.finalandroid

import Entidades.Alumno
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.NonNull
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_required_grades.*
import kotlinx.android.synthetic.main.item_alumno.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logica()
    }

    fun logica() {
        btnAdd.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            //intent.putExtra("origen", txtMonto.text.toString())
            startActivity(intent)
        }
    }

    internal class AdaptadorAlumnos(context: Context, datos:List<Alumno>):
        ArrayAdapter<Alumno>(context, R.layout.item_alumno, datos) {
        var _datos:List<Alumno>

        init {
            _datos = datos
        }

        @NonNull
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_alumno, parent, false)
            val currentEntity = getItem(position)

            if (currentEntity != null) {
                inflater.lblNombre.text = currentEntity.NOMBRE
                inflater.lblMateria.text = currentEntity.MATERIA
            }
            return inflater
        }
    }
}