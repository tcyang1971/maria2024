package tw.edu.pu.csim.tcyang.maria2024


class Icon(val screenW:Int, val screenH:Int, val scale:Float)  {
    var x = 0  //x軸座標
    var y = screenH - (200 * scale).toInt()  //y軸座標

    fun ReachRight():Boolean {
        if (x >= screenW) {
            return true
        }
        else{
            return false
        }
    }
}