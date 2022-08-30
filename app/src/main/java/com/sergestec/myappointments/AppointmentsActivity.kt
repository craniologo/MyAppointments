package com.sergestec.myappointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergestec.myappointments.model.Appointment
import kotlinx.android.synthetic.main.activity_appointments.*

class AppointmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        val appointments = ArrayList<Appointment>()
        appointments.add(
            Appointment(1, "Médico Test", "12/12/2022", "3:00 PM" )
        )
        appointments.add(
            Appointment(2, "Médico Na", "10/12/2022", "8:00 PM" )
        )
        appointments.add(
            Appointment(3, "Médico Mo", "1/12/2022", "10:00 PM" )
        )

        rvAppointments.layoutManager = LinearLayoutManager(this)    // GridLayoutManager
        rvAppointments.adapter = AppointmentAdapter(appointments)
    }
}