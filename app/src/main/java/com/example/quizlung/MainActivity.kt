package com.example.quizlung

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val compSci: CardView = findViewById(R.id.cvCompSci)
        compSci.setOnClickListener {
            val intent = Intent(this@MainActivity, CompSciActivity::class.java)
            startActivity(intent)
        }

        val socKnow: CardView = findViewById(R.id.cvSocKnow)
        socKnow.setOnClickListener {
            val intent = Intent(this@MainActivity, SocknowActivity::class.java)
            startActivity(intent)
        }

        val natSci: CardView = findViewById(R.id.cvNatSci)
        natSci.setOnClickListener {
            val intent = Intent(this@MainActivity, NatScienceActivity::class.java)
            startActivity(intent)
        }
    }
}