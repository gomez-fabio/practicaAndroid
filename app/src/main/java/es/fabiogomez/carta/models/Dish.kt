package es.fabiogomez.carta.models


import java.io.Serializable

data class Dish (
        var name: String,
        var image: Int, // En el json viene un entero, luego busco en local esa referencia
        var price: Float,
        var allergens: String, // TODO - Esto deberá ser un array de alérgenos, hay que cambiarlo
        var description: String,
        var customerCustomization: String?) : Serializable // Ya se que la voy a usar un fragment así que es necesario Serializable.