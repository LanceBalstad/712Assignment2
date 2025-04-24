package com.example.a712assignment2

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.a712assignment2.ui.theme._712Assignment2Theme

class MainActivity : ComponentActivity() {

    companion object {
        // Your custom permission name (must match AndroidManifest)
        const val PERMISSION_NAME = "com.example.a712assignment2.MSE412"
        // Any integer ≥0 will work as request code
        private const val REQ_MSE412 = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ① Check & request your custom permission *before* rendering UI
        if (ContextCompat.checkSelfPermission(this, PERMISSION_NAME)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(PERMISSION_NAME),
                REQ_MSE412
            )
        }

        enableEdgeToEdge()
        setContent {
            _712Assignment2Theme {
                MainScreen()
            }
        }
    }

    // ② Handle the user’s “Allow / Deny” choice
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQ_MSE412) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "MSE412 granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,
                    "MSE412 denied — you won’t be able to open the protected screen.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Name: Lance Balstad")
        Text(text = "Student ID: 1359915")

        // Unprotected explicit launch
        Button(onClick = {
            context.startActivity(
                Intent(context, SecondActivity::class.java)
            )
        }) {
            Text("Start Activity Explicitly")
        }

        // Protected launch—only if MSE412 granted
        Button(onClick = {
            if (ContextCompat.checkSelfPermission(
                    context,
                    MainActivity.PERMISSION_NAME
                ) == PackageManager.PERMISSION_GRANTED) {
                context.startActivity(
                    Intent(context, SecondActivity::class.java)
                )
            } else {
                Toast.makeText(
                    context,
                    "Please grant MSE412 permission first",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }) {
            Text("Start Protected Activity")
        }

        // The rest of your buttons…
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "This is an implicit intent example!")
            }
            context.startActivity(sendIntent)
        }) {
            Text("Start Activity Implicitly")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            context.startActivity(
                Intent(context, ThirdActivity::class.java)
            )
        }) {
            Text("View Image Activity")
        }
    }
}
