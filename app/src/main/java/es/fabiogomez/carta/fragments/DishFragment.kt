package es.fabiogomez.carta.fragments

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import es.fabiogomez.carta.R
import es.fabiogomez.carta.activities.CheckActivity
import es.fabiogomez.carta.models.Dish
import es.fabiogomez.carta.models.Table


class DishFragment : Fragment() {

    companion object {
        val REQUEST_CHECKOUT = 1
        private val ARG_TABLE = "ARG_TABLE"

        fun newInstance(table: Table) : DishFragment {
            val fragment = DishFragment()
            val arguments = Bundle()

            arguments.putSerializable(ARG_TABLE, table)
            fragment.arguments = arguments

            return fragment
        }
    }

    lateinit var root : View

    var table: Table? = null
        set(value){
            if (value != null) {
                root.findViewById<TextView>(R.id.table).setText(value?.name)
                dish = value?.dish
            }
        }
    var dish: Dish? = null
        set(value){
            val dishName = root.findViewById<TextView>(R.id.dish_name)
            val dishPrice = root.findViewById<TextView>(R.id.dish_price)
            val dishDescription = root.findViewById<TextView>(R.id.dish_description)
            val dishImage = root.findViewById<ImageView>(R.id.dish_image)

            if (value != null) {
                dishName.text = value.name
                dishPrice.text = getString(R.string.dish_price, value.price)
                dishDescription.text = value.description
                dishImage.setImageResource(value.image)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_dish, container, false)
            if (arguments != null) {
                table = arguments.getSerializable(ARG_TABLE) as? Table
            }
        }

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.check,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_pay_check) {
            val intent = CheckActivity.intent(activity) // Llamo al método factoria de CheckActivity
            startActivityForResult(intent, REQUEST_CHECKOUT)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CHECKOUT){
            if (resultCode == Activity.RESULT_OK) {
                // Ckeckout, tendremos que limpiar la mesa
            } else {
                // Cancel Ckeckout, no hacemos nada
            }
        }
    }
}