package com.example.quizlung

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SocknowActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "1. Apa nama ibukota negara Turki?",
        "2. Siapakah presiden pertama Indonesia?",
        "3. Apa lambang negara Indonesia?",
        "4. Kapan Indonesia merdeka?",
        "5. Apa nama alat musik tradisional dari Jawa Barat?",
        "6. Berapa jumlah pulau yang dimiliki Indonesia?",
        "7. Apa nama perjanjian yang mengakhiri penjajahan Belanda di Indonesia?",
        "8. Siapakah penulis buku 'Laskar Pelangi'?",
        "9. Apa nama danau terluas di Indonesia?",
        "10. Apa arti warna merah pada bendera Indonesia?"
    )

    private val options = arrayOf(
        arrayOf("A) Ankara", "B) Istanbul", "C) Izmir", "D) Bursa"),
        arrayOf("A) Soeharto", "B) Soekarno", "C) BJ Habibie", "D) Megawati Soekarnoputri"),
        arrayOf("A) Komodo", "B) Burung Garuda", "C) Harimau Sumatra", "D) Gajah"),
        arrayOf("A) 1 Juni 1945", "B) 17 Agustus 1945", "C) 10 November 1945", "D) 20 Mei 1908"),
        arrayOf("A) Angklung", "B) Gamelan", "C) Tifa", "D) Sasando"),
        arrayOf("A) 7.000", "B) 17.000", "C) 27.000", "D) 37.000"),
        arrayOf(
            "A) Perjanjian Roem-Royen",
            "B) Perjanjian Linggarjati",
            "C) Perjanjian Renville",
            "D) Konferensi Meja Bundar"
        ),
        arrayOf("A) Tere Liye", "B) Andrea Hirata", "C) Pramoedya Ananta Toer", "D) Dee Lestari"),
        arrayOf("A) Danau Singkarak", "B) Danau Toba", "C) Danau Poso", "D) Danau Maninjau"),
        arrayOf("A) Kesejahteraan", "B) Kesucian", "C) Keberanian", "D) Kesetiaan")
    )

    private val correctAnswers = intArrayOf(0, 1, 1, 1, 0, 1, 3, 1, 1, 2)

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Keluar")
        builder.setMessage("Apakah Anda yakin ingin keluar?")
        builder.setPositiveButton("Ya") { _, _ ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("Tidak") { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_socknow)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val closeQuiz: ImageView = findViewById(R.id.btnClose)
        closeQuiz.setOnClickListener {
            showExitConfirmationDialog()
        }

        val questionContainer: LinearLayout = findViewById(R.id.questionContainer)
        for (i in questions.indices) {
            val textView = TextView(this).apply {
                text = questions[i]
                textSize = 24f
                setPadding(0, 16, 0, 8)
            }
            questionContainer.addView(textView)

            val radioGroup = RadioGroup(this).apply {
                orientation = RadioGroup.VERTICAL
            }

            for (option in options[i]) {
                val radioButton = RadioButton(this).apply {
                    text = option
                    textSize = 20f
                    setPadding(8, 8, 8, 8)
                }
                radioGroup.addView(radioButton)
            }

            questionContainer.addView(radioGroup)
        }

        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            var score = 0

            for (i in questions.indices) {
                val selectedRadioButtonId = questionContainer.getChildAt(i * 2 + 1)
                val radioGroup = selectedRadioButtonId as RadioGroup
                val selectedId = radioGroup.checkedRadioButtonId

                if (selectedId != -1) {
                    val selectedRadioButton = findViewById<RadioButton>(selectedId)
                    val selectedAnswerIndex = radioGroup.indexOfChild(selectedRadioButton)

                    if (selectedAnswerIndex == correctAnswers[i]) {
                        score += 10
                    }
                }
            }

            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)


        }

    }

}