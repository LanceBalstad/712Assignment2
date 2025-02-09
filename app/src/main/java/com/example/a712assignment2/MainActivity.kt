package com.example.a712assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.a712assignment2.ui.theme._712Assignment2Theme
import androidx.compose.ui.unit.dp
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _712Assignment2Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, id: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Cyan) {
        Text(
            text = "$name $id",
            modifier = modifier.padding(24.dp)
        )
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current //This is the context

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Name: Lance Balstad")
        Text(text = "Student ID: 1359915")

        Button(onClick = {
            val secondActivityIntent = Intent(context, SecondActivity::class.java)
            context.startActivity(secondActivityIntent)
        }) {
            Text(text = "Start Activity Explicitly")
        }

        Button(onClick = {
            val sendIntent = Intent(Intent.ACTION_SEND) // Example of an implicit intent
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is an implicit intent example!")
            context.startActivity(sendIntent)
        }) {
            Text(text = "Start Activity Implicitly")
        }
    }
}