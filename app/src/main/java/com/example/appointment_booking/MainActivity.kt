package com.example.appointment_booking

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button



class MainActivity : AppCompatActivity() {

    private lateinit var btnBook: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBook = findViewById(R.id.btnBook)

        btnBook.setOnClickListener {

            val intent = Intent(this, BookAppointmentActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}