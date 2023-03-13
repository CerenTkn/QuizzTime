package com.example.quizztime.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizztime.R
import com.example.quizztime.databinding.ActivityQuizTimeBinding

class QuizTimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}