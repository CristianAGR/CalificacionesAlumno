package Modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import kotlin.Exception

class AlumnosDataSource (context: Context){
    private val openHelper: BdOpenHelper = BdOpenHelper(context)
    private val database: SQLiteDatabase
    object ColumnAlumnos {
        var ID_ALUMNO = BaseColumns._ID
        var NOMBRE = "nombre"
        var MATERIA = "materia"
        var CAL1ER = "cal1er"
        var CAL2DO = "cal2do"
        var CAL6 = "cal6"
        var CAL10 = "cal10"
    }

    init {
        database = openHelper.writableDatabase
    }

    fun consultarAlumnos(): Cursor {
        return  database.rawQuery("select _id,nombre,materia,cal1er,cal2do,cal6,cal10 from $ALUMNOS_TABLE_NAME", null)
    }

    fun buscarNombre(nombre: String): Cursor {
        return  database.rawQuery("select _id,nombre,materia,cal1er,cal2do,cal6,cal10 from $ALUMNOS_TABLE_NAME where nombre='$nombre'", null)
    }

    fun guardarAlumno(nombre: String, materia: String, cal1er: Double, cal2do: Double, cal6: Double, cal10: Double){
        val values = ContentValues()
        values.put(ColumnAlumnos.NOMBRE, nombre)
        values.put(ColumnAlumnos.MATERIA, materia)
        values.put(ColumnAlumnos.CAL1ER, cal1er)
        values.put(ColumnAlumnos.CAL2DO, cal2do)
        values.put(ColumnAlumnos.CAL6, cal6)
        values.put(ColumnAlumnos.CAL10, cal10)
        database.insert(ALUMNOS_TABLE_NAME, null, values)
    }

    fun modificarAlumno(nombre: String, materia: String, cal1er: Double, cal2do: Double, cal6: Double, cal10: Double, IdAlumno: Int){
        val values = ContentValues()
        values.put(ColumnAlumnos.NOMBRE, nombre)
        values.put(ColumnAlumnos.MATERIA, materia)
        values.put(ColumnAlumnos.CAL1ER, cal1er)
        values.put(ColumnAlumnos.CAL2DO, cal2do)
        values.put(ColumnAlumnos.CAL6, cal6)
        values.put(ColumnAlumnos.CAL10, cal10)
        val a = arrayOf("" + IdAlumno)
        database.update(ALUMNOS_TABLE_NAME, values, "_id=?", a)
    }

    fun eliminarAlumno(IdAlumno: Int):Boolean {
        val whereArgs = arrayOf("" + IdAlumno)
        try{
            database.delete(ALUMNOS_TABLE_NAME, "_id=?", whereArgs)
            return true
        }
        catch (ex: Exception){
            return false
        }
        finally {
            database.close()
        }
    }

    companion object {
        val ALUMNOS_TABLE_NAME = "Alumnos"
        val STRING_TYPE = "text"
        val INT_TYPE = "integer"
        val DECIMAL_TYPE = "decimal"
        val CREATE_ALUMNOS_SCRIPT = (
                "create table " + ALUMNOS_TABLE_NAME + "(" +
                        ColumnAlumnos.ID_ALUMNO + " " + INT_TYPE + " primary key autoincrement," +
                        ColumnAlumnos.NOMBRE + " " + STRING_TYPE + " not null," +
                        ColumnAlumnos.MATERIA + " " + STRING_TYPE + " not null," +
                        ColumnAlumnos.CAL1ER + " " + DECIMAL_TYPE + " not null," +
                        ColumnAlumnos.CAL2DO + " " + DECIMAL_TYPE + " not null," +
                        ColumnAlumnos.CAL6 + " " + DECIMAL_TYPE + " not null," +
                        ColumnAlumnos.CAL10 + " " + DECIMAL_TYPE + " not null)")
        val INSERT_ALUMNOS_SCRIPT = (
                "insert into " + ALUMNOS_TABLE_NAME + " values " +
                        "(null,'Cristian','Ingles',6.5,8.2,3,12)," +
                        "(null,'Ricardo','Proyectos',7.4,7,3,12)," +
                        "(null,'Carlos','Redes',8,7.9,3,12)")
    }
}