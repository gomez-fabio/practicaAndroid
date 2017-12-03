package es.fabiogomez.carta.models

object Dishes {
    private var dishes: MutableList<Dish> = arrayListOf()

    val count
        get() = dishes.size

    operator fun get(i: Int) = dishes[i]
    //fun getDishes(): List<Dish> = dishes

    fun addDish(name: String,
                image: Int,
                price: Float,
                description: String,
                customerCustomization: String?,
                alergeno_altramuces : Int,
                alergeno_apio : Int,
                alergeno_azufre : Int,
                alergeno_cacahuete : Int,
                alergeno_crustaceo : Int,
                alergeno_frutos : Int,
                alergeno_gluten : Int,
                alergeno_huevos : Int,
                alergeno_lacteo : Int,
                alergeno_molusco : Int,
                alergeno_mostaza : Int,
                alergeno_pescado : Int,
                alergeno_sesamo : Int,
                alergeno_soja : Int) {

        dishes.add(Dish(name,
                image,
                price,
                description,
                customerCustomization,
                alergeno_altramuces,
                alergeno_apio,
                alergeno_azufre,
                alergeno_cacahuete,
                alergeno_crustaceo,
                alergeno_frutos,
                alergeno_gluten,
                alergeno_huevos,
                alergeno_lacteo,
                alergeno_molusco,
                alergeno_mostaza,
                alergeno_pescado,
                alergeno_sesamo,
                alergeno_soja))
    }

}