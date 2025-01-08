package com.samuel.androidstudio1

import android.content.Intent
import java.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KonversiSuhu : AppCompatActivity() {

    //    inisialisasi variabel
    lateinit var etSuhuAwal: EditText
    lateinit var tvHasilSuhuAkhir: TextView
    lateinit var spSuhuAwal: Spinner
    lateinit var spSuhuAkhir: Spinner
    lateinit var btKonversi: androidx.cardview.widget.CardView
    lateinit var btBersihkan: androidx.cardview.widget.CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_konversi_suhu)

        val imageView: ImageView? = findViewById(R.id.back_convert)
        imageView?.setOnClickListener {
            val intent = Intent(this@KonversiSuhu, Beranda::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()

        btKonversi.setOnClickListener {
            konversiSuhu()
        }

        btBersihkan.setOnClickListener {
            bersihkanForm()
        }
    }

    fun init() {
        // mencocokkan variabel dengan komponen di layout
        etSuhuAwal = findViewById(R.id.etSuhuAwal)
        tvHasilSuhuAkhir = findViewById(R.id.tvHasilSuhuAkhir)
        spSuhuAwal = findViewById(R.id.spSuhuAwal)
        spSuhuAkhir = findViewById(R.id.spSuhuAkhir)
        btKonversi = findViewById(R.id.btKonversi)
        btBersihkan = findViewById(R.id.btBersihkan)
    }

    fun konversiSuhu() {
        val decimalFormat = DecimalFormat("#.####")
        val suhuAwal = etSuhuAwal.text.toString().toDoubleOrNull() ?: return
        val suhuAwalString = spSuhuAwal.selectedItem.toString()
        val suhuAkhirString = spSuhuAkhir.selectedItem.toString()

        var hasil = 0.0
        when (suhuAwalString) {
            "Celcius" -> {
                hasil = when (suhuAkhirString) {
                    "Fahrenheit" -> (suhuAwal * 9 / 5) + 32
                    "Kelvin" -> suhuAwal + 273.15
                    else -> suhuAwal
                }
            }
            "Fahrenheit" -> {
                hasil = when (suhuAkhirString) {
                    "Celcius" -> (suhuAwal - 32) * 5 / 9
                    "Kelvin" -> (suhuAwal + 459.67) * 5 / 9
                    else -> suhuAwal
                }
            }
            "Kelvin" -> {
                hasil = when (suhuAkhirString) {
                    "Celcius" -> suhuAwal - 273.15
                    "Fahrenheit" -> (suhuAwal * 9 / 5) - 459.67
                    else -> suhuAwal
                }
            }
        }

        val hasilTerformat = decimalFormat.format(hasil)
        tvHasilSuhuAkhir.text = hasilTerformat
    }

    private fun bersihkanForm() {
        etSuhuAwal.text.clear()  // Menghapus input suhu awal
        spSuhuAwal.setSelection(0)  // Reset spinner suhu awal ke pilihan pertama
        spSuhuAkhir.setSelection(0)  // Reset spinner suhu akhir ke pilihan pertama
        tvHasilSuhuAkhir.text = "-"  // Reset hasil suhu akhir
    }
}