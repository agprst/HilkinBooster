package id.mintlab.hilkinbooster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val animation: Animation
        animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.zoom_in
        )
        val animation2: Animation
        animation2 = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.zoom_out
        )
        val view = findViewById<View>(R.id.waveCircle)
        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            button1.startAnimation(animation2)
        }
        view.startAnimation(animation)
    }
}
