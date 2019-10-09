package com.dv.kotlin.kotlinapp.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.dv.kotlin.kotlinapp.R
import com.dv.kotlin.kotlinapp.fragments.ArrivalsFragment
import com.dv.kotlin.kotlinapp.fragments.DeparturesFragment
import com.dv.kotlin.kotlinapp.fragments.HomeFragment
import com.dv.kotlin.mylibraryaar.ToolbarActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad( toolbar as Toolbar? )

        setNavDrawer()

        fragmentTransaction( HomeFragment() )
        navView.menu.getItem( 0 ).isChecked = true
    }

    private fun setNavDrawer(){
        val toggle = ActionBarDrawerToggle( this, drawerLayout, _toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener( toggle )
        toggle.syncState()
        navView.setNavigationItemSelectedListener ( this )
    }

    private fun fragmentTransaction( fragment: Fragment ){
        supportFragmentManager.beginTransaction()
            .replace( R.id.container, fragment )
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when( item.itemId ){
            R.id.navHome -> fragmentTransaction( HomeFragment() )
            R.id.navDepartures -> fragmentTransaction( DeparturesFragment() )
            R.id.navArrivals -> fragmentTransaction( ArrivalsFragment() )
        }

        return true
    }
}
