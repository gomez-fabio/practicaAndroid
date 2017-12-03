package es.fabiogomez.carta.fragments

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewSwitcher
import es.fabiogomez.carta.JSON_URL
import es.fabiogomez.carta.R
import es.fabiogomez.carta.activities.CheckActivity
import es.fabiogomez.carta.models.Dish
import es.fabiogomez.carta.models.Table
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.json.JSONObject
import java.net.URL
import java.util.*


class DishFragment : Fragment() {

    enum class VIEW_INDEX (val index: Int) {
        LOADING(0),
        DISH(1)
    }

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
    lateinit var viewSwitcher : ViewSwitcher

    var table: Table? = null
        set(value){
            field = value
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
                viewSwitcher.displayedChild = VIEW_INDEX.DISH.index
            } else {
                updateDish()
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

            viewSwitcher = root.findViewById(R.id.view_switcher)
            viewSwitcher.setInAnimation(activity, android.R.anim.fade_in)
            viewSwitcher.setOutAnimation(activity, android.R.anim.fade_out)

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

    private fun updateDish() {
        viewSwitcher.displayedChild = VIEW_INDEX.LOADING.index

        async(UI) {
            val newDish: Deferred<Dish?> = bg {
                downloadDish(table)
            }
            dish = newDish.await()
        }
    }

    // TODO el argumento de entrada lo quito? Hm.
    fun downloadDish (table: Table?) : Dish? {
        try {
            val url = URL(JSON_URL)
            val jsonString = Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next()
            val jsonRoot = JSONObject(jsonString)
            val list = jsonRoot.getJSONArray("dishes")
            val fromJson = list.getJSONObject(0)

            val name = fromJson.getString("name")
            val image = fromJson.getInt("image")
            val price = fromJson.getDouble("price").toFloat()
            val description = fromJson.getString("description")
            val customerCustomization = fromJson.getString("customerCustomization")
            val alergeno_altramuces = fromJson.getInt("alergeno_altramuces")
            val alergeno_apio = fromJson.getInt("alergeno_apio")
            val alergeno_azufre = fromJson.getInt("alergeno_azufre")
            val alergeno_cacahuete = fromJson.getInt("alergeno_cacahuete")
            val alergeno_crustaceo = fromJson.getInt("alergeno_crustaceo")
            val alergeno_frutos = fromJson.getInt("alergeno_frutos")
            val alergeno_gluten = fromJson.getInt("alergeno_gluten")
            val alergeno_huevos = fromJson.getInt("alergeno_huevos")
            val alergeno_lacteo = fromJson.getInt("alergeno_lacteo")
            val alergeno_molusco = fromJson.getInt("alergeno_molusco")
            val alergeno_mostaza = fromJson.getInt("alergeno_mostaza")
            val alergeno_pescado = fromJson.getInt("alergeno_pescado")
            val alergeno_sesamo = fromJson.getInt("alergeno_sesamo")
            val alergeno_soja = fromJson.getInt("alergeno_soja")
            val iconResource = when (image) {
                0 -> R.drawable.f00_burguer
                1 -> R.drawable.f01_pizza
                2 -> R.drawable.f02_nachos
                3 -> R.drawable.f03_beer
                else -> R.drawable.f03_beer
            }

            return Dish( name, iconResource, price, description, customerCustomization,
                    alergeno_altramuces, alergeno_apio, alergeno_azufre, alergeno_cacahuete,
                    alergeno_crustaceo, alergeno_frutos, alergeno_gluten, alergeno_huevos,
                    alergeno_lacteo, alergeno_molusco, alergeno_mostaza, alergeno_pescado,
                    alergeno_sesamo, alergeno_soja )

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return null
    }
}