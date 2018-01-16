package es.fabiogomez.restaurante.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import es.fabiogomez.restaurante.R
import es.fabiogomez.restaurante.adapter.PlatesRecyclerViewAdapter
import es.fabiogomez.restaurante.fragment.PlatesListFragment
import es.fabiogomez.restaurante.model.Plate
import es.fabiogomez.restaurante.model.Tables

class PlateListActivity : AppCompatActivity(), PlatesRecyclerViewAdapter.OnPlateSelectedListener {

    var tableIndex: Int = -1
    val addButton: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.add_plate_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plate_list)

        tableIndex = intent.getIntExtra(TablePlateListActivity.EXTRA_TABLE_INDEX,-1)

        if (findViewById<View>(R.id.plate_list_content) != null) {
            val fragment = PlatesListFragment.newInstance()

            fragment.list = Tables[tableIndex].platos
            fragmentManager.beginTransaction()
                    .replace(R.id.plate_list_content, fragment)
                    .commit()
        }

        addButton.setOnClickListener{
            /// Llamar  Al CloudListActivity (igual que PlateListActivity) pasandole la table position
            startActivity(CloudPlateListActivity.intent(this,tableIndex))
        }
    }

    override fun onResume() {
        super.onResume()
        val total = Tables[tableIndex].calculateCount()
        title =  "Mesa "+" ${tableIndex}" + " - Total: ${total} €"
    }

    override fun onPlateSelected(plate: Plate?, position: Int) {
        startActivity(DetailPlateActivity.intent(this, tableIndex, position, 1))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu) {
            pay()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun pay() {
        var message: String

        val total = Tables[tableIndex].calculateCount()
        message = resources.getString(R.string.total, total.toString())

        when (total) {
            0f -> message = resources.getString(R.string.total_empty)
        }

        AlertDialog.Builder(this)
                .setTitle("Pagar la cuenta")
                .setMessage(message)
                .setPositiveButton("PAGAR", { dialog, which ->
                    dialog.dismiss()
                    Tables[tableIndex].platos.clear()
                    finish()
                })
                .setNegativeButton("CANCELAR", { dialog, which ->
                    dialog.dismiss()
                })
                .show()
    }
}
