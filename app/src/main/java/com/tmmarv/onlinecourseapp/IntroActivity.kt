package com.tmmarv.onlinecourseapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {
    private lateinit var iv1: View
    private lateinit var iv2: View
    private lateinit var iv3: View
    private lateinit var card1: CardView
    private lateinit var card2: CardView
    private lateinit var card3: CardView

    private lateinit var viewPager2: ViewPager2
    private lateinit var button: Button

    private lateinit var skip: TextView
    private lateinit var auth: FirebaseAuth


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        viewPager2 = findViewById(R.id.view_pager_2)
        iv1 = findViewById(R.id.iv1)
        iv2 = findViewById(R.id.iv2)
        iv3 = findViewById(R.id.iv3)
        card1 = findViewById(R.id.card_one)
        card2 = findViewById(R.id.card_two)
        card3 = findViewById(R.id.card_three)
        button = findViewById(R.id.next_btn)
        skip = findViewById(R.id.skip_text)

        auth = Firebase.auth

        if (auth.currentUser != null){
            startActivity(Intent(this, HomeActivity::class.java))
        }

        val images = listOf(R.drawable.intro_one, R.drawable.intro_two, R.drawable.intro_three)
        val textOne = listOf("Learn anytime\n and anywhere", "Find a course\n for you", "Improve your skills")
        val textTwo = listOf(
            "Now is the perfect time to spend your\n day learning something new, from anywhere!\n\n\n\n",
            "Now is the perfect time to spend your\n day learning something new, from anywhere!\n\n\n\n",
            "Now is the perfect time to spend your\n day learning something new, from anywhere!\n\n\n\n")
        val adapter = ViewPagerAdapter(images, textOne, textTwo)
        viewPager2.adapter = adapter

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                changeColor()
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                changeColor()
            }

        })

        skip.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            if(button.text == "Let's Start") {
                startActivity(Intent(this, LoginActivity::class.java))
            }else{
                viewPager2.currentItem = viewPager2.currentItem + 1
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun changeColor()
    {
        when(viewPager2.currentItem)
        {
            0 -> {
                button.text = "Next"
                iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.color_secondary))
                iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.color_pagination_inactive))
                iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.color_pagination_inactive))
            }
            1 -> {
                button.text = "Next"
                iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.color_pagination_inactive))
                iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.color_secondary))
                iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.color_pagination_inactive))
            }
            2 -> {
                button.text = "Let's Start"
                iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.color_pagination_inactive))
                iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.color_pagination_inactive))
                iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.color_secondary))
            }
        }
    }
}