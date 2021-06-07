package com.example.remotejoystick.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.remotejoystick.R
import com.example.remotejoystick.view_model.ViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModel
    lateinit var joystick : Joystick

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModel()

        val layout: LinearLayout = findViewById(R.id.joystick)
        joystick = Joystick(this)
        layout.addView(joystick)


        findViewById<Button>(R.id.button_connect).setOnClickListener {
            try {
                val ip = findViewById<EditText>(R.id.ip_flight_gear)
                val port = findViewById<EditText>(R.id.port_flight_gear)
                viewModel.connect(this, ip.text.toString(), port.text.toString().toInt())
            } catch (e: Exception) {
                Toast.makeText(this, "Connection failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}