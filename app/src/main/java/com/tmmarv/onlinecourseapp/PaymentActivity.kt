package com.tmmarv.onlinecourseapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PaymentActivity : AppCompatActivity() {
    lateinit var paymentBtn: Button
    lateinit var balanceTv: TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        balanceTv = findViewById(R.id.balance_tv)
        paymentBtn = findViewById(R.id.make_deposit_btn)

        sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        var bundle = intent.extras
        if (bundle != null) {
            if (bundle.getString("Amount") != null){
                var value = bundle.getString("Amount")
                var toDouble = value?.toDouble()
                var string: String = balanceTv.text.toString()
                var stringD = string.toDouble()
                if (toDouble != null) {
                    stringD += toDouble
                    editor.putString("initial", stringD.toString())
                    editor.apply()
                    val value = sharedPreferences.getString("initial", balanceTv.text.toString())
                    balanceTv.text = value
                }
            }
        }

        paymentBtn.setOnClickListener {
            startActivity(Intent(this, CardActivity::class.java))
        }
    }
}