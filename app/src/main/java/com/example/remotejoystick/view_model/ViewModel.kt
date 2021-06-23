package com.example.remotejoystick.view_model

import com.example.remotejoystick.model.Model
import kotlin.properties.Delegates

class ViewModel {

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

    fun setAileron(x: Float) {
        this.model.changeAileron(x)
    }
    fun setElevator(y: Float) {
        this.model.changeElevator(y)
    }
}