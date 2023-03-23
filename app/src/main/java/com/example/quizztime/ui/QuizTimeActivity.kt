package com.example.quizztime.ui

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.quizztime.R
import com.example.quizztime.databinding.ActivityQuizTimeBinding
import com.example.quizztime.model.Question
import com.example.quizztime.utils.Constants

class QuizTimeActivity : AppCompatActivity(), View.OnClickListener{

    //Create global variables for the views in the layout
    private var progressBar: ProgressBar?= null
    private var mCurrentPosition: Int = 1 //this is default and the first question position
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    private lateinit var binding: ActivityQuizTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setQuestion()

        with(binding){
            tvOptionOne.setOnClickListener (this@QuizTimeActivity)
            tvOptionTwo.setOnClickListener (this@QuizTimeActivity)
            tvOptionThree.setOnClickListener (this@QuizTimeActivity)
            tvOptionFour.setOnClickListener (this@QuizTimeActivity)
            btnSubmit.setOnClickListener (this@QuizTimeActivity)


        }

        mQuestionList = Constants.getQuestions()
    }

    private fun setQuestion(){
        Log.e("ceren", "set question")

        //Getting the question from the list with the help of current position
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()
        Log.e("ceren", "val question")


        if (mCurrentPosition == mQuestionList!!.size){
            binding.btnSubmit.text = "FINISH"
        }else{
            binding.btnSubmit.text = "SUBMIT"
        }

        //setting the current progress in the progressbar using the position of question.
        binding.progressBar.progress = mCurrentPosition

        //setting up the progress text
        binding.tvProgress.text = "$mCurrentPosition" + "/" + progressBar?.max

        with(binding){
            tvQuestion.text = question.questions
            ivImage.setImageResource(question.image)
            tvOptionOne.text = question.optionOne
            tvOptionTwo.text = question.optionTwo
            tvOptionThree.text = question.optionThree
            tvOptionFour.text = question.optionFour
        }


    }

    private fun defaultOptionsView(){

        val options = ArrayList<TextView>()
        binding.tvOptionOne.let {
            options.add(0, it)
        }
        binding.tvOptionTwo.let {
            options.add(1, it)
        }
        binding.tvOptionThree.let {
            options.add(2, it)
        }
        binding.tvOptionFour.let {
            options.add(3, it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.SERIF
            option.background = ContextCompat.getDrawable(this@QuizTimeActivity, R.drawable.default_option_border_bg)
        }

    }

    override fun onClick(v: View?) {

        when(v?.id){

            R.id.tv_option_one -> {
                binding.tvOptionOne.let {
                    selectectedOptionView(it, 1)
                }
            }

            R.id.tv_option_two -> {
                binding.tvOptionTwo.let {
                    selectectedOptionView(it, 2)
                }
            }

            R.id.tv_option_three -> {
                binding.tvOptionThree.let {
                    selectectedOptionView(it, 3)
                }
            }

            R.id.tv_option_four -> {
                binding.tvOptionFour.let {
                    selectectedOptionView(it, 4)
                }
            }

            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }

                        else -> {
                            Toast.makeText(this@QuizTimeActivity, "You have successfully completed the quiz. Your Score is $mCorrectAnswers"
                                , Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)

                    //checking if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border)
                    }
                    //increase the number of correct answer
                    else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionList!!.size){
                        binding.btnSubmit.text = "FINISH"
                    } else{
                        binding.btnSubmit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }


            }
        }
    }

    //this is a funciton for answer view(for true and false
    private fun answerView(answer: Int, drawableView: Int){
        when(answer){

            1-> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(this@QuizTimeActivity, drawableView)
            }
            2-> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(this@QuizTimeActivity, drawableView)
            }
            3-> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(this@QuizTimeActivity, drawableView)
            }
            4-> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(this@QuizTimeActivity, drawableView)
            }
        }

    }

    private fun selectectedOptionView(tv: TextView, selectedOptionNum: Int){

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this@QuizTimeActivity, R.drawable.selected_option_border_bg)

    }
}