package com.nicholaspiazza.allcaps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.activity_profile_actvity.*

class ProfileActvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_actvity)

        //intent object
        val intent: Intent = intent

        //get state capital string from intent extra
        val cap: String = intent.getStringExtra("state")

        //set captial textview to string extra
        captial_text.text = cap
    }
}