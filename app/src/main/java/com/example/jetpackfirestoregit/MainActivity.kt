package com.example.jetpackfirestoregit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackfirestoregit.ui.theme.JetpackFirestoreGitTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackFirestoreGitTheme {

                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Button(
                        "SnapshotListener",
                        Intent(this@MainActivity, SnapshotListener::class.java),
                        { intent: Intent -> (startActivity(intent)) })

                }

            }
            }
        }
    }

@Composable
fun Button (text: String, direction: Intent, direct: (Intent) -> Unit) {

    OutlinedButton(

        onClick = {direct(direction)},

        modifier = Modifier
            .width(220.dp)
            .height(50.dp),

        content = { Text(text) })

}

