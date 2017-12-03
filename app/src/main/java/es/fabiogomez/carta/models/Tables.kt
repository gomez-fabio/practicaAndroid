package es.fabiogomez.carta.models

import es.fabiogomez.carta.R
import java.io.Serializable


object Tables : Serializable {
    private var tables: List<Table> = listOf(
            Table("Table 1"),
            Table("Table 2"),
            Table("Table 3"),
            Table("Table 4")
    )

    val count
        get() = tables.size

    operator fun get(i: Int) = tables[i]

    fun toArray() = tables.toTypedArray()
}