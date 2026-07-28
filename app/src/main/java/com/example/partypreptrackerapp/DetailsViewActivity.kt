package com.example.partypreptrackerapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you create a layout file named activity_display.xml with a TextView inside it
        setContentView(R.layout.activity_display)

        val displayTextView = findViewById<TextView>(R.id.DisplayTextView)

        // 1. EXTRACT THE PARALLEL ARRAYS FROM THE INTENT
        val details = intent.getStringArrayExtra("EXTRA_DETAILS") ?: emptyArray()
        val categories = intent.getStringArrayExtra("EXTRA_CATEGORIES") ?: emptyArray()
        val quantities = intent.getIntArrayExtra("EXTRA_QUANTITIES") ?: intArrayOf()
        val comments = intent.getStringArrayExtra("EXTRA_COMMENTS") ?: emptyArray()

        // 2. LOOP THROUGH THE PARALLEL ARRAYS SIMULTANEOUSLY USING A SHARED INDEX
        val stringBuilder = StringBuilder()

        for (i in details.indices) {
            // Only format and display if the user actually typed a name
            if (details[i].isNotEmpty()) {
                stringBuilder.append("Item ${i + 1}:\n")
                stringBuilder.append("• Name: ${details[i]}\n")
                stringBuilder.append("• Category: ${categories[i]}\n")
                stringBuilder.append("• Quantity: ${quantities[i]}\n")
                stringBuilder.append("• Note: \"${comments[i]}\"\n\n")
            }
        }

        // 3. PRINT THE FORMATTED LIST TO THE SCREEN
        if (stringBuilder.isEmpty()) {
            displayTextView.text = "No items were added."
        } else {
            displayTextView.text = stringBuilder.toString()
        }
    }
}
