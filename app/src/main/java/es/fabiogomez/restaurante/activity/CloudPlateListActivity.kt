package es.fabiogomez.restaurante.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import es.fabiogomez.restaurante.R
import es.fabiogomez.restaurante.adapter.PlatesRecyclerViewAdapter
import es.fabiogomez.restaurante.fragment.PlatesListFragment
import es.fabiogomez.restaurante.model.CloudPlates
import es.fabiogomez.restaurante.model.Plate
import es.fabiogomez.restaurante.model.Tables

class CloudPlateListActivity : AppCompatActivity(), PlatesRecyclerViewAdapter.OnPlateSelectedListener {

    companion object {

        val EXTRA_TABLE_POSITION = "EXTRA_TABLE_POSITION"

        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, CloudPlateListActivity::class.java)
            intent.putExtra(EXTRA_TABLE_POSITION, tableIndex)
            return intent
        }
    }


    var tableIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cloud_plate_list)

        tableIndex = intent.getIntExtra(EXTRA_TABLE_POSITION, 0)


        if (findViewById<View>(R.id.plate_list) != null) {
            val fragment = PlatesListFragment.newInstance()
            fragment.list = CloudPlates.plates
            title =  "Añadiendo platos a la mesa"+" ${tableIndex}"
            fragmentManager.beginTransaction()
                    .replace(R.id.plate_list, fragment)
                    .commit()

        }
    }

    override fun onPlateSelected(plate: Plate?, position: Int) {
/*        Snackbar.make(
            findViewById(android.R.id.content),
            "Plato añadido", 2000)
                .setDuration(2000)
            .show()*/

        // Llamar a detailPlateActivity pasándole el plato, la mesa y un parámetro extra (INT) que indique si edito o añado
        startActivity(DetailPlateActivity.intent(this, tableIndex, position, 0))


        //finish()
    }

    private fun addPlate(plate: Plate?) {
        if (plate != null) {
            var plate = plate.copy()
            Tables[tableIndex].platos.add(plate)
        }
    }

}
