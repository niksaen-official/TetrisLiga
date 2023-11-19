package com.niksaen.tetrisliga

data class Position(var x:Int,var y:Int)
enum class ShapeType{
    Squad,
    Line,
    T_Shaped,
    L_Shaped,
    L_Shaped_Inverted
}
class FigureData(x:Int,y:Int, val shape:ShapeType) {
    var angle = 0
    var p1 = Position(x,y)
    var p2 = Position(0,0)
    var p3 = Position(0,0)
    var p4 = Position(0,0)
    init {
        when(shape){
            ShapeType.Squad -> {
                p2 = Position(1,0)
                p3 = Position(0,1)
                p4 = Position(1,1)
            }
            ShapeType.Line -> {
                p2 = Position(1,0)
                p3 = Position(2,0)
                p4 = Position(3,0)
            }
            ShapeType.T_Shaped -> {
                p2 = Position(0,1)
                p3 = Position(1,0)
                p4 = Position(-1,0)
            }
            ShapeType.L_Shaped -> {
                p2 = Position(1,0)
                p3 = Position(2,0)
                p4 = Position(0,-1)
            }
            ShapeType.L_Shaped_Inverted -> {
                p2 = Position(1,0)
                p3 = Position(2,0)
                p4 = Position(2,-1)
            }
        }
    }
    fun rotate(){
        angle+=90
        if(angle == 360) angle = 0

        when(angle){
            0 -> {
                when(shape){
                    ShapeType.Squad -> {
                        p2 = Position(1,0)
                        p3 = Position(0,1)
                        p4 = Position(1,1)
                    }
                    ShapeType.Line -> {
                        p2 = Position(1,0)
                        p3 = Position(2,0)
                        p4 = Position(3,0)
                    }
                    ShapeType.T_Shaped -> {
                        p2 = Position(0,1)
                        p3 = Position(1,0)
                        p4 = Position(-1,0)
                    }
                    ShapeType.L_Shaped -> {
                        p2 = Position(1,0)
                        p3 = Position(2,0)
                        p4 = Position(0,-1)
                    }
                    ShapeType.L_Shaped_Inverted -> {
                        p2 = Position(1,0)
                        p3 = Position(2,0)
                        p4 = Position(2,-1)
                    }
                }
            }
            90 -> {
                when(shape){
                    ShapeType.Squad -> {}
                    ShapeType.Line -> {
                        p2 = Position(0,1)
                        p3 = Position(0,2)
                        p4 = Position(0,3)
                    }
                    ShapeType.T_Shaped -> {
                        p2 = Position(-1,0)
                        p3 = Position(0,1)
                        p4 = Position(0,-1)
                    }
                    ShapeType.L_Shaped -> {
                        p2 = Position(0,1)
                        p3 = Position(0,2)
                        p4 = Position(1,0)
                    }
                    ShapeType.L_Shaped_Inverted -> {
                        p2 = Position(0,1)
                        p3 = Position(0,2)
                        p4 = Position(1,2)
                    }
                }
            }
            180 -> {
                when(shape){
                    ShapeType.Squad -> {}
                    ShapeType.Line -> {
                        p2 = Position(-1,0)
                        p3 = Position(-2,0)
                        p4 = Position(-3,0)
                    }
                    ShapeType.T_Shaped -> {
                        p2 = Position(0,1)
                        p3 = Position(-1,0)
                        p4 = Position(1,0)
                    }
                    ShapeType.L_Shaped -> {
                        p2 = Position(-1,0)
                        p3 = Position(-2,0)
                        p4 = Position(0,1)
                    }
                    ShapeType.L_Shaped_Inverted -> {
                        p2 = Position(-1,0)
                        p3 = Position(-2,0)
                        p4 = Position(-2,1)
                    }
                }
            }
            270 -> {
                when(shape){
                    ShapeType.Squad -> {}
                    ShapeType.Line -> {
                        p2 = Position(0,-1)
                        p3 = Position(0,-2)
                        p4 = Position(0,-3)
                    }
                    ShapeType.T_Shaped -> {
                        p2 = Position(1,0)
                        p3 = Position(0,-1)
                        p4 = Position(0,1)
                    }
                    ShapeType.L_Shaped -> {
                        p2 = Position(0,-1)
                        p3 = Position(0,-2)
                        p4 = Position(-1,0)
                    }
                    ShapeType.L_Shaped_Inverted -> {
                        p2 = Position(0,-1)
                        p3 = Position(0,-2)
                        p4 = Position(-1,-2)
                    }
                }
            }
        }
    }
}