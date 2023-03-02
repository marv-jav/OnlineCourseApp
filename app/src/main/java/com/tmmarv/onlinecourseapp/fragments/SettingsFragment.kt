package com.tmmarv.onlinecourseapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tmmarv.onlinecourseapp.R


class SettingsFragment : Fragment() {

    private lateinit var emailTv: TextView
    private lateinit var nameTv: TextView
    private lateinit var passwordTv: TextView
    private var db = Firebase.firestore
    private var auth = Firebase.auth
    private val settings = FirebaseFirestoreSettings.Builder().setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED).build()
    private lateinit var email: String
    private lateinit var name: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_settings, container, false)

        emailTv = view.findViewById(R.id.email_tv)
        nameTv = view.findViewById(R.id.name_tv)
        passwordTv = view.findViewById(R.id.password_tv)

        db.firestoreSettings = settings

        auth.currentUser?.let {
            db.collection("users").document(it.uid).get().addOnSuccessListener { result ->
                name = result["name"].toString()
                email = result["email"].toString()
                emailTv.text = email
                nameTv.text = name
            }
        }
        return view
    }


}