package id.mintlab.hilkinbooster

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.Fragment
import android.content.Context.VIBRATOR_SERVICE
import androidx.core.content.ContextCompat.getSystemService



class BoostFragment: Fragment() {
    var waveCircle : View? = null
    var button1 : Button? = null
    var vibrator : Vibrator? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_boost, container, false)
        waveCircle = view.findViewById<View>(R.id.waveCircle)
        button1 = view.findViewById<Button>(R.id.button1)
        vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation: Animation
        animation = AnimationUtils.loadAnimation(
            activity,
            R.anim.zoom_in
        )
        val animation2: Animation
        animation2 = AnimationUtils.loadAnimation(
            activity,
            R.anim.zoom_out
        )
        button1?.setOnClickListener {
            button1?.startAnimation(animation2)
            vibrator?.vibrate(50)
        }
        waveCircle?.startAnimation(animation)
    }
}