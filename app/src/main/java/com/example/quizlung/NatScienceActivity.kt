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

class NatScienceActivity : AppCompatActivity() {
    private val questions = arrayOf(
        "1. Apa gas yang paling melimpah di atmosfer Bumi?",
        "2. Apa yang dimaksud dengan fotosintesis?",
        "3. Siapa yang dikenal sebagai bapak teori evolusi?",
        "4. Apa unsur yang paling melimpah di dalam tubuh manusia?",
        "5. Apa yang dimaksud dengan siklus air?",
        "6. Apa yang menyebabkan terjadinya gempa bumi?",
        "7. Apa yang dimaksud dengan biodiversitas?",
        "8. Apa nama proses di mana air berubah dari bentuk cair menjadi gas?",
        "9. Apa fungsi utama dari akar pada tanaman?",
        "10. Siapa penemu hukum gravitasi?"
    )

    private val options = arrayOf(
        arrayOf("A) Oksigen", "B) Nitrogen", "C) Karbon dioksida", "D) Argon"),
        arrayOf("A) Proses membuat makanan oleh tanaman", "B) Proses pencernaan makanan", "C) Proses respirasi", "D) Proses reproduksi"),
        arrayOf("A) Albert Einstein", "B) Charles Darwin", "C) Isaac Newton", "D) Gregor Mendel"),
        arrayOf("A) Oksigen", "B) Karbon", "C) Kalsium", "D) Air"),
        arrayOf("A) Proses penguapan air", "B) Proses perputaran air di alam", "C) Proses penyimpanan air", "D) Proses pencemaran air"),
        arrayOf("A) Pergerakan lempeng tektonik", "B) Perubahan cuaca", "C) Aktivitas manusia", "D) Penurunan permukaan laut"),
        arrayOf("A) Keanekaragaman hayati", "B) Kepadatan populasi", "C) Ketidakstabilan ekosistem", "D) Penurunan spesies"),
        arrayOf("A) Penguapan", "B) Kondensasi", "C) Sublimasi", "D) Presipitasi"),
        arrayOf("A) Menyerap nutrisi", "B) Menyimpan energi", "C) Menyediakan dukungan struktural", "D) Menyimpan air"),
        arrayOf("A) Nikola Tesla", "B) Isaac Newton", "C) Albert Einstein", "D) Galileo Galilei")
    )

    private val correctAnswers = intArrayOf(1, 0, 1, 1, 1, 0, 0, 0, 0, 1)

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
        setContentView(R.layout.activity_nat_science)
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