package com.example.quizlung

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val score = intent.getIntExtra("SCORE", 0)

        val scoreText: TextView = findViewById(R.id.scoreText)
        scoreText.text = getString(R.string.score, score)

        val closeScore: ImageView = findViewById(R.id.btnClose)
        closeScore.setOnClickListener(){
            val intent = Intent(this@ScoreActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
