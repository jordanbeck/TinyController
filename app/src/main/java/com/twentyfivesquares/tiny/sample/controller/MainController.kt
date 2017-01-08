package com.twentyfivesquares.tiny.sample.controller

import android.content.Context
import com.twentyfivesquares.tiny.TinyController
import com.twentyfivesquares.tiny.sample.R

class MainController(context: Context?) : TinyController(context) {

    override fun getLayoutRes(): Int = R.layout.controller_main

}
