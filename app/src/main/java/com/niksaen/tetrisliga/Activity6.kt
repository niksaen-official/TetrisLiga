package com.niksaen.tetrisliga

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.niksaen.tetrisliga.databinding.Activity6Binding
import java.util.Timer
import java.util.TimerTask

class Activity6 : AppCompatActivity() {
    var points = 0
    private lateinit var ui:Activity6Binding
    lateinit var screen:Array<Array<View>>
    lateinit var screenData:Array<Array<Int>>
    lateinit var figureData:FigureData
    val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = Activity6Binding.inflate(layoutInflater)
        screen = arrayOf(
            arrayOf(ui.p0,ui.p1,ui.p2,ui.p3,ui.p4,ui.p5,ui.p6,ui.p7,ui.p8,ui.p9,ui.p10,ui.p11),
            arrayOf(ui.p12,ui.p13,ui.p14,ui.p15,ui.p16,ui.p17,ui.p18,ui.p19,ui.p20,ui.p21,ui.p22,ui.p23),
            arrayOf(ui.p24,ui.p25,ui.p26,ui.p27,ui.p28,ui.p29,ui.p30,ui.p31,ui.p32,ui.p33,ui.p34,ui.p35),
            arrayOf(ui.p36,ui.p37,ui.p38,ui.p39,ui.p40,ui.p41,ui.p42,ui.p43,ui.p44,ui.p45,ui.p46,ui.p47),
            arrayOf(ui.p48,ui.p49,ui.p50,ui.p51,ui.p52,ui.p53,ui.p54,ui.p55,ui.p56,ui.p57,ui.p58,ui.p59),
            arrayOf(ui.p60,ui.p61,ui.p62,ui.p63,ui.p64,ui.p65,ui.p66,ui.p67,ui.p68,ui.p69,ui.p70,ui.p71),
            arrayOf(ui.p72,ui.p73,ui.p74,ui.p75,ui.p76,ui.p77,ui.p78,ui.p79,ui.p80,ui.p81,ui.p82,ui.p83),
            arrayOf(ui.p84,ui.p85,ui.p86,ui.p87,ui.p88,ui.p89,ui.p90,ui.p91,ui.p92,ui.p93,ui.p94,ui.p95),
            arrayOf(ui.p96,ui.p97,ui.p98,ui.p99,ui.p100,ui.p101,ui.p102,ui.p103,ui.p104,ui.p105,ui.p106,ui.p107),
            arrayOf(ui.p108,ui.p109,ui.p110,ui.p111,ui.p112,ui.p113,ui.p114,ui.p115,ui.p116,ui.p117,ui.p118,ui.p119),
            arrayOf(ui.p120,ui.p121,ui.p122,ui.p123,ui.p124,ui.p125,ui.p126,ui.p127,ui.p128,ui.p129,ui.p130,ui.p131),
            arrayOf(ui.p132,ui.p133,ui.p134,ui.p135,ui.p140,ui.p141,ui.p142,ui.p143,ui.p144,ui.p145,ui.p146,ui.p147),
            arrayOf(ui.p148,ui.p149,ui.p150,ui.p151,ui.p152,ui.p153,ui.p154,ui.p155,ui.p156,ui.p157,ui.p158,ui.p159),
            arrayOf(ui.p160,ui.p161,ui.p162,ui.p163,ui.p164,ui.p165,ui.p166,ui.p167,ui.p168,ui.p169,ui.p170,ui.p171),
            arrayOf(ui.p172,ui.p173,ui.p174,ui.p175,ui.p176,ui.p177,ui.p178,ui.p179,ui.p180,ui.p181,ui.p182,ui.p183),
            arrayOf(ui.p184,ui.p185,ui.p186,ui.p187,ui.p188,ui.p189,ui.p190,ui.p191,ui.p192,ui.p193,ui.p194,ui.p195)
        )
        screenData = arrayOf(
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0),
            arrayOf(0,0,0,0,0,0,0,0,0,0,0,0)
        )
        setContentView(ui.root)
        generateFigure()
        updateDataScreen()
        rendering()
        ui.button3.setOnClickListener { moveFigureHorizontal(true) }
        ui.button4.setOnClickListener { moveFigureHorizontal(false) }
        val task = object : TimerTask(){
            override fun run() {
                moveFigureDown()
            }
        }
        timer.scheduleAtFixedRate(task,100,400)
        ui.back.setOnClickListener {
            startActivity(Intent(this,Activity2::class.java))
            finish()
        }
    }
    fun generateFigure(){
        val shapeType = when((0..4).random()){
            0->ShapeType.Squad
            1->ShapeType.Line
            2->ShapeType.T_Shaped
            3->ShapeType.L_Shaped
            4->ShapeType.L_Shaped_Inverted
            else -> ShapeType.Squad
        }
        figureData = FigureData(5,1, shapeType)
    }
    fun clearScreenData(){
        for(y in screenData.indices) {
            for (x in screenData[y].indices) {
                if(screenData[y][x] == 1){
                    screenData[y][x] = 0
                }
            }
        }
    }
    fun rendering(){
        for(y in screenData.indices){
            for (x in screenData[y].indices){
                if(screenData[y][x] != 0){
                    screen[y][x].setBackgroundColor(resources.getColor(R.color.green))
                }else{
                    screen[y][x].setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }
    }
    fun updateDataScreen(){
        clearScreenData()
        screenData[figureData.p1.y][figureData.p1.x] = 1
        screenData[figureData.p1.y + figureData.p2.y][figureData.p1.x + figureData.p2.x] = 1
        screenData[figureData.p1.y + figureData.p3.y][figureData.p1.x + figureData.p3.x] = 1
        screenData[figureData.p1.y + figureData.p4.y][figureData.p1.x + figureData.p4.x] = 1
    }
    fun moveFigureHorizontal(inc:Boolean){
        clearScreenData()
        if(inc){
            if(figureData.p1.x < screenData[0].size-1 &&
                figureData.p1.x + figureData.p2.x < screenData[0].size-1 &&
                figureData.p1.x + figureData.p3.x < screenData[0].size-1 &&
                figureData.p1.x + figureData.p4.x < screenData[0].size-1){
                figureData.p1.x++
            }
        }else{
            if(figureData.p1.x > 0 &&
                figureData.p1.x + figureData.p2.x > 0 &&
                figureData.p1.x + figureData.p3.x > 0 &&
                figureData.p1.x + figureData.p4.x > 0){
                figureData.p1.x--
            }
        }
        if(!haveEmptyPixel()){
            screenData[figureData.p1.y][figureData.p1.x] = 2
            screenData[figureData.p1.y + figureData.p2.y][figureData.p1.x + figureData.p2.x] = 2
            screenData[figureData.p1.y + figureData.p3.y][figureData.p1.x + figureData.p3.x] = 2
            screenData[figureData.p1.y + figureData.p4.y][figureData.p1.x + figureData.p4.x] = 2
            generateFigure()
        }
        updateDataScreen()
        rendering()
    }
    fun haveEmptyPixel():Boolean{
        val globalP2 = Position(figureData.p1.x+figureData.p2.x,figureData.p1.y+figureData.p2.y)
        val globalP3 = Position(figureData.p1.x+figureData.p3.x,figureData.p1.y+figureData.p3.y)
        val globalP4 = Position(figureData.p1.x+figureData.p4.x,figureData.p1.y+figureData.p4.y)
        return when(figureData.shape){
            ShapeType.Squad -> {
                screenData[globalP3.y+1][globalP3.x] == 0 && screenData[globalP4.y+1][globalP4.x] == 0
            }
            ShapeType.Line -> {
                screenData[figureData.p1.y + 1][figureData.p1.x] == 0 && screenData[globalP2.y + 1][globalP2.x] == 0 && screenData[globalP3.y + 1][globalP3.x] == 0 && screenData[globalP4.y + 1][globalP4.x] == 0
            }
            ShapeType.T_Shaped -> {
                screenData[globalP2.y + 1][globalP2.x] == 0 && screenData[globalP3.y + 1][globalP3.x] == 0  && screenData[globalP4.y + 1][globalP4.x] == 0
            }
            ShapeType.L_Shaped -> {
                screenData[globalP3.y + 1][globalP3.x] == 0 && screenData[globalP2.y + 1][globalP2.x] == 0 && screenData[figureData.p1.y + 1][figureData.p1.x] == 0
            }
            ShapeType.L_Shaped_Inverted -> {
                screenData[globalP3.y + 1][globalP3.x] == 0 && screenData[globalP2.y + 1][globalP2.x] == 0 && screenData[figureData.p1.y + 1][figureData.p1.x] == 0
            }
        }
    }
    fun check(){
        var res = 0
        for(y in screenData.indices){
            for(x in screenData[y].indices){
                res+=screenData[y][x]
                if(res == 24){
                    screenData[y] = arrayOf(0,0,0,0,0,0,0,0,0,0,0,0)
                    points+=120
                    ui.textView5.text = resources.getString(R.string.points)+points.toString()
                    res = 0
                }else if(res > 0){
                    if(y==1 || y==0){
                        timer.cancel()
                        val intent = Intent(this,Activity7::class.java)
                        intent.putExtra("points",points)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            res = 0
        }
    }
    fun moveFigureDown(){
        if(figureData.p1.y < screenData.size-1 &&
            figureData.p1.y + figureData.p2.y < screenData.size-1 &&
            figureData.p1.y + figureData.p3.y < screenData.size-1 &&
            figureData.p1.y + figureData.p4.y < screenData.size-1){
            figureData.p1.y++
            if(figureData.p1.y == screenData.size-1 ||
                figureData.p1.y + figureData.p2.y == screenData.size-1 ||
                figureData.p1.y + figureData.p3.y == screenData.size-1 ||
                figureData.p1.y + figureData.p4.y == screenData.size-1 || !haveEmptyPixel()){
                screenData[figureData.p1.y][figureData.p1.x] = 2
                screenData[figureData.p1.y + figureData.p2.y][figureData.p1.x + figureData.p2.x] = 2
                screenData[figureData.p1.y + figureData.p3.y][figureData.p1.x + figureData.p3.x] = 2
                screenData[figureData.p1.y + figureData.p4.y][figureData.p1.x + figureData.p4.x] = 2
                generateFigure()
                check()
            }
            updateDataScreen()
            rendering()
        }
    }
}