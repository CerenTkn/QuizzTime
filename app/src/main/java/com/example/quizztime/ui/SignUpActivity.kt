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
import com.example.quizztime.utils.Constants
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : BaseActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private var mUserName : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        mUserName = intent.getStringExtra(Constants.USER_NAME )


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
                            intent.putExtra(Constants.USER_NAME, binding.etName.text.toString())
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