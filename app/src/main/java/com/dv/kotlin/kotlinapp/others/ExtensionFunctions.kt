package com.dv.kotlin.kotlinapp.others


import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.dv.kotlin.kotlinapp.R
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

fun Int.isNatural() = this >= 0

fun Activity.toast( message: CharSequence, duration: Int = Toast.LENGTH_LONG) = Toast.makeText( this, message, duration).show()

fun Activity.toast( resourceId: Int, duration: Int = Toast.LENGTH_LONG) = Toast.makeText( this, resourceId, duration).show()

fun Activity.snackBar(
    message: CharSequence,
    view: View? = findViewById(R.id.container),
    duration: Int = Snackbar.LENGTH_LONG,
    action: String? = null,
    actionEvent: ( v: View ) -> Unit = {}) {

    if( view != null ){
        val snackbar = Snackbar.make( view, message, duration)

        if( !action.isNullOrEmpty() ){
            snackbar.setAction( action, actionEvent)
        }
        snackbar.show()
    }
}

fun ViewGroup.inflate( layoutId: Int ) = LayoutInflater.from( context ).inflate(layoutId, this, false)!!

fun ImageView.loadByUrl( url: String) = Picasso.get().load( url ).placeholder( R.drawable.ic_mtrl_chip_close_circle ).error( R.drawable.ic_mtrl_chip_close_circle ).into( this )

fun ImageView.loadBySrc( src: Int) = Picasso.get().load( src ).placeholder( R.drawable.ic_mtrl_chip_close_circle ).error( R.drawable.ic_mtrl_chip_close_circle ).into( this )

inline fun < reified T: Activity>Activity.goToActivity( noinline init: Intent.() -> Unit = {} ){
    val intent = Intent( this, T::class.java)
    intent.init()
    startActivity( intent )
}

fun Activity.goToActivityForResult( action: String, requestCode: Int, init: Intent.() -> Unit = {} ){
    val intent = Intent( action )
    intent.init()
    startActivityForResult( intent, requestCode )
}