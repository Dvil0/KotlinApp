package com.dv.kotlin.kotlinapp.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.dv.kotlin.kotlinapp.R
import com.dv.kotlin.kotlinapp.fragments.ArrivalsFragment
import com.dv.kotlin.kotlinapp.fragments.DeparturesFragment
import com.dv.kotlin.kotlinapp.fragments.HomeFragment
import com.dv.kotlin.kotlinapp.others.toast
import com.dv.kotlin.mylibraryaar.ToolbarActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarToLoad( toolbar as Toolbar? )

        setNavDrawer()
        setUserHeaderInformation()

        if( savedInstanceState == null ){
            fragmentTransaction( HomeFragment() )
            navView.menu.getItem( 0 ).isChecked = true
        }
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


    private fun loadFragmentById( id: Int){
        when( id ){
            R.id.navHome -> fragmentTransaction( HomeFragment() )
            R.id.navDepartures -> fragmentTransaction( DeparturesFragment() )
            R.id.navArrivals -> fragmentTransaction( ArrivalsFragment() )
        }
    }

    private fun showMessageNavItemSelectedById( id: Int){
        when( id ){
            R.id.navProfile -> toast( "Profile" )
            R.id.navSettings -> toast( "Settings" )
        }
    }

    private fun setUserHeaderInformation(){
        val name = navView.getHeaderView( 0 ).findViewById<TextView>( R.id.txtName )
        val email = navView.getHeaderView( 0 ).findViewById<TextView>( R.id.txtEmail )

        name?.let{ name.text = getString( R.string.user_name ) }
        email?.let{ email.text = getString( R.string.user_email ) }
    }

    override fun onBackPressed() {
        if( drawerLayout.isDrawerOpen( GravityCompat.START) ) {
            drawerLayout.closeDrawer( GravityCompat.START )
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showMessageNavItemSelectedById( item.itemId )
        loadFragmentById( item.itemId )
        drawerLayout.closeDrawer( GravityCompat.START )
        return true
    }

}
