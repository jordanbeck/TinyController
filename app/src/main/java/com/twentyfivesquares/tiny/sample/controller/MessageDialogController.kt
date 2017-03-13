package com.twentyfivesquares.tiny.sample.controller

import android.content.Context
import android.view.ViewGroup
import com.twentyfivesquares.tiny.DialogController
import com.twentyfivesquares.tiny.sample.R

class MessageDialogController(context: Context, parent: ViewGroup) : DialogController(context, parent) {

    override fun getDialogLayoutRes(): Int = R.layout.dialog_controller_message

}
