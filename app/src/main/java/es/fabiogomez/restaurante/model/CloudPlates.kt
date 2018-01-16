package es.fabiogomez.restaurante.model

import java.io.Serializable

object CloudPlates :Serializable {

    public var plates = arrayListOf<Plate>()

    operator fun get(i: Int) = plates[i]


}