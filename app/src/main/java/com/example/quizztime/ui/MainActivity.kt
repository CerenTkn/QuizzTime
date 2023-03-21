package com.example.quizztime.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.quizztime.R
import com.example.quizztime.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User


class  MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var user : FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


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
            goToSignUp()
        }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
            showErrorSnackBar("Please Sign Up")
        }
    }



    fun goToSignUp(){
        startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
        this.finish()


    }

    fun updateUI(FirebaseUser: User?){
        hideProgressDialog()
        if (user != null){
            updateUI(FirebaseUser)
        }

    }
    fun signInSuccess(user: User){
        hideProgressDialog()
        startActivity(Intent(this@MainActivity, QuizTimeActivity::class.java))
        this.finish()
    }

    private fun test() {
        //test
    }

    private fun signInRegisteredUser(){

        val email : String = binding.etEmail.text.toString().trim{it <= ' '}
        val password : String = binding.etPassword.text.toString().trim{it <= ' '}

        if (validateForm(email, password)){
            showProgressDialog(resources.getString(R.string.please_wait))
            // Sign-In using FirebaseAuth


        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("ceren", "signInWithEmail:success")
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("ceren", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }

    }

        /*
        val email : String = binding.etEmail.text.toString().trim{it <= ' '}
        val password : String = binding.etPassword.text.toString().trim{it <= ' '}

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

         */


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
        })

}
 */




}