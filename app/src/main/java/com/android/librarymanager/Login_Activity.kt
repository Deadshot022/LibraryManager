package com.android.librarymanager

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.librarymanager.databinding.ActivityLoginBinding


class Login_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            loginUser(email, password)
        }

        binding.alreadyHaveAccountTextView.setOnClickListener {
            // Navigate to the registration activity
            val signupIntent = Intent(this, Register_Activity::class.java)
            startActivity(signupIntent)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(baseContext, "Login successful.", Toast.LENGTH_SHORT).show()
                // Navigate to the home activity
            } else {
                // Sign in failed, display a message to the user.
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}