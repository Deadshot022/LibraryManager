package com.android.librarymanager
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.librarymanager.databinding.ActivityRegisterBinding
import com.android.librarymanager.models.students
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Register_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            registerUser(email, password)
        }

        binding.loginTextView.setOnClickListener {
            // Navigate to the login activity
            val loginIntent = Intent(this, Login_Activity::class.java)
            startActivity(loginIntent)
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign up success, update UI with the signed-in user's information
                Toast.makeText(baseContext, "Sign up successful.", Toast.LENGTH_SHORT).show()
                // Navigate to the home activity
            } else {
                // Sign up failed, display a message to the user.
                Toast.makeText(baseContext, "Sign up failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    val db = FirebaseFirestore.getInstance()

    val student = students(
        name = "John Doe",
        email = "john.doe@example.com",
        password = "password123",
        year = "2",
        branch = "Computer Science"
    )

    db.collection("students")
    .document(student.email)
    .set(student)
    .addOnSuccessListener { Log.d(TAG, "Student data saved successfully") }
    .addOnFailureListener { e -> Log.w(TAG, "Error saving student data", e) }

}