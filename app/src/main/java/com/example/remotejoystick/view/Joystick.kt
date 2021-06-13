package com.example.remotejoystick.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.pow

enum class WindowSize(val value: Int) {
    HEIGHT(0), WIDTH(0)
}

class Joystick @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        style = Paint.Style.FILL
    }

    private var isStart = true
    private var clickCircle = false
    private var circleX = 0
    private var circleY = 0
    //private var myMain: MainActivity? = context as MainActivity
    private var radiusOutsideCircle : Int = 300
    private var radius : Int = 100
    private var spaceWidth :Int = 0
    private var spaceHeight : Int = 0


    ///enter - if the player click at the screen
    ///exit- if the game didn't have thread that running right now the function will try to create one
    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        /*Log.d("find Problem", WindowSize.WIDTH.value.toString() + " " + WindowSize.HEIGHT.value.toString())*/

        ///if its not the first turn
        clickCircle = 300.toDouble().pow(2.0) >=
            (event.x.toInt()-width/2).toDouble().pow(2.0)+(event.y.toInt()-height/2).toDouble().pow(2.0)
        if (clickCircle) {
            circleX = event.x.toInt()
            circleY = event.y.toInt()

            //Log.d("", "height: " + height.toString() + ", width: " + width.toString())
            //Log.d("", "spaceHeight: " + spaceHeight.toString() + ", spaceWidth: " + spaceWidth.toString())
            //Log.d("", "circleX: " + circleX.toString() + ", circleY: " + circleY.toString())

            var mousePointX : Float = ((circleX - spaceWidth).toFloat())
            var mousePointY : Float = ((circleY - spaceHeight).toFloat())

            mousePointX = (mousePointX - radiusOutsideCircle) / radiusOutsideCircle
            mousePointY = (-1)*(mousePointY - radiusOutsideCircle) / radiusOutsideCircle

            /*if (mousePointX == radiusOutsideCircle.toFloat())
                mousePointX = 0f
            else if (mousePointX < radiusOutsideCircle)
                mousePointX = (-1)*((mousePointX / radiusOutsideCircle))
            else
                mousePointX = (1 - (mousePointX / radiusOutsideCircle))


            if (mousePointY == radiusOutsideCircle.toFloat())
                mousePointY = 0f
            else if (mousePointY > radiusOutsideCircle)
                mousePointY = (-1)*(1 - (mousePointY / radiusOutsideCircle))
            else
                mousePointY = (1 - (mousePointY / radiusOutsideCircle))*/

            //Log.d("", "mousePointX: " + mousePointX.toString() + ", mousePointY: " + mousePointY.toString())

            /*if(mousePointX > 1)
                mousePointX = 1.0;
            if(mousePointX < -1)
                mousePointX = (-1).toDouble();
            mousePointY = ((circleY - spaceHeight).toDouble()/radiusOutsideCircle.toDouble())
            if(mousePointY > 1)
                mousePointY = 1.0;
            if(mousePointY < -1)
                mousePointY = (-1).toDouble();*/
            //Log.d("find Problem", mousePointX.toString() + " " + mousePointY.toString())
            (context as MainActivity).MouseCoordinate(mousePointX, mousePointY)
        }

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {

            if(isStart) {
                isStart = false
                circleX = width / 2
                circleY = height / 2
                spaceWidth = (width - radiusOutsideCircle * 2) / 2
                spaceHeight = (height - radiusOutsideCircle * 2) / 2
            }

            paint.color = Color.RED
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 5.toFloat()
            drawCircle(width.toFloat() / 2, height.toFloat() / 2, radiusOutsideCircle.toFloat(), paint)

            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL

            ///draw circle
            drawCircle(circleX.toFloat(), circleY.toFloat(), radius.toFloat(), paint) ///draw the circle
        }
    }

}