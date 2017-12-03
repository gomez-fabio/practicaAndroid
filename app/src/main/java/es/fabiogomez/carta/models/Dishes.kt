package es.fabiogomez.carta.models

object Dishes {
    private var dishes: MutableList<Dish> = mutableListOf()

    val count
        get() = dishes.size

    operator fun get(i: Int) = dishes[i]
    fun add(dish: Dish) = dishes.add(dish)
    fun getDishes(): List<Dish> = dishes

    //fun toArray() = tables.toTypedArray()

}