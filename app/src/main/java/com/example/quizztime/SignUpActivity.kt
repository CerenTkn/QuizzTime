package com.example.quizztime

import FirestoreClass
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.quizztime.databinding.ActivitySignUpBinding
import com.example.quizztime.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.et_email
import kotlinx.android.synthetic.main.activity_sign_up.et_password

class SignUpActivity : BaseActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

       btnsignUp.setOnClickListener {
            registerUser()
        }
    }

    fun  userRegisteredSuccess() {
        Toast.makeText(this@SignUpActivity,
            "You have successfully registered with email.",
            Toast.LENGTH_SHORT).show()
        hideProgressDialog()

        /**
         * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
         * and send him to Intro Screen for Sign-In
         */
        FirebaseAuth.getInstance().signOut()
        finish()

    }

    private fun registerUser(){

        val name: String = et_name.text.toString().trim { it <= ' '}
        val email: String = et_email.text.toString().trim { it <= ' '}
        val password: String = et_password.text.toString().trim { it <= ' '}

        if (validateForm(name, email, password)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            val registeredEmail = firebaseUser.email!!
                            val user = User(name, email, password )

                            FirestoreClass().registerUser(this, user)
                        }else{
                            Toast.makeText(this@SignUpActivity, "Registration failed",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                )
        }

    }

    private fun validateForm(name: String, email:String, password:String) : Boolean{
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter your name")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter your email")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter your password")
                false
            }else -> {
                true
            }
        }
    }
    /*

    fun signUpClicked(view: View){

        binding.btnSignUp.setOnClickListener{

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this@SignUpActivity, "Please enter your email and password", Toast.LENGTH_LONG).show()
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    intent = Intent(this@SignUpActivity, QuizQuestionsActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener{
                    Toast.makeText(this@SignUpActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

     */
}