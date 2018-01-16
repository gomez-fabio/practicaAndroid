package es.fabiogomez.restaurante.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import es.fabiogomez.restaurante.R
import es.fabiogomez.restaurante.fragment.TablesListFragment
import es.fabiogomez.restaurante.model.Table

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_plate_list)
        if (findViewById<View>(R.id.main_content) != null) {
            if (fragmentManager.findFragmentById(R.id.main_content) == null) {
                val fragment = TablesListFragment.newInstance()
                fragmentManager.beginTransaction()
                        .replace(R.id.main_content, fragment, TABLE_LIST_TAG)
                        .commit()

            }
        }
    }

    override fun onTableSelected(table: Table?, position: Int) {
        //Voy a la segunda vista
        startActivity(intent(this, position))
    }
}
