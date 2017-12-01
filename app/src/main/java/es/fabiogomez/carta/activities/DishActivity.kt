package es.fabiogomez.carta.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import es.fabiogomez.carta.R
import es.fabiogomez.carta.models.Dish

class DishActivity : AppCompatActivity(){

    // TODO - Limpieza de TAG
    val TAG = DishActivity::class.java.canonicalName

    var dish: Dish? = null
        set(value){
            val dishName = findViewById<TextView>(R.id.dish_name)
            val dishPrice = findViewById<TextView>(R.id.dish_price)
            val dishDescription = findViewById<TextView>(R.id.dish_description)
            val dishImage = findViewById<ImageView>(R.id.dish_image)
            val dishCustom = findViewById<TextView>(R.id.dish_customization)

            if (value != null) {
                dishName.text = value.name
                dishPrice.text = getString(R.string.dish_price, value.price)
                dishDescription.text = value.description
                dishImage.setImageResource(value.image)
                dishCustom.text = value.customerCustomization
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)

        dish = Dish("Hamburguesa \"Pecadorl\" Gourmet BLACK ANGUS",
                            R.drawable.f00_burguer,
                         10.50f,
                      "Lacteos, Sesamo",
                     "300 Gr de carne Angus, servida con patatas gratinadas al horno con queso",
                "")


        findViewById<View>(R.id.cancel_dish_button).setOnClickListener { cancelDish() }

        findViewById<View>(R.id.add_dish_button).setOnClickListener { addDish() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.check,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_pay_check) {
            val intent = Intent(this, CheckActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cancelDish() {
        finish()
    }

    private fun addDish() {
        finish()
    }


}
