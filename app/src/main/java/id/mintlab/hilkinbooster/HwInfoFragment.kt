package id.mintlab.hilkinbooster

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hookedonplay.decoviewlib.DecoView
import com.hookedonplay.decoviewlib.charts.SeriesItem
import com.hookedonplay.decoviewlib.events.DecoEvent

class HwInfoFragment : Fragment() {
    var cpuChart : DecoView? = null
    var ramChart : DecoView? = null
    var storageChart : DecoView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_hwinfo, container, false)
        cpuChart = view.findViewById<DecoView>(R.id.cpuChart)
        ramChart = view.findViewById<DecoView>(R.id.ramChart)
        storageChart = view.findViewById<DecoView>(R.id.storageChart)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Create data series track
        val cpuSeries = SeriesItem.Builder(Color.parseColor("#ffbaba"))
            .setRange(0f, 100f, 0f)
            .setLineWidth(32f)
            .build()

        val ramSeries = SeriesItem.Builder(Color.parseColor("#aed8ff"))
            .setRange(0f, 100f, 0f)
            .setLineWidth(32f)
            .build()

        val storageSeries = SeriesItem.Builder(Color.argb(255, 64, 255, 64), Color.argb(255, 255, 0, 0))
            .setRange(0f, 100f, 0f)
            .setLineWidth(32f)
            .build()

        val cpuIndex = cpuChart?.addSeries(cpuSeries)!!
        val ramIndex = ramChart?.addSeries(ramSeries)!!
        val storageIndex = storageChart?.addSeries(storageSeries)!!

        cpuChart?.addEvent(DecoEvent.Builder(25f).setIndex(cpuIndex).build())
        ramChart?.addEvent(DecoEvent.Builder(80f).setIndex(ramIndex).build())
        storageChart?.addEvent(DecoEvent.Builder(100f).setIndex(storageIndex).build())

    }
}