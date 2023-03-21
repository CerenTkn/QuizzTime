package com.example.quizztime.ui
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.quizztime.R
import com.example.quizztime.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : BaseActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()


        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

       binding.btnsignUp.setOnClickListener {
           Log.e("ceren","buton sign up tıklandı")

            registerUser()
        }
    }

    /*
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

     */

    private fun registerUser(){
        with(binding){
            val name: String = etName.text.toString().trim { it <= ' '}
            val email: String = etEmail.text.toString().trim { it <= ' '}
            val password: String = etPassword.text.toString().trim { it <= ' '}

            if (validateForm(name, email, password)) {
                Log.e("ceren","form validate true")
                showProgressDialog(resources.getString(R.string.please_wait))
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@SignUpActivity) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")

                            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                            startActivity(intent)


                            /*
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            val registeredEmail = firebaseUser.email!!
                            val user = User(name, email, password)

                            FirestoreClass().registerUser(this@SignUpActivity, user)

                             */
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                this@SignUpActivity, "Registration failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

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
}