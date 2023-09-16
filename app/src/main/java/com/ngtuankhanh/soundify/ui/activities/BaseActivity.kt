package com.ngtuankhanh.soundify.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ngtuankhanh.soundify.ui.Model
import com.ngtuankhanh.soundify.ui.SoundifyApplication

abstract class BaseActivity : AppCompatActivity() {
    lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (application as SoundifyApplication).model
    }
}