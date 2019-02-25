package id.mintlab.hilkinbooster

import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.os.CpuUsageInfo
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random
import android.app.ActivityManager
import android.content.Context.ACTIVITY_SERVICE

open class HwInfoFragment : Fragment() {

    var lineChart : LineChart? = null
    var index : Float = 0f
    var thread : Thread? = null
    private var entries = ArrayList<Entry>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_hwinfo, container, false)

        lineChart = view.findViewById<LineChart>(R.id.chart)

        return view
    }

    override fun onPause() {
        super.onPause()
        thread?.interrupt()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("entries", entries)
        outState.putFloat("index", index)
        Log.d("", "test")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null){
            entries = savedInstanceState.getParcelableArrayList("entries")
            index = savedInstanceState.getFloat("index")
        }
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }


    @Synchronized fun updateData(){
        var lineData : LineData
        lineChart?.xAxis?.textColor = Color.TRANSPARENT
        lineChart?.axisLeft?.textColor = Color.TRANSPARENT
        lineChart?.axisRight?.textColor = Color.TRANSPARENT
        lineChart?.legend?.isEnabled = false
        lineChart?.setBorderWidth(3f)
        val description = Description()
        description.text = ""
        description.textColor = resources.getColor(R.color.colorPrimary)
        lineChart?.description = description


        thread  = Thread(
            Runnable {
                while (true){

                    entries.add(Entry(index, Random.nextInt(10).toFloat()))
                    lineData = LineData(LineDataSet(entries, ""))
                    lineData.setValueTextColor(Color.GRAY)
                    lineChart?.data = lineData

                    lineChart?.setVisibleXRange(0f, 10f)
                    lineChart?.moveViewToX(index)
                    index++
                    info()
                    try {
                        Thread.sleep(1000)
                    } catch(e : InterruptedException){
                        Log.d("Interupted", e.toString())
                    }
                }
            })
        thread?.start()
    }

    fun info(){
        if (context == null){ return }
        val activityMgr = context?.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityMgr.getMemoryInfo(memoryInfo)

        Log.d("Aval memory :", memoryInfo.availMem.toString())
        Log.d("low memory :", memoryInfo.lowMemory.toString())
        Log.d("threshold memory :", memoryInfo.threshold.toString())
    }

}