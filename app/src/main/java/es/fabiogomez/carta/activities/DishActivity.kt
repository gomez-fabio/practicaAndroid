package es.fabiogomez.carta.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.fabiogomez.carta.R
import es.fabiogomez.carta.fragments.TableListFragment
import es.fabiogomez.carta.models.Table
import es.fabiogomez.carta.models.Tables

class DishActivity : AppCompatActivity(), TableListFragment.OnTableSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)

        if (fragmentManager.findFragmentById(R.id.table_list_fragment) == null ){
            val fragment = TableListFragment.newInstance(Tables())
            fragmentManager.beginTransaction()
                    .add(R.id.table_list_fragment, fragment)
                    .commit()
        }


//        findViewById<View>(R.id.cancel_dish_button).setOnClickListener { cancelDish() }
//
//        // Tendré que llamar a addDish pasándole el dish que hay que añadir
//        findViewById<View>(R.id.add_dish_button).setOnClickListener { addDish() }
    }

    override fun onTableSelected(table: Table?, position: Int) {
        startActivity(TablePagerActivity.intent(this, position))
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
