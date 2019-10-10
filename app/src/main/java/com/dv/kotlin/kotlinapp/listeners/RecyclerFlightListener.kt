package com.dv.kotlin.kotlinapp.listeners

import com.dv.kotlin.kotlinapp.models.Flight

interface RecyclerFlightListener{

    fun onClick( flight: Flight, position: Int )
    fun onDelete( flight: Flight, position: Int )
}