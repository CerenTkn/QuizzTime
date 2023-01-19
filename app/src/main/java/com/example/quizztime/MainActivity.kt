package com.example.quizztime

import FirestoreClass
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.quizztime.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.et_email
import kotlinx.android.synthetic.main.activity_main.et_password

class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        btn_start.setOnClickListener {
            signInRegisteredUser()

        }
        signUp.setOnClickListener {
            goToSignUp()
        }

    }

    fun goToSignUp(){
        startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
        this.finish()


    }

    fun signInSuccess(user: User){
        hideProgressDialog()
        startActivity(Intent(this@MainActivity, QuizQuestionsActivity::class.java))
        this.finish()
    }

    private fun signInRegisteredUser(){
        val email : String = et_email.text.toString().trim{it <= ' '}
        val password : String = et_password.text.toString().trim{it <= ' '}

        if (validateForm(email, password)){
            showProgressDialog(resources.getString(R.string.please_wait))
            // Sign-In using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        // Calling the FirestoreClass signInUser function to get the data of user from database.
                        FirestoreClass().signInUser(this@MainActivity)
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
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


    /*



    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null){
            intent = Intent(this@MainActivity, QuizQuestionsActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun startClicked(view: View){

        binding.btnStart.setOnClickListener{


        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this@MainActivity, "Please enter your email and password", Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                Log.d(TAG, "signInWithEmail:success")
                intent = Intent(this@MainActivity, QuizQuestionsActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Log.e(TAG, "signInWithEmail:failure")
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
        }

    }

     */
    /*
fun signIn(){
    val email = binding.etEmail.text.toString()
    val password = binding.etPassword.text.toString()
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener (OnCompleteListener<AuthResult>() {
            if ()
        })

}
 */




}