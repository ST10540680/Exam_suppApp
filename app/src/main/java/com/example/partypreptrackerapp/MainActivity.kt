package com.example.partypreptrackerapp

import android.content.Intent
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

        //linking implicitly by their index positions (
        val itemDetails = arrayOf("","","")
        val itemCategories = arrayOf("","","")
        val itemQuantities = arrayOf(0,0,0)
        val itemComments = arrayOf("","","")

        // setting on click listener
        addItemBtnID?.setOnClickListener {
            val supplyName = suppliesID?.text?.toString() ?: ""
            val beverageName = beveragesID?.text?.toString() ?: ""
            val snackName = snackID?.text?.toString() ?: ""

            //Populating Index 0: supplies
            itemDetails[0]= supplyName
            itemCategories[0] = "Supplies"
            itemQuantities[0] = 50
            itemComments[0] = "Red ones for the theme"

            // Populating Index 1: beverages
            itemDetails[1] = beverageName
            itemCategories[1] = "Beverages"
            itemQuantities[1] = 10
            itemComments[1] = "mix of cola and orange"

            // Populating Index 2: Snacks
            itemDetails[2] =snackName
            itemCategories[2] ="Snacks"
            itemQuantities[2] = 5
            itemComments[2] = "Large bags only"

            val intent = Intent(this@MainActivity, DetailsViewActivity::class.java).apply {
                putExtra("EXTRA_DETAILS", itemDetails)
                putExtra("EXTRA_CATEGORIES", itemCategories)
                // Quantities must be converted to IntArray to pass smoothly through intent
                putExtra("EXTRA_QUANTITIES", itemQuantities.toIntArray())
                putExtra("EXTRA_COMMENTS", itemComments)
            }

            // 4. LAUNCH THE SECOND SCREEN
            startActivity(intent)

            // Using the Item class to satisfy the unused class warning
            val Item = Item(itemDetails[0], itemCategories[0], itemQuantities[0], "")

            Toast.makeText(
                this@MainActivity,
                "Stored! Index 0: ${itemDetails[0]} (${itemCategories[0]}, Qty: ${itemQuantities[0]}) | Index 1: ${itemDetails[1]} | Index 2: ${itemDetails[2]}",
                Toast.LENGTH_LONG,
                ).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
