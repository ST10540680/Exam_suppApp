package com.example.partypreptrackerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // installing the splash screen before setting content view
        val splashScreen =installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        //keep the splash screen visible for 3 seconds while loading local data
        //setting up data Loading(SplashScreen)
        var isReady = false
        
        //telling the splash screen to wait until is Ready is true
        SplashScreen.setKeepOnScreenCondition {
            lifecycleScope.launch {
                delay(3000.milliseconds) 
                isReady = true
            }
        }

        // Declaring variables
        val SuppliesID= findViewById<EditText>(R.id.SuppliesID)
        val BeveragesID = findViewById<EditText>(R.id.BeveragesID)
        val SnackID = findViewById<EditText>(R.id.SnackID)
        val AddItemBtnID = findViewById<Button>(R.id.AddItemBtnID)

        // setting on click listener
        AddItemBtnID.setOnClickListener{
            
        }
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

fun SplashScreen.Companion.setKeepOnScreenCondition(function: () -> Job) {}
