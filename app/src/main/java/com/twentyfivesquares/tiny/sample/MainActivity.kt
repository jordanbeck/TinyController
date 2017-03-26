package com.twentyfivesquares.tiny.sample

import android.os.Bundle
import com.twentyfivesquares.tiny.TinyActivity
import com.twentyfivesquares.tiny.sample.controller.MainController

class MainActivity : TinyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addController(MainController(this), true)
    }
}
