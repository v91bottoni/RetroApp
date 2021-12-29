package com.v1bottoni.retroapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.v1bottoni.retroapp.domain.models.Response
import com.v1bottoni.retroapp.extensions.moshi
import com.v1bottoni.retroapp.viewmodels.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.toString()


    }

    private var usersViewModel: UsersViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersViewModel = getViewModel()
        usersViewModel?.fetchAllUsers()

        usersViewModel?.users?.observe(this) { response ->
            when (response) {
                is Response.Loading -> {}
                is Response.Error -> {}
                is Response.Success -> {
                    var usersList = response.data
                findViewById<TextView>(R.id.text).text = usersList.toString()
                }

            }
        }
    }
}