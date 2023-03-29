package com.example.quizztime.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.quizztime.R
import com.example.quizztime.databinding.ActivityMainBinding
import com.example.quizztime.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User


class  MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private var mUserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        mUserName = intent.getStringExtra(Constants.USER_NAME )

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.btnStart.setOnClickListener {
            Log.e("ceren","buton start tıklandı")
            signInRegisteredUser()
        }
        binding.signUp.setOnClickListener {
            Log.e("ceren","buton sign up tıklandı")
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
            this.finish()        }

    }
/*
    public override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null){
           // showErrorSnackBar("Please Sign Up")
        }
    }

 */


    private fun signInRegisteredUser(){

        val email : String = binding.etEmail.text.toString().trim{it <= ' '}
        val password : String = binding.etPassword.text.toString().trim{it <= ' '}

        if (validateForm(email, password)){
            showProgressDialog(resources.getString(R.string.please_wait))
            // Sign-In using FirebaseAuth
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("ceren", "signInWithEmail:success")
                    val intent = Intent(this@MainActivity, QuizCardsActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("ceren", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }

    }

    }
    private fun validateForm(email: String, password: String) : Boolean {

        return if(TextUtils.isEmpty(email)){
            showErrorSnackBar("Please enter email.")
            false
        }else if(TextUtils.isEmpty(password)){
            showErrorSnackBar("Please enter password")
            false
        }
        else{
            true
        }

    }

}