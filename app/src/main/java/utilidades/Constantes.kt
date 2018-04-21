package utilidades

import android.content.Context
import android.widget.Toast

//BASE DE DATOS
val BD_NAME = "heladeria.db"
val BD_VERSION = 1

//TABLAS

//Tabla #1 seccion helados
val TB_HELADOS = "helados"


//CAMPOS

//Campos tabla helado
val HELADOS_ID = "id"
val HELADOS_NOMBRE = "nombre"
val HELADOS_PRECIO = "precio"
val HELADOS_IMAGEN = "imagen"


//SENTENCIAS SQL
val SQL_CREAR_TABLA_HELADOS = "CREATE TABLE $TB_HELADOS (" +
        "$HELADOS_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
        "$HELADOS_NOMBRE VARCHAR," +
        "$HELADOS_PRECIO VARCHAR," +
        "$HELADOS_IMAGEN BLOG);"

val SQL_EXTRAER_DATOS = "SELECT * FROM $TB_HELADOS"

val SQL_INSERTAR_DATO = "INSERT INTO $TB_HELADOS VALUES(NULL, ?, ?, ?);"

fun eliminarTabla(nombre_tabla: String): String {
    return "DROP IF EXISTS $nombre_tabla"
}

val REQUEST_CODE_GALLERY = 999

fun alerta(cxt: Context, mensaje: String) {
    Toast.makeText(cxt, mensaje, Toast.LENGTH_LONG).show()
}


val INFORMACION = "INFORMACION"