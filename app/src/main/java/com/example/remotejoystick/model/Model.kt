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
        executor.execute {
            try {
                socket = Socket(ip, port)
                stream = DataOutputStream(socket.getOutputStream())
            } catch (e: Exception) {
            }
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

    fun changeAileron(newVal: Float) {
        executor.execute {
            try {
                stream.writeBytes(aileron + newVal + "\r\n")
                stream.flush()
            } catch (e: Exception) {
            }
        }
    }

    fun changeElevator(newVal: Float) {
        executor.execute {
            try {
                stream.writeBytes(elevator + newVal + "\r\n")
                stream.flush()
            } catch (e: Exception) {
            }
        }
    }
}