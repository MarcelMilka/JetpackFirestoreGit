package com.example.jetpackfirestoregit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.jetpackfirestoregit.ui.theme.EerieBlack
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SnapshotListener : ComponentActivity() {

//    References
    private val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()

//    Keys
    private val keyDrankAmountOfWater = "drankAmountOfWater"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

//            Data from database
            val dailyWaterIntake by remember { mutableStateOf(2100) }

            var mondayDrankAmountOfWater by remember { mutableStateOf(0) }
            var tuesdayDrankAmountOfWater by remember { mutableStateOf(0) }
            var wednesdayDrankAmountOfWater by remember { mutableStateOf(0) }
            var thursdayDrankAmountOfWater by remember { mutableStateOf(0) }
            var fridayDrankAmountOfWater by remember { mutableStateOf(0) }
            var saturdayDrankAmountOfWater by remember { mutableStateOf(0) }
            var sundayDrankAmountOfWater by remember { mutableStateOf(0) }

//            Getting data from database
            CoroutineScope(Dispatchers.Main).run {

                gettingData(firebase, "snapshotListener", "monday", keyDrankAmountOfWater) {gottenData: String -> mondayDrankAmountOfWater = gottenData.toInt()}
                gettingData(firebase, "snapshotListener", "tuesday", keyDrankAmountOfWater) {gottenData: String -> tuesdayDrankAmountOfWater = gottenData.toInt()}
                gettingData(firebase, "snapshotListener", "wednesday", keyDrankAmountOfWater) {gottenData: String -> wednesdayDrankAmountOfWater = gottenData.toInt()}
                gettingData(firebase, "snapshotListener", "thursday", keyDrankAmountOfWater) {gottenData: String -> thursdayDrankAmountOfWater = gottenData.toInt()}
                gettingData(firebase, "snapshotListener", "friday", keyDrankAmountOfWater) {gottenData: String -> fridayDrankAmountOfWater = gottenData.toInt()}
                gettingData(firebase, "snapshotListener", "saturday", keyDrankAmountOfWater) {gottenData: String -> saturdayDrankAmountOfWater = gottenData.toInt()}
                gettingData(firebase, "snapshotListener", "sunday", keyDrankAmountOfWater) {gottenData: String -> sundayDrankAmountOfWater = gottenData.toInt()}

            }

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),

                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,

                content = {


                    // Adding
                    Column (

                        Modifier
                            .width(125.dp)
                            .height(125.dp)
                            .background(

                                color = EerieBlack,
                                shape = RoundedCornerShape(20.dp)

                            ),

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,

                        content = {

                            Button(

                                onClick = {},
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(50.dp),

                                colors = ButtonDefaults.buttonColors(White),

                                shape = RoundedCornerShape(10.dp),
                                content = {}
                            )
                        }
                    )

                    Spacer(Modifier.height(250.dp))

                    // Graph
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

                                    DisplayWeeklyProgress("mon", mondayDrankAmountOfWater, dailyWaterIntake)
                                    DisplayWeeklyProgress("tue", tuesdayDrankAmountOfWater, dailyWaterIntake)
                                    DisplayWeeklyProgress("wed", wednesdayDrankAmountOfWater, dailyWaterIntake)
                                    DisplayWeeklyProgress("thu", thursdayDrankAmountOfWater, dailyWaterIntake)
                                    DisplayWeeklyProgress("fri", fridayDrankAmountOfWater, dailyWaterIntake)
                                    DisplayWeeklyProgress("sat", saturdayDrankAmountOfWater, dailyWaterIntake)
                                    DisplayWeeklyProgress("sun", sundayDrankAmountOfWater, dailyWaterIntake)

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
fun RowScope.DisplayWeeklyProgress (day: String, ingestedAmountOfWater: Int, requiredAmountOfWater: Int) {

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

fun gettingData(firestore: FirebaseFirestore, collectionPath: String, documentPath: String, key: String, toReturn: (String) -> Unit) {

    val reference: DocumentReference = firestore.collection(collectionPath).document(documentPath)

    reference.get()
        .addOnSuccessListener { resource ->

            if (resource == null) {

                toReturn("Error")

            }

            else {

                if (resource.getString(key) != null) {

                    val dataToReturn = resource.getString(key)

                    toReturn(dataToReturn!!)


                }

            }

        }

}

