package es.fabiogomez.restaurante.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


import es.fabiogomez.restaurante.R
import es.fabiogomez.restaurante.model.CloudPlates
import es.fabiogomez.restaurante.model.Plate
import es.fabiogomez.restaurante.model.Tables
import com.squareup.picasso.Picasso
import android.R.drawable.edit_text
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class PlateDetailFragment : Fragment() {
    companion object {
        val EXTRA_TABLE_POSITION = "EXTRA_TABLE_POSITION"
        val EXTRA_PLATE_POSITION = "EXTRA_PLATE_POSITION"
        val EXTRA_DETAIL_MODE    = "EXTRA_DETAIL_MODE"

        fun newInstance(tablePosition: Int,plateNumberList: Int, mode: Int): PlateDetailFragment {
            val arguments = Bundle()
            arguments.putInt(EXTRA_TABLE_POSITION, tablePosition)
            arguments.putInt(EXTRA_PLATE_POSITION,plateNumberList)
            arguments.putInt(EXTRA_DETAIL_MODE, mode)
            val fragment = PlateDetailFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
    lateinit var plate: Plate
    lateinit var root: View
    lateinit var name: TextView
    lateinit var imageView: ImageView
    lateinit var description: TextView
    lateinit var price: TextView
    lateinit var note: EditText
    var tablePosition: Int = -1
    var platePosition: Int = -1
    var mode: Int = -1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (inflater != null) {
            platePosition = arguments.getInt(EXTRA_PLATE_POSITION)
            tablePosition = arguments.getInt(EXTRA_TABLE_POSITION)
            mode = arguments.getInt(EXTRA_DETAIL_MODE)

            setDetailViews(inflater, container)
            when(mode){
                0->{
                    plate = CloudPlates[platePosition]
                    root.findViewById<Button>(R.id.add_btn).setText(resources.getString(R.string.add))
                    root.findViewById<Button>(R.id.cancel_btn).setText(resources.getString(R.string.cancel))
                    root.findViewById<Button>(R.id.cancel_btn).setOnClickListener { cancel() }
                }
                1->{
                    plate = Tables[tablePosition].platos[platePosition]
                    root.findViewById<Button>(R.id.add_btn).setText(resources.getString(R.string.edit))
                    root.findViewById<Button>(R.id.cancel_btn).setText(resources.getString(R.string.delete))
                    root.findViewById<Button>(R.id.cancel_btn).setOnClickListener { delete()}
                }
            }
            root.findViewById<Button>(R.id.add_btn).setOnClickListener { add() }
            Picasso.with(activity).
                    load(plate.image).
                    fit().
                    into(imageView);
            description.text = plate.description
            price.text = plate.price.toString() + " €"
            name.text = plate.name
            note.setText(plate.note)
        }
        return root
    }
    private fun setDetailViews(inflater: LayoutInflater, container: ViewGroup?) {
        root = inflater.inflate(R.layout.fragment_detail, container, false)
        name = root.findViewById<TextView>(R.id.plate_name_detail)
        imageView = root.findViewById<ImageView>(R.id.plate_detail_photo)
        description = root.findViewById<TextView>(R.id.plate_detail_description)
        price = root.findViewById<TextView>(R.id.plate_detail_price)
        note = root.findViewById<EditText>(R.id.plate_note)
    }
    private fun cancel() {
        activity.finish()
    }
    private fun delete(){
        Tables[tablePosition].platos.removeAt(platePosition)
        Toast.makeText(root.context, resources.getString(R.string.deleted), Toast.LENGTH_LONG).show()
        activity.finish()
    }
    private fun add(){
        when(mode) {
            0 -> {
                var plateTosave = plate.copy()
                plateTosave.updateNote(note.text.toString())
                Tables[tablePosition].platos.add(plateTosave)
                Toast.makeText(root.context, resources.getString(R.string.added), Toast.LENGTH_LONG).show()
            }
            1 -> {
                plate.updateNote(note.text.toString())
                Tables[tablePosition].platos.get(platePosition).updateNote(note.text.toString())
                Toast.makeText(root.context, resources.getString(R.string.edited), Toast.LENGTH_LONG).show()
            }
        }
        activity.finish()
    }

}
