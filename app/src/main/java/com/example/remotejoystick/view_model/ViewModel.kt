package com.example.remotejoystick.view_model

import android.os.Build
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.example.remotejoystick.view.MainActivity
import com.example.remotejoystick.model.Model
import kotlin.properties.Delegates

public class ViewModel() {

    private var model : Model = Model()

    var rudder : Int by Delegates.observable(1000) { property, oldValue, newValue ->
        val realVal : Float = (oldValue.toFloat() - 1000) / 1000
        model.changeRudder(realVal)
    }

    var throttle : Int by Delegates.observable(0) { property, oldValue, newValue ->
        val realVal : Float = oldValue.toFloat()/1000
        model.changeThrottle(realVal)
    }


    fun connect(ip : String, port : Int) {
        this.model.connectToServer(ip,port)
    }


    fun setJoystickProgress(aileron : Float, elevator : Float) {
        model.changeJoystick(aileron, elevator)
    }

}