package es.fabiogomez.restaurante.model

import java.io.Serializable

data class Table(var name: String, var platos: ArrayList<Plate>) : Serializable {

    constructor(name: String) : this(name, arrayListOf<Plate>())

    override fun toString() = name

    fun calculateCount() : Float {
        var total = 0f
        if (platos.size > 0){
            platos.map { total += it.price }
        }
        return total
    }
}
