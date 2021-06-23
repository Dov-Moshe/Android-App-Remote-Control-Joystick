package com.example.remotejoystick.model

import java.io.DataOutputStream
import java.lang.Exception
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Model {

    lateinit var socket : Socket
    lateinit var stream : DataOutputStream
    var executor : ExecutorService = Executors.newFixedThreadPool(1)

    var rudder = "set /controls/flight/rudder "
    var throttle = "set /controls/engines/current-engine/throttle "
    var aileron = "set /controls/flight/aileron "
    var elevator = "set /controls/flight/elevator "

    fun connectToServer(ip : String, port : Int) {

        try {
            Thread {
                socket = Socket(ip, port)
                stream = DataOutputStream(socket.getOutputStream())
            }.start()
        } catch (e : Exception) {
        }
    }

    fun changeRudder(newVal: Float) {
        executor.execute {
            try {
                stream.writeBytes(rudder + newVal + "\r\n")
                stream.flush()
            } catch (e: Exception) {
            }
        }
    }

    fun changeThrottle(newVal: Float) {
        executor.execute {
            try {
                stream.writeBytes(throttle + newVal + "\r\n")
                stream.flush()
            } catch (e: Exception) {
            }
        }
    }

    fun changeJoystick(newValFirst: Float, newValSecond: Float) {
        executor.execute {
            try {
                stream.writeBytes(aileron + newValFirst + "\r\n")
                stream.writeBytes(elevator + newValSecond + "\r\n")
                stream.flush()
            } catch (e: Exception) {
            }
        }
    }
}