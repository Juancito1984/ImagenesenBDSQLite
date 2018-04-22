package utilidades

class ModelHelado {
    var id = 0
    var nombre = ""
    var precio = ""
    var imagen:ByteArray ?=null

    constructor()
    constructor(id: Int, nombre: String, precio: String, imagen: ByteArray?) {
        this.id = id
        this.nombre = nombre
        this.precio = precio
        this.imagen = imagen
    }


}