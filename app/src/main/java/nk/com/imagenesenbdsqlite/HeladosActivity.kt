package nk.com.imagenesenbdsqlite

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_helados.*
import utilidades.*

class HeladosActivity : AppCompatActivity() {
    val items: MutableList<ModelHelado> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helados)

        iniciarVistas()

        try {

            leerBD()
        }catch (e:Exception){
            Log.d(INFORMACION, e.toString())
            alerta(this, e.toString())
        }
    }

    private fun iniciarVistas() {

        rvHelados.setHasFixedSize(true)
        rvHelados.layoutManager = GridLayoutManager(this, 2)
    }

    private fun leerBD() {
        val sqLiteHelper = SQLiteHelper(this)
        val sqLiteDatabase = sqLiteHelper.readableDatabase
        val cursor = sqLiteDatabase.rawQuery(SQL_EXTRAER_DATOS, null)

        if (cursor.moveToFirst()) {
            do {
                items.add(ModelHelado(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getBlob(3)))

            } while (cursor.moveToNext())
        }
        cursor.close()
        sqLiteDatabase.close()


        val adaptador = Adaptador(items)
        rvHelados.adapter = adaptador
    }


}