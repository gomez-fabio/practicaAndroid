package es.fabiogomez.carta.activities

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import es.fabiogomez.carta.R
import es.fabiogomez.carta.fragments.DishFragment
import es.fabiogomez.carta.models.Tables

class TablePagerActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, TablePagerActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }
    }
    val pager by lazy { findViewById<ViewPager>(R.id.view_pager) }
    val tables = Tables()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_pager)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                updateTableInfo(position)
            }

        })

        val initialTableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX, 0)

        pager.currentItem = initialTableIndex
        updateTableInfo(initialTableIndex)
    }

    fun updateTableInfo(position: Int) {
        supportActionBar?.title = tables[position].name
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.pager, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.previous -> {
                pager.currentItem = pager.currentItem - 1
                true
            }
            R.id.next -> {
                pager.currentItem++
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)

        invalidateOptionsMenu()

        val menuPrev = menu?.findItem(R.id.previous)
        val menuNext = menu?.findItem(R.id.next)

        menuPrev?.setEnabled(pager.currentItem > 0)
        menuNext?.setEnabled(pager.currentItem < tables.count - 1)

        return true
    }
}
