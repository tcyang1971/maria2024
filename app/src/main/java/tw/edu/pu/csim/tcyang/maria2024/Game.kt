package tw.edu.pu.csim.tcyang.maria2024

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class Game(val scope: CoroutineScope, screenW:Int, screenH:Int, scale:Float) {
    var counter = 0
    val state = MutableStateFlow(0)
    val icon = Icon(screenW, screenH, scale)
    var isPlaying = true

    fun Play(){

        scope.launch {
            counter = 0
            while (isPlaying) {
                delay(1000)
                counter++
                state.emit(counter)

                icon.x += 50  //往右移動
                if (icon.ReachRight()){
                    isPlaying = false
                }
            }
        }
    }
}