package es.fabiogomez.carta.fragments


import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import es.fabiogomez.carta.R
import es.fabiogomez.carta.models.Table
import es.fabiogomez.carta.models.Tables

class TableListFragment : Fragment() {

    companion object {

        fun newInstance(): TableListFragment {
            val fragment = TableListFragment()
            return fragment
        }
    }

    lateinit var root: View
    private var onTableSelectedListener: OnTableSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table_list, container, false)
            val list = root.findViewById<ListView>(R.id.table_list)
            val adapter = ArrayAdapter<Table>(activity, android.R.layout.simple_list_item_1, Tables.toArray())

            list.adapter = adapter

            list.setOnItemClickListener { parent, view, position, id ->
                onTableSelectedListener?.onTableSelected(Tables.get(position), position)
            }
        }

        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onTableSelectedListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnTableSelectedListener) {
            onTableSelectedListener = listener
        }
    }


    interface OnTableSelectedListener {
        fun onTableSelected( table: Table?, position: Int)
    }
}

