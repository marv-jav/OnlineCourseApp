package com.tmmarv.onlinecourseapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.tmmarv.onlinecourseapp.R
import com.tmmarv.onlinecourseapp.adapters.CategoryAdapter
import com.tmmarv.onlinecourseapp.adapters.CourseAdapter
import com.tmmarv.onlinecourseapp.models.CategoryModel
import com.tmmarv.onlinecourseapp.models.CourseModel


class CourseFragment : Fragment() {
    private lateinit var usernameTv: TextView
    private lateinit var searchEt: EditText
    private lateinit var categoryRecycler: RecyclerView
    private lateinit var courseRecycler: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var courseAdapter: CourseAdapter
    private var categoryList = ArrayList<CategoryModel>()
    private var courseList = ArrayList<CourseModel>()
    private var db = Firebase.firestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_course, container, false)
        usernameTv = view.findViewById(R.id.username_tv)
        searchEt = view.findViewById(R.id.search_et)
        categoryRecycler = view.findViewById(R.id.recycler_category)
        courseRecycler = view.findViewById(R.id.recycler_courses)
        categoryAdapter = CategoryAdapter(categoryList)
        courseAdapter = CourseAdapter(courseList)

        val layoutManager = LinearLayoutManager(this.requireActivity().applicationContext)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        categoryRecycler.layoutManager = layoutManager

        val courseLayoutManager = LinearLayoutManager(this.requireActivity().applicationContext)
        courseRecycler.layoutManager = courseLayoutManager

        db.collection("category").get().addOnSuccessListener { qSnapshot ->
            if (!qSnapshot.isEmpty) {
                val list = qSnapshot.documents
                for (d in list) {
                    var c = d.toObject(CategoryModel::class.java)
                    if (c != null) {
                        categoryList.add(c)
                    }
                }
                categoryRecycler.adapter = categoryAdapter
            }
        }

        db.collection("course").get().addOnSuccessListener { qSnapshot ->
            if (!qSnapshot.isEmpty) {
                val list = qSnapshot.documents
                for (d in list) {
                    var c = d.toObject(CourseModel::class.java)
                    if (c != null) {
                        courseList.add(c)
                    }
                }
                courseRecycler.adapter = courseAdapter
            }
        }



        return view
    }
}