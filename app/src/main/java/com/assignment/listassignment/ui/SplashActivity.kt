package com.assignment.listassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.assignment.listassignment.ui.news.MainActivity
import com.assignment.listassignment.R
import com.assignment.listassignment.databinding.ActivityLauncherBinding


/**
 * Created by Aparna S.
 */
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_launcher)
        init()

    }

    /**
     * Init variables and set values
     */
    private fun init() {
        Handler().postDelayed({
            //getting instance
          goToDashboard()

        }, 2000)
    }

    override fun onBackPressed() {
        //nothing to do
    }

    /**
     *  navigation to Dashboard
     */
    private fun goToDashboard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this@SplashActivity.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)  // for open
    }


}

