package com.sergestec.myappointments.model

data class Schedule(val morning: ArrayList<HourInterval>, val afternoon: ArrayList<HourInterval>) {
    val start: String
        get() {
            TODO()
        }
}