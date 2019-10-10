package com.dv.kotlin.kotlinapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dv.kotlin.kotlinapp.R
import com.dv.kotlin.kotlinapp.listeners.RecyclerFlightListener
import com.dv.kotlin.kotlinapp.models.Flight
import com.dv.kotlin.kotlinapp.others.inflate
import com.dv.kotlin.kotlinapp.others.loadBySrc
import com.dv.kotlin.kotlinapp.others.loadByUrl
import kotlinx.android.synthetic.main.recycler_flight.view.*

class FlightAdapter(
    private val flights: List<Flight>,
    private val listener:RecyclerFlightListener
): RecyclerView.Adapter<FlightAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( parent.inflate( R.layout.recycler_flight ) )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind( flights[ position ], listener )
    }

    override fun getItemCount(): Int = flights.size

    class ViewHolder(itemView: View ): RecyclerView.ViewHolder( itemView ){

        fun bind( flight: Flight, listener: RecyclerFlightListener){
            with( itemView ){
                txtCity.text = flight.city
                imgCity.loadBySrc( flight.imgResource )

                // Click events
                setOnClickListener{ listener.onClick( flight, adapterPosition)}
                btnDelete.setOnClickListener { listener.onDelete( flight, adapterPosition ) }
            }
        }
    }
}