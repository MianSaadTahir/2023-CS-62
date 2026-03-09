package com.example.appointment_booking

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button
import android.content.Intent

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var btnNewAppointment: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val tvSummary = findViewById<TextView>(R.id.tvSummary)
        btnNewAppointment = findViewById(R.id.btnNewAppointment)
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val type = intent.getStringExtra("type")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val gender = intent.getStringExtra("gender")

        tvSummary.text = """
Name: $name
Phone: $phone
Email: $email
Appointment Type: $type
Date: $date
Time: $time
Gender: $gender
""".trimIndent()
        btnNewAppointment.setOnClickListener {

            val intent = Intent(this, BookAppointmentActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}