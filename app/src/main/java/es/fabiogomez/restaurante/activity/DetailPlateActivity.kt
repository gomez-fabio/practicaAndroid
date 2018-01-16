package es.fabiogomez.restaurante.activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import es.fabiogomez.restaurante.R
import es.fabiogomez.restaurante.fragment.PlateDetailFragment

class DetailPlateActivity : AppCompatActivity() {

    companion object {

        val EXTRA_TABLE_POSITION = "EXTRA_TABLE_POSITION"
        val EXTRA_PLATE_POSITION = "EXTRA_PLATE_POSITION"
        val EXTRA_DETAIL_MODE    = "EXTRA_DETAIL_MODE"

        var tableIndex = -1
        var plateIndex = -1
        var mode = -1

        fun intent(context: Context, tableIndex: Int, plateIndex: Int, mode: Int) : Intent {
            val intent = Intent(context, DetailPlateActivity::class.java)

            intent.putExtra(EXTRA_TABLE_POSITION, tableIndex)
            intent.putExtra(EXTRA_PLATE_POSITION, plateIndex)
            intent.putExtra(EXTRA_DETAIL_MODE, mode)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_plate)

        tableIndex = intent.getIntExtra(EXTRA_TABLE_POSITION, -1)
        plateIndex = intent.getIntExtra(EXTRA_PLATE_POSITION, -1)
        mode = intent.getIntExtra(EXTRA_DETAIL_MODE, -1)

        if (findViewById<View>(R.id.content) != null) {
            val fragment = PlateDetailFragment.newInstance(tableIndex, plateIndex, mode)
            title =  "Editando platos de la mesa"+" ${tableIndex}"
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit()

        }

    }
}
