package es.fabiogomez.restaurante.model

interface DownloadMenuInteractor {
    fun execute():ArrayList<Plate>?
}