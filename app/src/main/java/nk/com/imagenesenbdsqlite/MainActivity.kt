package nk.com.imagenesenbdsqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import utilidades.SQLiteHelper
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import utilidades.*
import utilidades.alerta
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {


    val sqLiteHelper = SQLiteHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCargarImagen.setOnClickListener {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_GALLERY)
        }

        btnAgregarABD.setOnClickListener {
            try {
                sqLiteHelper.insertDato(
                        0,
                        etNombre.text.toString(),
                        eTPrecio.text.toString(),
                        imagenAByte(iVImagen)
                )

                alerta(this, "Agregado correctamente")
            } catch (e: Exception) {
                Log.d(INFORMACION, e.toString())
            }

        }
    }

    private fun imagenAByte(iVImagen: ImageView): ByteArray {
        val bitmap = (iVImagen.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

        return stream.toByteArray()

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.size >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, REQUEST_CODE_GALLERY)
            } else {
                alerta(this, "No tienes permiso para acceder a la localizacion")
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data

            try {
                val inputStream = contentResolver.openInputStream(uri)
                val bitmapFactory = BitmapFactory.decodeStream(inputStream)
                iVImagen.setImageBitmap(bitmapFactory)
            } catch (e: FileNotFoundException) {
                Log.d(INFORMACION, e.message)
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
