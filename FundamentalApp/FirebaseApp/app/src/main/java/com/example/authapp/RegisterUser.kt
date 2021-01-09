package com.example.authapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register_user.*
import java.util.*


class RegisterUser : AppCompatActivity(), View.OnClickListener {

    // Declare an instance of Firebase Auth
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        mAuth = FirebaseAuth.getInstance()

        bannerRegister.setOnClickListener(this)
        registerUser.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bannerRegister -> {
                val moveToLoginActivity = Intent (this@RegisterUser, MainActivity::class.java)
                startActivity(moveToLoginActivity)
            }
            R.id.registerUser -> {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        val email = et_email.text.toString().trim()
        val password = et_password.text.toString().trim()

        when {
            email.isEmpty() -> {
                et_email.error = "Email is Required"
                et_email.requestFocus()
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                et_email.error = " Input the valid Email Address "
                et_email.requestFocus()
            }
            password.isEmpty() -> {
                et_password.error = "Password is Required"
                et_password.requestFocus()
            }
            password.length < 6 -> {
                et_password.error = "Min password length should be 6 Characters"
                et_password.requestFocus()
            }
            email.isNotEmpty() || password.isNotEmpty() -> {
                progressBar.visibility
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this) {task ->
                            if (task.isSuccessful) {
                                Log.e("Task Message","Successful")
                                Toast.makeText(this, "User has been registered successfully ", Toast.LENGTH_SHORT).show()
                                progressBar.isInvisible
                                val moveToDashboardActivity = Intent(this,DashboardActivity::class.java)
                                startActivity(moveToDashboardActivity)
                            }else {
                                Log.e("Task Message","Failed")
                                Toast.makeText(this, "Failed to registry! Try Again...", Toast.LENGTH_LONG).show()
                                progressBar.isInvisible
                            }
                        }
            }
        }
    }
}