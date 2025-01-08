package com.samuel.androidstudio1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Beranda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beranda)

        val linearLayoutForm: LinearLayout? = findViewById(R.id.bt_form)
        linearLayoutForm?.setOnClickListener {
            val intent = Intent(this@Beranda, form::class.java)
            startActivity(intent)
        }

        val linearLayoutkalkulator: LinearLayout? = findViewById(R.id.bt_kalkulator)
        linearLayoutkalkulator?.setOnClickListener {
            val intent = Intent(this@Beranda, kalkulator_tombol::class.java)
            startActivity(intent)
        }

        val linearLayoutSuhu: LinearLayout? = findViewById(R.id.bt_suhu)
        linearLayoutSuhu?.setOnClickListener {
            val intent = Intent(this@Beranda, KonversiSuhu::class.java)
            startActivity(intent)
        }

        val linearLayoutProfile: LinearLayout? = findViewById(R.id.bt_akun)
        linearLayoutProfile?.setOnClickListener {
            val intent = Intent(this@Beranda, activity_profile::class.java)
            startActivity(intent)
        }

        val change: ImageView? = findViewById(R.id.bt_change)
        change?.setOnClickListener {
            val intent = Intent(this@Beranda, activity_profile::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            if (insets.isConsumed) return@setOnApplyWindowInsetsListener insets
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}