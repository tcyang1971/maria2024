package tw.edu.pu.csim.tcyang.maria2024

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import tw.edu.pu.csim.tcyang.maria2024.ui.theme.Maria2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Maria2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    Exam(m = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Exam(m:Modifier) {
    val activity = (LocalContext.current as? Activity)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff95fe95))
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

            Text("遊戲持續時間 0 秒")
            Text("您的成績 0 分")

            Button(onClick = {
                activity?.finish()
            }) { Text("結束App") }
        }
    }
}

