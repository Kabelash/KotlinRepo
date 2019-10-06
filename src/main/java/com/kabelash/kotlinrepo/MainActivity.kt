package com.kabelash.kotlinrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

/*
* Created by Kabelash on 30.09.2019
* */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this);

        fetchJson()

    }

    // Fetch JSON
    fun fetchJson() {
        val url = "https://api.github.com/users/Kotlin/repos"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body) // To get Json on Logcat

                val gson = GsonBuilder().create()

                //got Json object as array
                val feed = gson.fromJson(body, Array<Feed>::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(feed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Request Failed")
            }
        })
    }
}

//Class for Json
class Feed (val name: String, val created_at: String, val owner: Owner)

class Owner (val login: String, val avatar_url: String)