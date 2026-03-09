package com.example.appointment_booking

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import java.util.*
import android.widget.*
import android.util.Patterns
class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var spinnerType: Spinner
    private lateinit var tvDate: TextView
    private lateinit var tvTime: TextView
    private lateinit var btnDate: Button
    private lateinit var btnTime: Button
    private lateinit var rgGender: RadioGroup
    private lateinit var cbTerms: CheckBox
    private lateinit var btnConfirm: Button

    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        spinnerType = findViewById(R.id.spinnerType)
        tvDate = findViewById(R.id.tvDate)
        tvTime = findViewById(R.id.tvTime)
        btnDate = findViewById(R.id.btnDate)
        btnTime = findViewById(R.id.btnTime)
        rgGender = findViewById(R.id.rgGender)
        cbTerms = findViewById(R.id.cbTerms)
        btnConfirm = findViewById(R.id.btnConfirm)

        val types = arrayOf(
            "Doctor Consultation",
            "Dentist Appointment",
            "Eye Specialist",
            "Skin Specialist",
            "General Checkup"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            types
        )

        spinnerType.adapter = adapter

        btnDate.setOnClickListener {

            val cal = Calendar.getInstance()

            DatePickerDialog(this,
                { _, y, m, d ->
                    selectedDate = "$d/${m+1}/$y"
                    tvDate.text = selectedDate
                },

                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnTime.setOnClickListener {

            val cal = Calendar.getInstance()

            TimePickerDialog(this,
                { _, h, m ->
                    selectedTime = "$h:$m"
                    tvTime.text = selectedTime
                },
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        btnConfirm.setOnClickListener {

            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val type = spinnerType.selectedItem.toString()
//validations
            if (name.isEmpty()) {
                etName.error = "Enter name"
                return@setOnClickListener
            }
            if (name.length<3) {
                etName.error = "Enter valid name"
                return@setOnClickListener
            }

            if (phone.isEmpty()) {
                etPhone.error = "Enter phone number"
                return@setOnClickListener
            }
            if (phone.length < 11) {
                etPhone.error = "Enter a valid phone number"
                etPhone.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                etEmail.error = "Enter email"
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Enter a valid email"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (!cbTerms.isChecked) {
                Toast.makeText(this,"Accept terms first",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val genderId = rgGender.checkedRadioButtonId
            if (genderId == -1) {
                Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val gender = findViewById<RadioButton>(genderId).text.toString()
            if (selectedDate.isEmpty()) {
                Toast.makeText(this, "Please select appointment date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (selectedTime.isEmpty()) {
                Toast.makeText(this, "Please select appointment time", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ConfirmationActivity::class.java)

            intent.putExtra("name", name)
            intent.putExtra("phone", phone)
            intent.putExtra("email", email)
            intent.putExtra("type", type)
            intent.putExtra("date", selectedDate)
            intent.putExtra("time", selectedTime)
            intent.putExtra("gender", gender)

            startActivity(intent)
        }
    }
}