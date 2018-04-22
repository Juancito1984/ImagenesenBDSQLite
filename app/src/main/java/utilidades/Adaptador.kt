package utilidades

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.items_helados.view.*
import nk.com.imagenesenbdsqlite.R

class Adaptador(val items:MutableList<ModelHelado>):RecyclerView.Adapter<Adaptador.Reciclador>() {
    var onClick : View.OnClickListener?= null

    fun setOnCLickListener(onClickListener: View.OnClickListener){
        this.onClick = onClickListener
    }

    class Reciclador(view: View):RecyclerView.ViewHolder(view) {
        val imagen = view.ivImagen
        val nombre = view.txtNombre
        val precio = view.txtPrecio
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Reciclador {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.items_helados, parent, false)
        vista.setOnClickListener(onClick)
        return Reciclador(vista)
    }

    override fun onBindViewHolder(holder: Reciclador, position: Int) {
        holder.imagen
        holder.nombre.text = items[position].nombre
        holder.precio.text = items[position].precio

        val byteArray = items[position].imagen
        val bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray!!.size)
        holder.imagen.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}