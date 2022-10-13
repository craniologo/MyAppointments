package com.sergestec.myappointments.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
//import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.sergestec.myappointments.R
import com.sergestec.myappointments.io.ApiService
import com.sergestec.myappointments.model.User
import com.sergestec.myappointments.util.PreferenceHelper
import com.sergestec.myappointments.util.PreferenceHelper.get
import com.sergestec.myappointments.util.PreferenceHelper.set
import com.sergestec.myappointments.util.toast
import kotlinx.android.synthetic.main.activity_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
/*
        val storeToken = intent.getBooleanExtra("store_token", false)
        if (storeToken)
            storeToken()

 */

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
/*
    private fun storeToken() {
        val jwt = preferences["jwt", ""]
        val authHeader = "Bearer $jwt"

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult ->
            val deviceToken = instanceIdResult.token

            val call = apiService.postToken(authHeader, deviceToken)
            call.enqueue(object: Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    toast(t.localizedMessage)
                }
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "Token registrado correctamente")
                    } else {
                        Log.d(TAG, "Hubo un problema al registrar el token")
                    }
                }
            })
        }
    }

 */

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

    companion object {
        private const val TAG = "MenuActivity"
    }
}