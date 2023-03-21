package com.example.quizztime.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizztime.R
import com.example.quizztime.databinding.ActivityQuizCardsBinding

class QuizCardsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}