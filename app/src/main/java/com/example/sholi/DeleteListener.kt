package com.example.sholi

import android.widget.Button
/*
Das Interface dient der übergabe der gesamten delete funktionalität aus dem Adapter in die MainActivity
 */
interface DeleteListener {
    fun onDeleteClick(position: Int)
}