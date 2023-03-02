package com.tmmarv.onlinecourseapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import co.paystack.android.Paystack
import co.paystack.android.PaystackSdk
import co.paystack.android.Transaction
import co.paystack.android.model.Card
import co.paystack.android.model.Charge
import com.braintreepayments.cardform.view.CardEditText
import com.braintreepayments.cardform.view.CvvEditText
import com.braintreepayments.cardform.view.ExpirationDateEditText

class CardActivity : AppCompatActivity() {
    lateinit var cardNumber: CardEditText
    lateinit var cardExpiry: ExpirationDateEditText
    lateinit var cardCvv: CvvEditText
    lateinit var amountEt: EditText
    lateinit var addBtn: Button
    lateinit var amountStr: String
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        cardNumber = findViewById(R.id.card_no_et)
        cardExpiry = findViewById(R.id.card_expire_et)
        cardCvv = findViewById(R.id.cvv_et)
        amountEt = findViewById(R.id.amount_et)
        addBtn = findViewById(R.id.card_add_btn)

        initializeSdk()
        addBtn.setOnClickListener {
            performCharge()
        }

    }

    private fun performCharge() {
        val cardNumber = cardNumber.text?.trim().toString()
        val cardCvv = cardCvv.text.toString()
        val cardMonth = cardExpiry.month.toInt()
        val cardYear = cardExpiry.year.toInt()
        amountStr = amountEt.text.toString()
        val amount = amountStr.toInt()

        val card = Card(cardNumber, cardMonth, cardYear, cardCvv)
        val charge = Charge()
        charge.amount = amount
        charge.email = "ayoolamarvellous83@gmail.com"
        charge.card = card

        PaystackSdk.chargeCard(this, charge, object : Paystack.TransactionCallback {
            override fun onSuccess(transaction: Transaction) {
                parseResponse(transaction.reference)
            }

            override fun beforeValidate(transaction: Transaction) {
                Log.d("Main Activity", "beforeValidate: " + transaction.reference)
            }

            override fun onError(error: Throwable, transaction: Transaction) {
                Log.d("Main Activity", "onError: " + error.localizedMessage)
                Log.d("Main Activity", "onError: $error")
            }

        })

    }

    private fun parseResponse(reference: String?) {
        val message = "Payment Successful - $reference"
        val intent = Intent(this, PaymentActivity::class.java)
        editor.putString("amount", amountStr)
        intent.putExtra("Amount", amountStr)
        startActivity(intent)

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun initializeSdk() {
        PaystackSdk.initialize(this.applicationContext)
        PaystackSdk.setPublicKey("pk_test_0316cd3430668d4e1c483ee1902000aa31ac0422")
    }
}