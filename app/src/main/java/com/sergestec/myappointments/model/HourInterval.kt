package com.sergestec.myappointments.model

data class HourInterval(val start: String, val end: String) {
    override fun toString(): String {
        return "$start - $end"
    }
}