package com.example.introductiontoroom.introduction.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.introductiontoroom.databinding.ActivityStartBinding
import com.example.introductiontoroom.introduction.view.person.PersonActivity

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("StartActivity", "onCreate chamado.")
        enableEdgeToEdge()
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.roundedImageView.setOnClickListener {
            startActivity(Intent(this, PersonActivity::class.java))
        }

        window.setFlags(
            FLAG_LAYOUT_NO_LIMITS,
            FLAG_LAYOUT_NO_LIMITS
        )

    }
}