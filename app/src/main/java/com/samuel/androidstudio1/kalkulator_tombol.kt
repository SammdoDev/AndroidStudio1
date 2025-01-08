package com.samuel.androidstudio1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Stack
import android.widget.Toast
import android.widget.ImageView
import android.content.Intent
import androidx.cardview.widget.CardView


class kalkulator_tombol : AppCompatActivity() {
    // langkah 1
    // deklarasi variabel

    lateinit var display: EditText
    lateinit var tombolTujuh: CardView
    lateinit var tombolDelapan: CardView
    lateinit var tombolSembilan: CardView
    lateinit var tombolKali: CardView
    lateinit var tombolEmpat: CardView
    lateinit var tombolLima: CardView
    lateinit var tombolEnam: CardView
    lateinit var tombolBagi: CardView
    lateinit var tombolSatu: CardView
    lateinit var tombolDua: CardView
    lateinit var tombolTiga: CardView
    lateinit var tombolKurang: CardView
    lateinit var tombolClear: CardView
    lateinit var tombolNol: CardView
    lateinit var tombolKoma: CardView
    lateinit var tombolTambah: CardView
    lateinit var tombolSamaDengan: CardView
    lateinit var btPlusMinus: CardView
    lateinit var percent: CardView
    lateinit var tombolBackspace: CardView

    fun evaluateExpression(expression: String): Double {
        val tokens = expression.toCharArray()
        val values = Stack<Double>()
        val operators = Stack<Char>()
        var i = 0

        while (i < tokens.size) {
            when {
                tokens[i] == ' ' -> i++  // Melewati spasi kosong
                tokens[i] in '0'..'9' || tokens[i] == '.' -> {
                    val sb = StringBuilder()
                    while (i < tokens.size && (tokens[i] in '0'..'9' || tokens[i] == '.')) {
                        sb.append(tokens[i++])
                    }
                    values.push(sb.toString().toDouble())
                    continue
                }
                tokens[i] == '(' -> operators.push(tokens[i])
                tokens[i] == ')' -> {
                    while (operators.peek() != '(') {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()))
                    }
                    operators.pop()
                }
                tokens[i] in arrayOf('+', '-', '*', '/') -> {
                    while (operators.isNotEmpty() && hasPrecedence(tokens[i], operators.peek())) {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()))
                    }
                    operators.push(tokens[i])
                }
            }
            i++
        }

        while (operators.isNotEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()))
        }

        return values.pop()
    }

    fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
    }

    fun applyOperation(op: Char, b: Double, a: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                if (b == 0.0) throw UnsupportedOperationException("Cannot divide by zero")
                a / b
            }
            else -> 0.0
        }
    }

    // oncreate adalah sebuah fungsi yg pasti ada karena dia
    // adalah fungsi  yang pertama kali dipanggil ketika sabuah activity dijanlankan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kalkulator_tombol)

        //langkah3
        //memanggil fun init() dari fun oncreate

        init()
        pencet()

        val imageView: ImageView? = findViewById(R.id.back_kalkulator)
        imageView?.setOnClickListener {
            val intent = Intent(this@kalkulator_tombol, Beranda::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //langkah 2
    //membuat fun init()
    //digunakan untuk mengisikan lateinit
    //yang sudah kita buat pada langkah 1

    fun init() {
        display = findViewById(R.id.display)
        tombolTujuh = findViewById(R.id.tombol_tujuh)
        tombolDelapan = findViewById(R.id.tombol_delapan)
        tombolSembilan = findViewById(R.id.tombol_sembilan)
        tombolKali = findViewById(R.id.tombol_kali)
        tombolEmpat = findViewById(R.id.tombol_empat)
        tombolLima = findViewById(R.id.tombol_lima)
        tombolEnam = findViewById(R.id.tombol_enam)
        tombolBagi = findViewById(R.id.tombol_bagi)
        tombolSatu = findViewById(R.id.tombol_satu)
        tombolDua = findViewById(R.id.tombol_dua)
        tombolTiga = findViewById(R.id.tombol_tiga)
        tombolKurang = findViewById(R.id.tombol_kurang)
        tombolClear = findViewById(R.id.tombol_clear)
        tombolNol = findViewById(R.id.tombol_nol)
        tombolKoma = findViewById(R.id.tombol_koma)
        tombolTambah = findViewById(R.id.tombol_tambah)
        tombolSamaDengan = findViewById(R.id.tombol_sama_dengan)
        btPlusMinus = findViewById(R.id.btPlusMinus)
        percent = findViewById(R.id.percent)
        tombolBackspace = findViewById(R.id.tombol_backspace)
    }
//langkah 4
    //aksi saat
    private fun pencet() {
    tombolTujuh.setOnClickListener {
        display.append("7")
    }
    tombolDelapan.setOnClickListener {
        display.append("8")
    }
    tombolSembilan.setOnClickListener {
        display.append("9")
    }
    tombolKali.setOnClickListener {
        display.append("*")
    }
    tombolEmpat.setOnClickListener {
        display.append("4")
    }
    tombolLima.setOnClickListener {
        display.append("5")
    }
    tombolEnam.setOnClickListener {
        display.append("6")
    }
    tombolBagi.setOnClickListener {
        display.append("/")
    }
    tombolSatu.setOnClickListener {
        display.append("1")
    }
    tombolDua.setOnClickListener {
        display.append("2")
    }
    tombolTiga.setOnClickListener {
        display.append("3")
    }
    tombolKurang.setOnClickListener {
        val currentText = display.text.toString()
        if (currentText.isEmpty() || currentText.last() in arrayOf('+', '-', '*', '/')) {
            display.append("-")
        } else {
            display.append("-")
        }
    }

    tombolClear.setOnClickListener {
        display.text.clear()
    }

    tombolNol.setOnClickListener {
        display.append("0")
    }
    tombolKoma.setOnClickListener {
        display.append(".")
    }
    tombolTambah.setOnClickListener {
        display.append("+")
    }
    tombolSamaDengan.setOnClickListener {
        val currentText = display.text.toString()

        if (currentText.isNotEmpty()) {
            try {
                // Mengevaluasi ekspresi yang dimasukkan
                val result = evaluateExpression(currentText)

                // Menampilkan hasil perhitungan
                display.setText(result.toString())

            } catch (e: Exception) {
                // Menangani error dan menampilkan pesan kesalahan
                Toast.makeText(this, "Terjadi kesalahan dalam perhitungan", Toast.LENGTH_SHORT).show()
                display.setText("Error")
            }
        } else {
            Toast.makeText(this, "Input kosong", Toast.LENGTH_SHORT).show()
        }
    }


    btPlusMinus.setOnClickListener {
        val currentText = display.text.toString()

        if (currentText.isNotEmpty()) {
            // Memeriksa apakah angka pertama negatif
            if (currentText[0] == '-') {
                display.setText(currentText.substring(1)) // Hapus tanda minus
            } else {
                display.setText("-$currentText") // Tambahkan tanda minus
            }
        }
    }

    tombolBackspace.setOnClickListener {
        val currentText = display.text.toString()
        if (currentText.isNotEmpty()) {
            // Hapus karakter terakhir
            display.setText(currentText.substring(0, currentText.length - 1))
        }
    }


    percent.setOnClickListener {
        val currentText = display.text.toString()

        if (currentText.isNotEmpty()) {
            try {
                // Menghitung persen dari nilai yang ada
                val result = evaluateExpression(currentText) / 100
                display.setText(result.toString())
            } catch (e: Exception) {
                Toast.makeText(this, "Terjadi kesalahan saat menghitung persen", Toast.LENGTH_SHORT).show()
                display.setText("Error") // Menampilkan error
            }
        } else {
            Toast.makeText(this, "Input kosong", Toast.LENGTH_SHORT).show()
        }
    }


}

}