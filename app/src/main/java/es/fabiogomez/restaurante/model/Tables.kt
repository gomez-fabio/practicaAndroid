package es.fabiogomez.restaurante.model

import es.fabiogomez.restaurante.NUMBER_OFF_TABLES
import java.io.Serializable
import java.util.prefs.Preferences


object Tables : Serializable {


    var tables: ArrayList<Table> = generateTablesByPreferences()

    val count
        get() = tables.size

    operator fun get(i: Int) = tables[i]

    fun toArray() = tables.toTypedArray()

    fun generateTablesByPreferences():ArrayList<Table>{

        var generatedTables = arrayListOf<Table>()
        for(index in 0 until NUMBER_OFF_TABLES){
            generatedTables.add(Table("Mesa ${index}"))
        }
        return generatedTables
    }
}
