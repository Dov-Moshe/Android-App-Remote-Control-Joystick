package com.example.remotejoystick.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.pow

enum class WindowSize(val value: Int) {
    HEIGHT(0), WIDTH(0)
}

enum class HalfWindowSize(var value: Float) {
    HEIGHT(0f), WIDTH(0f)
}

class Joystick @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paintBigCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = Color.rgb(41, 171, 226)
        style = Paint.Style.STROKE
        strokeWidth = 5.toFloat()
    }

    private val paintSmallCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        //color = Color.rgb(34, 181, 115)
        style = Paint.Style.FILL
    }

    private var isStart = true
    private var clickCircle = false
    private var circleX = 0
    private var circleY = 0
    //private var myMain: MainActivity? = context as MainActivity
    private var bigCircleOutsideRadius : Float = 0f
    private var bigCircleMidRadius : Float = 0f
    private var bigCircleInsideRadius : Float = 0f
    private var radius : Float = 0f
    private var spaceWidth :Int = 0
    private var spaceHeight : Int = 0


    ///enter - if the player click at the screen
    ///exit- if the game didn't have thread that running right now the function will try to create one
    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)

        ///if its not the first turn
        clickCircle = 300.toDouble().pow(2.0) >=
                (event.x.toInt()-width/2).toDouble().pow(2.0)+(event.y.toInt()-height/2).toDouble().pow(2.0)
        if (clickCircle) {
            circleX = event.x.toInt()
            circleY = event.y.toInt()

            var mousePointX : Float = ((circleX - spaceWidth).toFloat())
            var mousePointY : Float = ((circleY - spaceHeight).toFloat())

            mousePointX =  (-1)*(bigCircleOutsideRadius - mousePointX) / bigCircleOutsideRadius
            mousePointY = (bigCircleOutsideRadius - mousePointY) / bigCircleOutsideRadius

            (context as MainActivity).MouseCoordinate(mousePointX, mousePointY)
        }

        invalidate()
        return true
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if(isStart) {
            isStart = false
            circleX = width / 2
            circleY = height / 2

            bigCircleOutsideRadius = width.toFloat() / 3
            bigCircleMidRadius = bigCircleOutsideRadius - 10
            bigCircleInsideRadius = bigCircleOutsideRadius - 20
            radius = bigCircleOutsideRadius / 2

            HalfWindowSize.WIDTH.value = width.toFloat() / 2
            HalfWindowSize.HEIGHT.value = height.toFloat() / 2
            spaceWidth = (width - bigCircleOutsideRadius.toInt() * 2) / 2
            spaceHeight = (height - bigCircleOutsideRadius.toInt() * 2) / 2
        }

        val gradient = RadialGradient(circleX.toFloat(),circleY.toFloat(), radius, Color.rgb(34, 181, 115), Color.rgb(0, 143, 77), Shader.TileMode.MIRROR)
        paintSmallCircle.setShader(gradient)

        canvas.apply {

            //draw big circle
            drawCircle(HalfWindowSize.WIDTH.value, HalfWindowSize.HEIGHT.value, bigCircleOutsideRadius, paintBigCircle)
            drawCircle(HalfWindowSize.WIDTH.value, HalfWindowSize.HEIGHT.value, bigCircleMidRadius, paintBigCircle)
            drawCircle(HalfWindowSize.WIDTH.value, HalfWindowSize.HEIGHT.value, bigCircleInsideRadius, paintBigCircle)

            //draw small circle
            drawCircle(circleX.toFloat(), circleY.toFloat(), radius, paintSmallCircle) ///draw the circle
        }
    }

}