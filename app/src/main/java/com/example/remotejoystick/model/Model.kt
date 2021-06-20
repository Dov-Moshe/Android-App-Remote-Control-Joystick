package com.example.remotejoystick.model

import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import com.example.remotejoystick.view.MainActivity
import java.io.DataOutputStream
import java.lang.Exception
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Stream

class Model() : IModel{

    lateinit var socket : Socket
    lateinit var stream : DataOutputStream
    var executor : ExecutorService = Executors.newFixedThreadPool(1)

    init {


        /*try {
            executor.execute(Runnable { socket = Socket("10.0.0.138",12345) })
            //val stream = DataOutputStream(socket.getOutputStream())
            //Toast.makeText(activity, "Connecting to server!", Toast.LENGTH_SHORT).show()
        } catch (e : Exception) {
            //Toast.makeText(activity, "Cannot connect to server!", Toast.LENGTH_SHORT).show()
        }*/

        /*try {
            var socket = Socket("10.0.0.138",12345)
            val stream = DataOutputStream(socket.getOutputStream())
            Toast.makeText(activity, "Connecting to server!", Toast.LENGTH_SHORT).show()
        } catch (e : Exception) {
            Toast.makeText(activity, "Cannot connect to server!", Toast.LENGTH_SHORT).show()
        }*/
    }

    fun connectToServer(ip : String, port : Int) {

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        try {
            socket = Socket(ip,port)
            stream = DataOutputStream(socket.getOutputStream())
            Log.d("", "Connecting to server!")
        } catch (e : Exception) {
            Log.d("", "Cannot connect to server!")
        }


        /*if (socket.isConnected) {
            Toast.makeText(activity, "Connecting to server!", Toast.LENGTH_SHORT).show()
        }*/

        /*Thread(Runnable {
            fun run() {
                try  {
                    socket = Socket("10.0.0.12",12345)
                    Toast.makeText(activity, "Connecting to server!", Toast.LENGTH_SHORT).show()
                } catch (e : Exception) {
                    Toast.makeText(activity, "Cannot connect to server!", Toast.LENGTH_SHORT).show()
                }
            }
        }).start()*/




        /*executor.execute(Runnable { try {
            socket = Socket("10.0.0.12",12345)
            //val stream = DataOutputStream(socket.getOutputStream())
            Toast.makeText(activity, "Connecting to server!", Toast.LENGTH_SHORT).show()
        } catch (e : Exception) {
            Toast.makeText(activity, "Cannot connect to server!", Toast.LENGTH_SHORT).show()
        } })*/

        /*try {
            socket = Socket("10.0.0.138",12345)
            //val stream = DataOutputStream(socket.getOutputStream())
            Toast.makeText(activity, "Connecting to server!", Toast.LENGTH_SHORT).show()
        } catch (e : Exception) {
            Toast.makeText(activity, "Cannot connect to server!", Toast.LENGTH_SHORT).show()
        }*/
    }




    override fun changeRudder(newVal: Float) {
        //Log.d("", "Rudder: $newVal")
        executor.execute(Runnable { try {
            stream.writeBytes("set /controls/flight/rudder "+newVal+"\r\n")
            stream.flush()
        } catch (e : Exception){} })
    }

    override fun changeThrottle(newVal: Float) {
        //Log.d("", "Throttle: $newVal")
        executor.execute(Runnable { try {
            stream.writeBytes("set /controls/engines/current-engine/throttle "+newVal+"\r\n")
            stream.flush()
        } catch (e : Exception){} })
    }

    override fun changeJoystick(newValFirst: Float, newValSecond: Float) {
        //Log.d("", "aileron: $newValFirst, elevator: $newValSecond")
        executor.execute(Runnable { try {
            stream.writeBytes("set /controls/flight/aileron "+newValFirst+"\r\n")
            stream.writeBytes("set /controls/flight/elevator "+newValSecond+"\r\n")
            stream.flush()
        } catch (e : Exception){} })
    }
}