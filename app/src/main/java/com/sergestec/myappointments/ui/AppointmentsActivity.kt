package com.sergestec.myappointments.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergestec.myappointments.R
import com.sergestec.myappointments.io.ApiService
import com.sergestec.myappointments.model.Appointment
import com.sergestec.myappointments.util.PreferenceHelper
import com.sergestec.myappointments.util.PreferenceHelper.get
import com.sergestec.myappointments.util.toast
import kotlinx.android.synthetic.main.activity_appointments.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class AppointmentsActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

    private val appointmentAdapter = AppointmentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        loadAppointments()

        rvAppointments.layoutManager = LinearLayoutManager(this)
        rvAppointments.adapter = appointmentAdapter
    }

    private fun loadAppointments () {
        val jwt = preferences["jwt", ""]
        val call = apiService.getAppointments("Bearer $jwt")
        call.enqueue(object: Callback<ArrayList<Appointment>> {
            override fun onFailure(call: Call<ArrayList<Appointment>>, t: Throwable) {
                toast(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ArrayList<Appointment>>,
                response: Response<ArrayList<Appointment>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        appointmentAdapter.appointments = it
                        appointmentAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}