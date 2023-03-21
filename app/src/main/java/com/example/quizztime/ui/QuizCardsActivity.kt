package com.example.quizztime.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.quizztime.R
import com.example.quizztime.databinding.ActivityQuizCardsBinding

class QuizCardsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCardView()
    }

    private fun setupCardView(){
        binding.cardflag.setOnClickListener {
            Log.e("ceren", "flag card")
            val intent = Intent(this@QuizCardsActivity, QuizTimeActivity::class.java)
            startActivity(intent)
        }
    }
}