package com.samuel.androidstudio1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class kalkulator : AppCompatActivity() {
    //Langkah 1
    //Inisialisasi Variable
    //Varible dari komponen yang dibutuhkan
    lateinit var bilangan1: EditText
    lateinit var bilangan2: EditText
    lateinit var operasi: Spinner
    lateinit var hitung: Button

    //Langkah 5
    //membuat variable penampung nilai bilangan
    var angka1: Double = 0.0
    var angka2: Double = 0.0
    var hasil: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.constraintkalkulator)
        //langkah 4
        //memanggil fun init
        init()

        //langkah 7
        //button fun hitung
        hitung.setOnClickListener{
            hitung()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //Langkah 2
    //Membuat Function
    //untuk singkron komponen

    fun init() {
        //langkah3
        //Mencocokan variable dengan komponen
        bilangan1 = findViewById(R.id.input1)
        bilangan2 = findViewById(R.id.input2)

        operasi = findViewById(R.id.spinner)
        hitung = findViewById(R.id.button)
    }

    //Langkah 6
    //Membuat fun hitung
    //Menghitung operasi Aritmatika
    fun hitung() {
        // Ambil nilai dari EditText dan konversi menjadi Double
        val input1Text = bilangan1.text.toString()
        val input2Text = bilangan2.text.toString()

        // Konversi ke Double dengan pengecekan null (untuk menghindari error parsing)
        angka1 = bilangan1.text.toString().toDouble()
        angka2 = bilangan2.text.toString().toDouble()

        // Cek operasi yang dipilih dan lakukan kalkulasi
        when (operasi.selectedItemPosition) {
            0 -> hasil = angka1 + angka2  // operasi tambah
            1 -> hasil = angka1 - angka2  // operasi kurang
            2 -> hasil = angka1 * angka2  // operasi kali
            3 -> if (angka2 != 0.0) {  // operasi bagi, cek pembagian dengan 0
                hasil = angka1 / angka2
            } else {
                hasil = 0.0
                Toast.makeText(this@kalkulator, "Tidak bisa membagi dengan nol!", Toast.LENGTH_SHORT).show()
                return
            }
            else -> hasil = 0.0
        }

        // Tampilkan hasil menggunakan Toast
        Toast.makeText(this@kalkulator, "Hasil = $hasil", Toast.LENGTH_SHORT).show()
    }

}