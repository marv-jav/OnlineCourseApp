package com.tmmarv.onlinecourseapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var signUp: TextView
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var emailTxt: String
    private lateinit var passwordTxt: String
    private lateinit var progressDialog: ProgressDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUp = findViewById(R.id.sign_up_txt)
        loginBtn = findViewById(R.id.login_btn)
        email = findViewById(R.id.email_et)
        password = findViewById(R.id.password_et)
        mAuth = Firebase.auth

        progressDialog = ProgressDialog(this@LoginActivity)
        progressDialog.setMessage("Loading...")


        signUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            if (email.text != null && password.text != null) {
                emailTxt = email.text.toString()
                passwordTxt = password.text.toString()
                progressDialog.show()
                mAuth.signInWithEmailAndPassword(emailTxt, passwordTxt)
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }
}