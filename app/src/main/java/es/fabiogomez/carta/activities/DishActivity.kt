package es.fabiogomez.carta.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.fabiogomez.carta.R

class DishActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)


//        findViewById<View>(R.id.cancel_dish_button).setOnClickListener { cancelDish() }
//
//        // Tendré que llamar a addDish pasándole el dish que hay que añadir
//        findViewById<View>(R.id.add_dish_button).setOnClickListener { addDish() }
    }




//    private fun cancelDish() {
//        setResult(Activity.RESULT_CANCELED)
//        finish()
//    }
//
//    // Esta función tendrá que recibir el dish que añadir a la table
//    private fun addDish() {
//        // TENDRE QUE RECIBIR LA POSICION DE LA MESA CUANDO LLAMEN A ESTA ACTIVIDAD
////        intent.getIntExtra(TABLE_INDEX)
//        // TENDRE QUE BUSCAR LA TABLE en TABLETS (singleton)
////        val table = Tables.get(TABLE_INDEX)
//        // TENDRE QUE añadir a la table el dish que me pasan
//        //table.addDish(dish)
//
//
//        //TENDRE QUE DEVOLVER LA MISMA MESA QUE ME PASAN
//        val returnIntent = Intent()
////        returnIntent.putExtra(TABLE_INDEX, table)
//        setResult(Activity.RESULT_OK)
//        finish()
//    }


}
