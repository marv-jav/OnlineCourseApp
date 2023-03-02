package com.tmmarv.onlinecourseapp.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tmmarv.onlinecourseapp.LoginActivity
import com.tmmarv.onlinecourseapp.PaymentActivity
import com.tmmarv.onlinecourseapp.R


class ProfileFragment : Fragment() {

    private lateinit var logout: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var paymentBtn: CardView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        logout = view.findViewById(R.id.logout_text)
        auth = Firebase.auth
        paymentBtn = view.findViewById(R.id.payment_card)

        logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this.activity, LoginActivity::class.java))
        }
        paymentBtn.setOnClickListener {
            startActivity(Intent(this.activity, PaymentActivity::class.java))
        }
        return view
    }

}