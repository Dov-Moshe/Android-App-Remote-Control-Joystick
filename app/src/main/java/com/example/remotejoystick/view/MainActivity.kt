package com.example.remotejoystick.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.example.remotejoystick.R
import com.example.remotejoystick.databinding.ActivityMainBinding
import com.example.remotejoystick.view_model.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    lateinit var joystick : Joystick

    private var mouseX: TextView? = null
    private var mosueY: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModel(this)
        binding.viewModel = viewModel

        val layout: LinearLayout = findViewById(R.id.joystick)
        joystick = Joystick(this)
        layout.addView(joystick)


        findViewById<Button>(R.id.button_connect).setOnClickListener {
            try {
                val ip = findViewById<EditText>(R.id.ip_flight_gear)
                val port = findViewById<EditText>(R.id.port_flight_gear)
                binding.buttonConnect.text = "connecting..."
                viewModel.connect(ip.text.toString(), port.text.toString().toInt())
            } catch (e: Exception) {
                Toast.makeText(this, "Connection failed!", Toast.LENGTH_SHORT).show()
            }
        }

        /*val rudder = findViewById<SeekBar>(R.id.rudder_bar)
        rudder?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                try {
                    viewModel.setSeekbarProgress(progress, "rudder")
                } catch (e : Exception) {

                }

            }
            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }
            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })

        val throttle = findViewById<SeekBar>(R.id.throttle_bar)
        throttle?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                try {
                    viewModel.setSeekbarProgress(progress, "throttle")
                } catch (e : Exception) {

                }
            }
            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }
            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })*/

        mouseX = findViewById(R.id.tvXmouse)
        mosueY = findViewById(R.id.tvYmouse)

    }

    fun MouseCoordinate(x: Float, y: Float) {
        viewModel.setJoystickProgress(x,y)

        mouseX!!.text = "x coordinate is - ($x)"
        mosueY!!.text = "y coordinate is - ($y)"
    }
}