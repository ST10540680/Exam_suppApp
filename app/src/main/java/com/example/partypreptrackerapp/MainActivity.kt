package com.example.partypreptrackerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : AppCompatActivity() {

    data class Item(
        val name: String,
        val category: String,
        val quantity: Int,
        val comment: String,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        // installing the splash screen before setting content view
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //keep the splash screen visible for 3 seconds while loading local data
        //setting up data Loading(SplashScreen)
        var isReady = false

        //telling the splash screen to wait until is Ready is true
        splashScreen.setKeepOnScreenCondition {
            !isReady
        }

        lifecycleScope.launch {
            delay(3000.milliseconds)
            isReady = true
        }

        // Declaring variables
        val suppliesID = findViewById<EditText>(R.id.SuppliesID)
        val beveragesID = findViewById<EditText>(R.id.BeveragesID)
        val snackID = findViewById<EditText>(R.id.SnackID)
        val addItemBtnID = findViewById<Button>(R.id.AddItemBtnID)

        // setting on click listener
        addItemBtnID?.setOnClickListener {
            val supply = suppliesID?.text?.toString() ?: ""
            val beverage = beveragesID?.text?.toString() ?: ""
            val snack = snackID?.text?.toString() ?: ""

            // Using the Item class to satisfy the unused class warning
            val newItem = Item(supply, "Misc", 1, "Added via UI")

            Toast.makeText(
                this@MainActivity,
                "Added: ${newItem.name}. Supplies: $supply, Bev: $beverage, Snack: $snack",
                Toast.LENGTH_LONG,
            ).show()

            // declaration
            val items = arrayOf("Paper Cups", "Soda Bottles", "Potato Chips")

            //Updating elements
            items[2] = "Soda Bottles"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
