package es.fabiogomez.restaurante.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import es.fabiogomez.restaurante.R
import es.fabiogomez.restaurante.fragment.PlatesListFragment
import es.fabiogomez.restaurante.fragment.TablesListFragment
import es.fabiogomez.restaurante.model.Table
import es.fabiogomez.restaurante.model.Tables
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem

class TablePlateListActivity : AppCompatActivity(), TablesListFragment.OnTableSelectedListener {
    private val TABLE_LIST_TAG = "TableList"
    companion object {

        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"


        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, PlateListActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }
    }
    var tablet = false
    var tableIndex = -1

    val addButton: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.add_plate_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_plate_list)

        tablet = findViewById<View>(R.id.plate_list_content) != null


        if (findViewById<View>(R.id.main_content) != null) {
            if (fragmentManager.findFragmentById(R.id.main_content) == null) {
                val fragment = TablesListFragment.newInstance()
                fragmentManager.beginTransaction()
                        .replace(R.id.main_content, fragment, TABLE_LIST_TAG)
                        .commit()

            }
        }


        if (tablet) {
            val fragment = PlatesListFragment.newInstance()
            fragment.list = Tables[0].platos
            fragmentManager.beginTransaction()
                    .replace(R.id.plate_list_content, fragment)
                    .commit()
        }
    }

    override fun onResume() {
        super.onResume()

        if (tableIndex == -1) {
            title = "Restaurante"
        } else {
            val total = Tables[tableIndex].calculateCount()
            title =  "Mesa "+" ${tableIndex}" + " - Total: ${total} €"
        }

    }

    override fun onTableSelected(table: Table?, position: Int) {
        if (!tablet) {
            //Voy a la segunda vista
            startActivity(intent(this, position))
        }
        else
        {
            val total = Tables[position].calculateCount()
            title =  "Mesa "+" ${position}" + " - Total: ${total} €"
            tableIndex = position

            if (findViewById<View>(R.id.plate_list_content) != null) {
                val fragment = PlatesListFragment.newInstance()

                fragment.list = Tables[position].platos
                fragmentManager.beginTransaction()
                        .replace(R.id.plate_list_content, fragment)
                        .commit()
            }

            addButton.setOnClickListener{
                startActivity(CloudPlateListActivity.intent(this,position))
            }
        }
    }


    // Menu y pagar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        if (tablet) {
            menuInflater.inflate(R.menu.menu, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu && tableIndex != -1) {
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
                    startActivity(intent)
                })
                .setNegativeButton("CANCELAR", { dialog, which ->
                    dialog.dismiss()
                })
                .show()
    }


}
