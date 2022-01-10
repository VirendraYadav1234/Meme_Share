package com.example.memeapi1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
    }

     private fun loadMeme()
     {

         // Instantiate the RequestQueue.
         val queue = Volley.newRequestQueue(this)
         val url = "https://i.redd.it/1epa1kxerta81.jpg"

        // Request a string response from the provided URL.
         val JsonObjectRequest = JsonObjectRequest(
             Request.Method.GET,url,null,

             Response.Listener{ response ->
                 val url=response.getString("url")
                 Glide.with(this).load(url).into(image)


             },
             Response.ErrorListener {

                 Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()

             })

// Add the request to the RequestQueue.
         queue.add(JsonObjectRequest)


     }


    fun next(view: android.view.View) {


    }

    fun sharememe(view: android.view.View) {


    }
}