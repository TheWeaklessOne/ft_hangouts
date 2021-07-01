package com.example.ft_hangouts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ft_hangouts.R
import com.example.ft_hangouts.data.SharedPreferenceHelper
import com.example.ft_hangouts.presentation.main.MainFragment
import com.example.ft_hangouts.presentation.navigator.Navigator
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var sharedPref: SharedPreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = SharedPreferenceHelper(this)
        goToMainFragment()
    }

    override fun onResume() {
        super.onResume()
        showCurrentDateToast()
    }

    override fun goToMainFragment() {
        openFragment(MainFragment.newInstance())
    }

    private fun showCurrentDateToast() {
        val lastActiveDate = sharedPref.backgroundDate

        if (lastActiveDate.isNotEmpty()) {
            val lastVisitText = getString(R.string.last_visit, lastActiveDate)
            Toast.makeText(this,  lastVisitText, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onPause() {
        super.onPause()
        val lastActiveDate: String = SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(Date())
        sharedPref.backgroundDate = lastActiveDate
    }

    companion object {
        private const val DATE_PATTERN = "h:mm a, dd.MM.yyyy"
    }
}