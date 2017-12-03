package es.fabiogomez.carta.models

import es.fabiogomez.carta.R
import java.io.Serializable


object Tables : Serializable {
    private var tables: List<Table> = listOf(
            Table("Table 1",
                    Dish("Hamburguesa \"Pecadorl\" Gourmet BLACK ANGUS",
                            R.drawable.f00_burguer,
                            10.50f,
                            "Lacteos, Sesamo",
                            "300 Gr de carne Angus, servida con patatas gratinadas al horno con queso",
                            "")
            ),
            Table("Table 2",
                    Dish("Pizza de la casa \"de la pradera\"",
                            R.drawable.f01_pizza,
                            12.00f,
                            "Lacteos, Sesamo",
                            "Mozzarella, champiñones, pimiento verde, aceitunas negras y salsa de tomate.",
                            "")
            ),
            Table("Table 3",
                    Dish("Nachos \"de bonanza\"",
                            R.drawable.f02_nachos,
                            6.40f,
                            "Lacteos, Sesamo",
                            "Triángulos de maíz cubiertos de delicionso queso, frijoles, guacamole, tomate, aceitunas negras, salsa agria y salsa \"de bonanza\"",
                            "")
            ),
            Table("Table 4",
                    Dish("Cerveza Weihenstephan Vitus \"te das cuen\"",
                            R.drawable.f03_beer,
                            2.65f,
                            "Lacteos, Sesamo",
                            "De trigo, de sabor intenso, por la gloria de mi madre!, doblemente malteada, de alta fermentación, no filtrada y con una segunda fermentación en botella",
                            "")
            )
    )

    val count: Int
        get() = tables.size

    operator fun get(i: Int) = tables[i]

    fun toArray() = tables.toTypedArray()
}