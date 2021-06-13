package com.example.remotejoystick.view_model

import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.example.remotejoystick.view.MainActivity
import com.example.remotejoystick.model.Model

public class ViewModel(activity: MainActivity) {

    var activity: MainActivity
    private var model : Model = Model(activity)

    init {
        this.activity = activity
    }


    fun connect(ip : String, port : Int) {
        this.model.connectToServer(ip,port)
        //print(ip)
        //print(port)
        //Toast.makeText(activity, ip, Toast.LENGTH_SHORT).show()
        //Toast.makeText(activity, port.toString(), Toast.LENGTH_SHORT).show()
    }

    /*fun setSeekbarProgress(seekBar : SeekBar, newOpacity : Int, fromUser : Boolean) : Unit {
        Toast.makeText(activity, newOpacity.toString(), Toast.LENGTH_SHORT).show()
    }*/

    fun setSeekbarProgress(newOpacity : Int, str : String) {

        when(str) {
            "rudder" -> {
                val realVal : Float = (newOpacity.toFloat() - 1000) / 1000
                model.changeRudder(realVal)
            }
            "throttle" -> {
                val realVal : Float = newOpacity.toFloat()/1000
                model.changeThrottle(realVal)
            }
        }
    }

    fun setJoystickProgress(aileron : Float, elevator : Float) {
        model.changeJoystick(aileron, elevator)
    }

}