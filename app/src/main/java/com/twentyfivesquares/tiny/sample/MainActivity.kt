package com.twentyfivesquares.tiny.sample

import android.os.Bundle
import com.twentyfivesquares.tiny.TinyActivity
import com.twentyfivesquares.tiny.sample.controller.MainController
import com.twentyfivesquares.tiny.sample.controller.MessageDialogController

class MainActivity : TinyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addController(MainController(this, { addDialog(it) }), true)
    }

    private fun addDialog(message: String) {
        val dialog = MessageDialogController(this, contentContainer)
        addController(dialog, false)
        dialog.show()
    }
}
