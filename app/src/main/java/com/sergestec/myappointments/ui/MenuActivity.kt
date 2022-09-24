package com.sergestec.myappointments.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sergestec.myappointments.util.PreferenceHelper
import com.sergestec.myappointments.util.PreferenceHelper.set
import com.sergestec.myappointments.util.PreferenceHelper.get
import com.sergestec.myappointments.R
import com.sergestec.myappointments.io.ApiService
import kotlinx.android.synthetic.main.activity_menu.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
//import javax.security.auth.callback.Callback

class MenuActivity : AppCompatActivity() {

    private val apiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnCreateAppointment.setOnClickListener {
            val intent = Intent(this, CreateAppointmentActivity::class.java)
            startActivity(intent)
        }

        btnMyAppointments.setOnClickListener {
            val intent = Intent(this, AppointmentsActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            perFormLogout()
        }
    }

    private fun perFormLogout() {
        val jwt = preferences["jwt", ""]
        val call = apiService.postLogout("Bearer: $jwt")
        call.enqueue(object: Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                clearSessionPreferences()

                val intent = Intent(this@MenuActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun clearSessionPreferences() {
        preferences["jwt"] = ""
    }
}