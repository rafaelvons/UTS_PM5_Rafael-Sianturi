package com.example.quizlung

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private fun setupCardListeners() {
        findViewById<CardView>(R.id.cvCompSci).setOnClickListener {
            startActivity(Intent(this, CompSciActivity::class.java))
        }
        findViewById<CardView>(R.id.cvSocKnow).setOnClickListener {
            startActivity(Intent(this, SocknowActivity::class.java))
        }
        findViewById<CardView>(R.id.cvNatSci).setOnClickListener {
            startActivity(Intent(this, NatScienceActivity::class.java))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setupCardListeners()
    }
}
