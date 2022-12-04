package com.example.finalandroid

import Entidades.Alumno
import Modelo.AlumnosDataSource
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_alumno.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logica()
        llenarInformacion()
    }

    fun logica() {
        // realizar una accion en dar buscar

        btnAdd.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            //intent.putExtra("origen", txtMonto.text.toString())
            startActivity(intent)
        }

    }
    // configurar recyclerView
    private fun llenarInformacion() {
        val datasource = AlumnosDataSource(this)

        val registros = ArrayList<Alumno>()
        //Se esta llamando al método para traernos toda la información de la BD
        val cursor = datasource.consultarAlumnos()

        while(cursor.moveToNext()){
            val columnas =  Alumno(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getDouble(3),
                cursor.getDouble(4),
                cursor.getDouble(5),
                cursor.getDouble(6),
            )
            registros.add(columnas)
        }

        val adaptador = AdaptadorAlumnos(this, registros)

        lvAlumnos.adapter = adaptador
        lvAlumnos.setOnItemClickListener{ parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Alumno

            val intent = Intent(this, RegisterActivity::class.java)

            intent.putExtra("id", item.ID_ALUMNO)
            intent.putExtra("nombre", item.NOMBRE)
            intent.putExtra("materia", item.MATERIA)
            intent.putExtra("cal1er", item.CAL1ER)
            intent.putExtra("cal2do", item.CAL2DO)
            intent.putExtra("cal6", item.CAL6)
            intent.putExtra("cal10", item.CAL10)

            startActivity(intent)

        }
    }


    internal class AdaptadorAlumnos(context: Context, datos:List<Alumno>):
        ArrayAdapter<Alumno>(context, R.layout.item_alumno, datos) {
        var _datos:List<Alumno>

        init {
            _datos = datos
        }

        // cargar el item de cada registro de SQLite
        @NonNull
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_alumno, parent, false)
            val currentEntity = getItem(position)

            if (currentEntity != null) {
                inflater.txtNombre.text = currentEntity.NOMBRE
                inflater.txtMateria.text = currentEntity.MATERIA
                // Carga las imagenes de acuerdo al id del alumno
                val url = "https://picsum.photos/300/200?image=${currentEntity.ID_ALUMNO}"
                Glide.with(this.context)
                    .load(url)
                    .thumbnail(Glide.with(this.context).load(R.drawable.fotos))
                    .into(inflater.ivAlumno)
            }
            return inflater
        }
    }
}