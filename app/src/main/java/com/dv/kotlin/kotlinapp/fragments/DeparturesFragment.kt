package com.dv.kotlin.kotlinapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dv.kotlin.kotlinapp.R
import com.dv.kotlin.kotlinapp.adapters.FlightAdapter
import com.dv.kotlin.kotlinapp.listeners.RecyclerFlightListener
import com.dv.kotlin.kotlinapp.models.Flight
import com.dv.kotlin.kotlinapp.others.toast
import kotlinx.android.synthetic.main.fragment_departures.view.*

class DeparturesFragment : Fragment() {

    private val list: ArrayList<Flight> by lazy{ getFlights() }
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FlightAdapter
    private val layoutManager by lazy{ LinearLayoutManager( context ) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTitle( R.string.departures_fragment_title )
        val rootView = inflater.inflate(R.layout.fragment_departures, container, false)
        recycler = rootView.recyclerView as RecyclerView
        setRecyclerView()

        return rootView
    }

    private fun setRecyclerView(){
        recycler.setHasFixedSize( true )
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layoutManager
        adapter = FlightAdapter( list, object: RecyclerFlightListener{
            override fun onClick(flight: Flight, position: Int) {
                activity?.toast( "Let's go to ${flight.city}" )
            }

            override fun onDelete(flight: Flight, position: Int) {
                list.remove( flight )
                adapter.notifyItemRemoved( position )
                activity?.toast( "${flight.city} has been removed" )
            }
        } )

        recycler.adapter = adapter
    }

    private fun getFlights(): ArrayList<Flight>{
        return object: ArrayList<Flight>(){
            init {
                add( Flight( 1, "Santiago de Cali", R.drawable.cali ) )
                add( Flight( 2, "Atlanta", R.drawable.atlanta ) )
                add( Flight( 3, "Beverly Hills", R.drawable.beverly ) )
                add( Flight( 4, "California", R.drawable.california ) )
                add( Flight( 5, "Germany", R.drawable.germany ) )
                add( Flight( 6, "Ireland", R.drawable.ireland ) )
                add( Flight( 7, "San Francisco", R.drawable.sanfrancisco ) )
                add( Flight( 8, "Norway", R.drawable.norway ) )
                add( Flight( 9, "Turkey", R.drawable.turkey ) )
                add( Flight( 10, "Sao Pablo", R.drawable.saopablo ) )
            }
        }
    }

}
