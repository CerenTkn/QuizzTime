package com.example.quizztime.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizztime.R
import com.example.quizztime.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnFinish.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }

        //Toast.makeText(this@QuizTimeActivity, "You have successfully completed the quiz. Your Score is $mCorrectAnswers" , Toast.LENGTH_SHORT).show()
    }
}