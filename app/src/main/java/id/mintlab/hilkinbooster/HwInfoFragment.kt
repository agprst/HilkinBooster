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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_hwinfo, container, false)

        lineChart = view.findViewById<LineChart>(R.id.chart)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateData()

    }

    @Synchronized fun updateData(){
        val entries = ArrayList<Entry>()
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



        Thread(
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
                    Thread.sleep(1000)
                }
            }).start()
    }

    fun info(){
        val activityMgr = activity?.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityMgr.getMemoryInfo(memoryInfo)

        Log.d("Aval memory :", memoryInfo.availMem.toString())
        Log.d("low memory :", memoryInfo.lowMemory.toString())
        Log.d("threshold memory :", memoryInfo.threshold.toString())
    }

}