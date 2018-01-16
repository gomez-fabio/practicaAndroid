package es.fabiogomez.restaurante.model

import java.io.Serializable

data class Plate(val name: String,
                 var allergens: List<Int>? ,
                 var image: String?,
                 var description: String?,
                 var note: String,
                 var price:Float) : Serializable{

    // Constructor de conveniencia
    constructor( name: String,
                 allergens: List<Int>?,
                 image: String?,
                 description: String? ,price: Float) : this(name,allergens,image,description,"",price)
    fun updateNote(newNote: String){
        this.note = newNote
    }
    fun copy() = Plate( name, allergens,image, description, "", price)
}
