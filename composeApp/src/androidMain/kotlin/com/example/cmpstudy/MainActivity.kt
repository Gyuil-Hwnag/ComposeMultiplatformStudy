package com.example.cmpstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        Firebase.initialize(
            context = applicationContext,
            options = FirebaseOptions(
                applicationId = getString(R.string.firebase_application_id),
                apiKey = getString(R.string.firebase_api_key),
                projectId = getString(R.string.firebase_project_id)
            )
        )

        setContent {
            App()
        }
    }
}
