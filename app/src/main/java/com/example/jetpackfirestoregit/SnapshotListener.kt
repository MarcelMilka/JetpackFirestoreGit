package com.example.jetpackfirestoregit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackfirestoregit.ui.theme.EerieBlack
import com.example.jetpackfirestoregit.ui.theme.JetpackFirestoreGitTheme

class SnapshotListener : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),

                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,

                content = {

                    Column (

                        Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(

                                color = EerieBlack,
                                shape = RoundedCornerShape(20.dp)

                            ),

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,

                        content = {

                            Row (

                                Modifier
                                    .fillMaxSize(),

                                content = {

                                    Spacer(Modifier.height(10.dp))

                                    TextAndProgress("mon", 10, 100)
                                    TextAndProgress("tue", 20, 100)
                                    TextAndProgress("wed", 30, 100)
                                    TextAndProgress("thu", 40, 100)
                                    TextAndProgress("fri", 50, 100)
                                    TextAndProgress("sat", 60, 100)
                                    TextAndProgress("sun", 0, 100)

                                }
                            )
                        }
                    )
                }
            )
        }
    }
}

@Composable
fun RowScope.TextAndProgress (day: String, ingestedAmountOfWater: Int, requiredAmountOfWater: Int) {

    var currentHeight = (ingestedAmountOfWater * 100) / requiredAmountOfWater

    if (currentHeight > 100) {

        currentHeight = 100

    }


    Column (

        Modifier
            .fillMaxHeight()
            .weight(1f, true),

        content = {

            // Progress
            Column (

                Modifier
                    .fillMaxWidth()
                    .weight(3f, true),

                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,

                content = {

                    Surface (

                        modifier = Modifier
                            .width(10.dp)
                            .height(currentHeight.dp),

                        shape = RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp),
                        color = White,
                        content = {}

                    )
                }
            )

            Divider(Modifier.padding(vertical = 5.dp, horizontal = 5.dp))

            // Text
            Column (

                Modifier
                    .fillMaxWidth()
                    .weight(0.8f, true),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                content = { Text(day, color = White) }

            )
        }
    )
}

