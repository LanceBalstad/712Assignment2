package com.example.a712assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.a712assignment2.ui.theme._712Assignment2Theme
import androidx.compose.ui.unit.dp
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _712Assignment2Theme {
                SecondScreen()
            }
        }
    }
}

@Composable
fun SecondScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Mobile Software Engineering Challenges:")
        Text(text = "1. Providing accessibility to multiple platforms")
        Text(text = "2. Performance Optimization")
        Text(text = "3. Security Issues")
        Text(text = "4. Varying UI sizes")
        Text(text = "5. Network Connectivity Issues")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val intent = Intent(context, MainActivity::class.java) // Go back to MainActivity
            context.startActivity(intent)
        }) {
            Text(text = "Main Activity")
        }
    }
}
