package com.example.quizlung

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class CompSciActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "1. Apa yang dilakukan oleh algoritma Bubble Sort?",
        "2. Struktur data yang paling efisien untuk LIFO?",
        "3. Apa perbedaan antara kompiler dan interpreter?",
        "4. Apa yang dimaksud dengan Big Data?",
        "5. Algoritma untuk jalur terpendek dalam graf adalah?",
        "6. Ubah 65 ke dalam binary",
        "7. Apa yang dilakukan algoritma BFS?",
        "8. Apa definisi Polimorfisme dalam OOP?",
        "9. Apa fungsi utama dari cache pada prosesor?",
        "10. Perangkat untuk akses jaringan adalah?"
    )

    private val options = arrayOf(
        arrayOf("A) Mengurutkan data", "B) Mencari elemen terbesar", "C) Menghapus elemen ganda", "D) Menghitung elemen"),
        arrayOf("A) Array", "B) Stack", "C) Queue", "D) Linked List"),
        arrayOf("A) Menerjemahkan bersama", "B) Kompiler untuk bahasa rendah", "C) Kompiler terjemah sekaligus, interpreter baris", "D) Interpreter lebih cepat"),
        arrayOf("A) Jenis data besar", "B) Data kompleks", "C) Data rahasia", "D) Data kecil namun penting"),
        arrayOf("A) Prim's", "B) Kruskal's", "C) Dijkstra's", "D) Bellman-Ford"),
        arrayOf("A) 01100001", "B) 01000001", "C) 00100001", "D) 00100101"),
        arrayOf("A) Jalur terpendek", "B) Traversal bertingkat", "C) Menjadi pohon biner", "D) Semua jalur sekaligus"),
        arrayOf("A) Objek punya banyak bentuk", "B) Pewarisan", "C) Enkapsulasi data", "D) Deklarasi variabel"),
        arrayOf("A) Data sementara dari RAM", "B) Hubungkan prosesor ke hard drive", "C) Akses data cepat", "D) Proses semua instruksi"),
        arrayOf("A) Router", "B) Switch", "C) Access Point", "D) Hub")
    )

    private val correctAnswers = intArrayOf(0, 1, 2, 0, 2, 1, 0, 0, 2, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp_sci)

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

            // Kirim skor ke ScoreActivity
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
        }

        // Mengatur tombol close
        val closeScore: ImageView = findViewById(R.id.btnClose)
        closeScore.setOnClickListener {
            showExitConfirmationDialog() // Menampilkan dialog konfirmasi
        }
    }

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
}
