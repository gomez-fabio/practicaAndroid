package es.fabiogomez.restaurante.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.fabiogomez.restaurante.R
import es.fabiogomez.restaurante.adapter.PlatesRecyclerViewAdapter
import es.fabiogomez.restaurante.model.Plate


class PlatesListFragment : Fragment() {
    val LIST = "LIST"
    companion object {
        fun newInstance() = PlatesListFragment()
    }

    lateinit var plateListRecyclerView: RecyclerView
    lateinit var root: View
    lateinit var list : ArrayList<Plate>

    var onPlateClickListener: PlatesRecyclerViewAdapter.OnPlateSelectedListener? = null
    lateinit var adapter: PlatesRecyclerViewAdapter


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (inflater != null){
            if (savedInstanceState != null)
                @Suppress("UNCHECKED_CAST")
                list =savedInstanceState.getSerializable(LIST) as ArrayList<Plate>

            root = inflater.inflate(R.layout.fragment_plates_list,container,false)
            plateListRecyclerView = root.findViewById(R.id.menu_list)
            plateListRecyclerView.layoutManager = LinearLayoutManager(activity)
            plateListRecyclerView.itemAnimator = DefaultItemAnimator()
            // Adapter
            adapter = PlatesRecyclerViewAdapter(list, onPlateClickListener)
            plateListRecyclerView.adapter = adapter
        }

        return root
    }

    override fun onResume() {
        super.onResume()

        plateListRecyclerView.adapter = adapter
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    fun commonOnAttach(context: Context?) {
        if (context is PlatesRecyclerViewAdapter.OnPlateSelectedListener) {
            onPlateClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        onPlateClickListener = null
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putSerializable(LIST,list)
    }


}
