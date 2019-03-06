package id.mintlab.hilkinbooster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry


open class HwInfoFragment : Fragment() {

    var lineChart : LineChart? = null
    var index : Float = 0f
    var thread : Thread? = null
    private var entries = ArrayList<Entry>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_hwinfo, container, false)
        val listView = view.findViewById<ListView>(R.id.packageList)

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList = context?.getPackageManager()?.queryIntentActivities(mainIntent, 0)
        val appListAdapter = AppListAdapter(context, pkgAppsList)
        listView.adapter = appListAdapter
        return view
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

    }

}