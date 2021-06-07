package com.example.remotejoystick.view_model

import android.widget.Toast
import com.example.remotejoystick.view.MainActivity

public class ViewModel {

    public fun connect(activity: MainActivity, ip : String, port : Int) {
        //print(ip);
        //print(port);
        Toast.makeText(activity, ip, Toast.LENGTH_SHORT).show()
        Toast.makeText(activity, port.toString(), Toast.LENGTH_SHORT).show()
    }
}