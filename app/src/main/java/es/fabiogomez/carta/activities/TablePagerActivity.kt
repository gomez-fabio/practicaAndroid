package es.fabiogomez.carta.activities

import android.app.Fragment
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import es.fabiogomez.carta.R
import es.fabiogomez.carta.fragments.DishFragment
import es.fabiogomez.carta.models.Tables

class TablePagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_pager)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val pager = findViewById<ViewPager>(R.id.view_pager)
        val tables = Tables()
        val adapter = object: FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return DishFragment.newInstance(tables[position])
            }

            override fun getCount(): Int {
                return tables.count
            }

            override fun getPageTitle(position: Int): CharSequence {
                return tables[position].name
            }
        }
        pager.adapter = adapter
    }
}
