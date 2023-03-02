package com.tmmarv.onlinecourseapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var buttonReg: Button
    private lateinit var text: TextView
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    private lateinit var nameTxt: String
    private lateinit var emailTxt: String
    private lateinit var passwordTxt: String
    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        progressDialog = ProgressDialog(this@RegisterActivity)
        progressDialog.setMessage("Loading...")

        name = findViewById(R.id.name_reg);
        email = findViewById(R.id.email_reg)
        password = findViewById(R.id.password_reg)
        buttonReg = findViewById(R.id.sign_up_btn)
        text = findViewById(R.id.log_in_txt)
        mAuth = Firebase.auth


        buttonReg.setOnClickListener {
            if (name.text != null && email.text != null && password.text != null) {
                nameTxt = name.text.toString()
                emailTxt = email.text.toString()
                passwordTxt = password.text.toString()
            }
            progressDialog.show()
            mAuth.createUserWithEmailAndPassword(emailTxt, passwordTxt).addOnCompleteListener {
                val user = HashMap<String, String>()
                user["name"] = nameTxt
                user["email"] = emailTxt
                mAuth.currentUser?.let {
                    db.collection("users").document(it.uid).set(user)
                }
            }
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        }

        text.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}