package com.example.remotejoystick.model

interface IModel {
    fun changeRudder(newVal : Float)
    fun changeThrottle(newVal : Float)
    fun changeJoystick(newValFirst : Float, newValSecond : Float)
}