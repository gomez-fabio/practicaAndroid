package es.fabiogomez.carta.models


import java.io.Serializable

data class Dish (
        var name: String,
        var image: Int,
        var price: Float,
        var description: String,
        var customerCustomization: String?,
        val alergeno_altramuces : Int,
        val alergeno_apio : Int,
        val alergeno_azufre : Int,
        val alergeno_cacahuete : Int,
        val alergeno_crustaceo : Int,
        val alergeno_frutos : Int,
        val alergeno_gluten : Int,
        val alergeno_huevos : Int,
        val alergeno_lacteo : Int,
        val alergeno_molusco : Int,
        val alergeno_mostaza : Int,
        val alergeno_pescado : Int,
        val alergeno_sesamo : Int,
        val alergeno_soja : Int) : Serializable