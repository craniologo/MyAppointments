package com.sergestec.myappointments.io

//import android.telecom.Call
import com.sergestec.myappointments.io.response.LoginResponse
import com.sergestec.myappointments.io.response.SimpleResponse
import com.sergestec.myappointments.model.Appointment
import com.sergestec.myappointments.model.Doctor
import com.sergestec.myappointments.model.Schedule
import com.sergestec.myappointments.model.Specialty
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("specialties")
    fun getSpecialties(): Call<ArrayList<Specialty>>

    @GET("specialties/{specialty}/doctors")
    fun getDoctors(@Path("specialty") specialtyId: Int): Call<ArrayList<Doctor>>

    @GET("schedule/hours")
    fun getHours(@Query("doctor_id") doctorId: Int, @Query("date") date: String):
            Call<Schedule>

    @POST("login")
    fun postLogin(@Query("email") email: String, @Query("password") password: String):
            Call<LoginResponse>

    @POST("logout")
    fun postLogout(@Header ("Authorization") authHeader: String): Call<Void>

    @GET("appointments")
    fun getAppointments(@Header ("Authorization") authHeader: String):
            Call<ArrayList<Appointment>>

    @POST("appointments")
    @Headers("Accept: application/java")
    fun storeAppointment(
        @Header ("Authorization") authHeader: String,
        @Query("description") description: String,
        @Query("specialty_id") specialtyId: Int,
        @Query("doctor_id") doctorId: Int,
        @Query("scheduled_date") scheduledDate: String,
        @Query("scheduled_time")scheduledTime: String,
        @Query("type") type: String
    ): Call<SimpleResponse>

    @POST("register")
    @Headers("Accept: application/java")
    fun postRegister(
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("password_confirmation") password_confirmation: String
    ): Call<LoginResponse>

    companion object Factory {
        private const val BASE_URL = "http://159.223.98.199/api/"
        //private const val BASE_URL = "http://10.0.2.2:8000/api/"

        fun create(): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}