package com.sergestec.myappointments.io.response

import com.sergestec.myappointments.model.User

data class LoginResponse(val success: Boolean, val user: User, val jwt: String)