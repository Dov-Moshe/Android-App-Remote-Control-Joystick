package com.example.remotejoystick.view

import android.app.ActionBar
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.example.remotejoystick.R
import com.example.remotejoystick.databinding.ActivityMainBinding
import com.example.remotejoystick.view_model.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    lateinit var joystick : Joystick

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModel()
        binding.viewModel = viewModel

        val layout: LinearLayout = findViewById(R.id.joystick)
        joystick = Joystick(this)
        layout.addView(joystick)

        joystick.joystickService = object : JoystickService {
            override fun Coordinates(x: Float, y: Float) {
                viewModel.setJoystickProgress(x, y)
            }
        }

        findViewById<Button>(R.id.button_connect).setOnClickListener {
            try {
                val ip = findViewById<EditText>(R.id.ip_flight_gear)
                val port = findViewById<EditText>(R.id.port_flight_gear)

                //binding.buttonConnect.text = "connecting..."
                viewModel.connect(ip.text.toString(), port.text.toString().toInt())
            } catch (e: Exception) {
                Toast.makeText(this, "Connection failed!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.portFlightGear.setOnClickListener {
            hideKeyboard()
        }
    }

    /*override fun onResume() {
        super.onResume()
        binding.throttleBar.layoutParams =
            ActionBar.LayoutParams(binding.frameLayout.height, ActionBar.LayoutParams.MATCH_PARENT)
    }*/

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(view.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}