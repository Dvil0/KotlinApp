package com.dv.kotlin.mylibraryaar.interfaces

import androidx.appcompat.widget.Toolbar


interface IToolbar{
    fun toolbarToLoad( toolbar: Toolbar? )
    fun enableHomeDisplay( value: Boolean )
}