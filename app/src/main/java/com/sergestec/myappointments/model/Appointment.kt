package com.sergestec.myappointments.model

import com.google.gson.annotations.SerializedName

/*
{
    "id": 1,
    "description": "De los ojos",
    "scheduled_date": "2022-08-18",
    "type": "Consulta",
    "created_at": "2022-08-13 01:07:35",
    "status": "Cancelada",
    "scheduled_time_12": "9:00 AM",
    "specialty": {
        "id": 3,
        "name": "Neurología"
    },
    "doctor": {
        "id": 3,
        "name": "Médico Test"
    }
}
 */

data class Appointment (
    val id: Int,
    val description: String,
    val type: String,
    val status: String,

    @SerializedName("scheduled_date") val scheduledDate: String,
    @SerializedName("scheduled_time_12") val scheduledTime: String,
    @SerializedName("created_at") val createdAt: String,

    val specialty: Specialty,
    val doctor: Doctor
)