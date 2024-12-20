package tw.edu.pu.csim.tcyang.maria2024

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import tw.edu.pu.csim.tcyang.maria2024.ui.theme.Maria2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Maria2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val screenW = resources.displayMetrics.widthPixels
                    val screenH = resources.displayMetrics.heightPixels
                    //dp轉像素的倍率 (1dp的像素)
                    val scale = resources.displayMetrics.density
                    val game = Game(GlobalScope, screenW, screenH, scale)

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    Exam(m = Modifier.padding(innerPadding), game)
                    game.Play()
                }
            }
        }
    }
}

@Composable
fun Exam(m:Modifier, game:Game) {
    val activity = (LocalContext.current as? Activity)

    var bkColor = arrayOf(
        Color(0xff95fe95), Color(0xfffdca0f),
        Color(0xfffea4a4), Color(0xffa5dfed))
    var offset1 by remember { mutableStateOf(Offset.Zero) }
    var offset2 by remember { mutableStateOf(Offset.Zero) }
    var Number by remember { mutableStateOf(0) }

    val counter by game.state.collectAsState()
    var score by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount -> offset2+=dragAmount},
                    onDragStart = {
                        offset1 = it
                        offset2 = it },
                    onDragEnd = {
                        if (offset2.x >= offset1.x){
                            Number ++
                            if (Number>3){Number=0}
                        }
                        else{
                            Number --
                            if (Number<0){Number=3}
                        }

                    }
                )
            }
            .background(bkColor[Number])
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "2024期末上機考(資管四A楊子青)",
                modifier = m
            )

            Image(
                painter = painterResource(id = R.drawable.class_a),
                contentDescription = "A班合照",
            )

            Text("遊戲持續時間 " + counter.toString() + " 秒")
            Text("您的成績 " + score.toString() + " 分")

            Button(onClick = {
                activity?.finish()
            }) { Text("結束App") }
        }
    }

    var pic = arrayOf(R.drawable.maria0, R.drawable.maria1,
        R.drawable.maria2, R.drawable.maria3)
    Image(
        painter = painterResource(id = pic[game.icon.pictNo]),
        contentDescription = "瑪利亞位置圖示",
        modifier = Modifier
            .size(200.dp)
            .offset { (IntOffset(game.icon.x,game.icon.y))}
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        if ( Number == game.icon.pictNo){
                            score ++
                            game.icon.Reset()
                        }
                        else{
                            score --
                        }
                    }
                )
            }
    )
}

