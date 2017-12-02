package es.fabiogomez.carta.models

import java.io.Serializable

data class Table(
    var name: String,
    var dish: Dish) : Serializable {
}