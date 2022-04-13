package com.study.crawling.util

import android.view.View
import androidx.databinding.BindingAdapter


//Progress Bar
@BindingAdapter("visibleIf")
fun View.visibleIf(value: Boolean){
    visibility = when(value){
        true -> View.VISIBLE
        else -> View.INVISIBLE
    }
}