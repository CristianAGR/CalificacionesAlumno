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
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.example.finalandroid.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_alumno.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val registros = ArrayList<Alumno>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        btnSearch.setOnClickListener{
            if(txtSearch.text.equals("") || txtSearch.text.isEmpty()) {
                llenarInformacion()
            } else {
                buscarAlumno()
            }
            hideKeyboard()
        }


    }
    // esconder el teclado al dar click en viewRoot
    private fun hideKeyboard(){
        val imn: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(binding.viewRoot.windowToken,0)
    }

    private fun buscarAlumno() {
        val datasource = AlumnosDataSource(this)
        registros.clear()
        val cursor = datasource.buscarNombre(txtSearch.text.toString())

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
        obtenerInfo()

    }

    // configurar recyclerView
    private fun llenarInformacion() {
        val datasource = AlumnosDataSource(this)

        registros.clear()
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
        obtenerInfo()
    }

    private fun obtenerInfo() {
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