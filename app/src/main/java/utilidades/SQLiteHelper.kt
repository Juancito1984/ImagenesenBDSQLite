package utilidades

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(cxt: Context) : SQLiteOpenHelper(cxt, BD_NAME, null, BD_VERSION) {

    fun getQuery(sql: String) {
        val database = writableDatabase
        database.execSQL(sql)
    }

    fun insertDato(id: Int, name: String, precio: String, imagen: ByteArray) {
        val database = writableDatabase

        val statement = database.compileStatement(SQL_INSERTAR_DATO)
        statement.clearBindings()

        statement.bindString(1, name)
        statement.bindString(2, precio)
        statement.bindBlob(3, imagen)

        statement.executeInsert()
    }

    fun getData(): Cursor? {
        val database = readableDatabase
        return database.rawQuery(SQL_EXTRAER_DATOS, null)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREAR_TABLA_HELADOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(eliminarTabla(TB_HELADOS))
        onCreate(db)
    }
}