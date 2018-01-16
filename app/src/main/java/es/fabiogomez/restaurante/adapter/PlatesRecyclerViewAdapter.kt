package es.fabiogomez.restaurante.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import es.fabiogomez.restaurante.R
import es.fabiogomez.restaurante.model.Plate
import com.squareup.picasso.Picasso


class PlatesRecyclerViewAdapter(val plates: List<Plate>, val listener:OnPlateSelectedListener?): RecyclerView.Adapter<PlatesRecyclerViewAdapter.PlateListViewHolder>(){

    private var onPlateSelectedListener: PlatesRecyclerViewAdapter.OnPlateSelectedListener? = null

    override fun getItemCount() = plates.size


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlateListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.plate_item, parent, false)
        onPlateSelectedListener = listener
        return PlateListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlateListViewHolder?, position: Int) {
        holder?.bindPlate(plates[position], position)
    }

    inner class PlateListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val plateName = itemView.findViewById<TextView>(R.id.plate_name)
        val plateImage = itemView.findViewById<ImageView>(R.id.plate_image)
        val description = itemView.findViewById<TextView>(R.id.plate_description)
        val price = itemView.findViewById<TextView>(R.id.price_text_view)
        val linear = itemView.findViewById<LinearLayout>(R.id.aller_content)
        val note = itemView.findViewById<TextView>(R.id.notes_text_View)


        fun bindPlate(plate: Plate, position: Int){
            // Actualizo la vista con el modelo
            // Accedemos al contexto
            val context = itemView.context
            plateName.text = plate.name
            description.text = plate.description
            price.text = "${plate.price} Euros"
            note.text = plate.note
            Picasso.with(context).
                    load(plate.image).
                    fit().
                    into(plateImage);
            plate.allergens?.let{
                for(i in 0..it.size-1){
                    val lineari = LinearLayout(context)
                    lineari.layoutParams = ViewGroup.LayoutParams(50,50)
                    val view = ImageView(context)
                    view.setImageResource(getAllergenIcon(it.get(i)))
                    lineari.addView(view)
                    linear.addView(lineari)
                }

            }
            itemView.setOnClickListener {
                onPlateSelectedListener?.onPlateSelected(plate,position)
            }
        }

    }

    fun getAllergenIcon(type: Int): Int{
        val resource =  when (type) {
            0  -> R.drawable.a00_alergeno_altramuces
            1  -> R.drawable.a01_alergeno_apio
            2  -> R.drawable.a02_alergeno_azufre
            3  -> R.drawable.a03_alergeno_cacahuete
            4  -> R.drawable.a04_alergeno_crustaceo
            5  -> R.drawable.a05_alergeno_frutos
            6  -> R.drawable.a06_alergeno_gluten
            7  -> R.drawable.a07_alergeno_huevos
            8  -> R.drawable.a08_alergeno_lacteo
            9  -> R.drawable.a09_alergeno_molusco
            10 -> R.drawable.a10_alergeno_mostaza
            11 -> R.drawable.a11_alergeno_pescado
            12 -> R.drawable.a12_alergeno_sesamo
            13 -> R.drawable.a13_alergeno_soja
            else -> R.drawable.a14_nada
        }
        return resource
    }
    interface OnPlateSelectedListener {
        fun onPlateSelected(plate: Plate?, position: Int)
    }
}
