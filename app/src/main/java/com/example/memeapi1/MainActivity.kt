package com.example.memeapi1

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent

class MainActivity : AppCompatActivity() {

    var currentImage:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
    }

     private fun loadMeme()
     {



         // Instantiate the RequestQueue.

         progressBar.visibility = View.VISIBLE

         val queue = Volley.newRequestQueue(this)
         val url = "https://meme-api.herokuapp.com/gimme"
        // Request a string response from the provided URL.
         val JsonObjectRequest = JsonObjectRequest(
             Request.Method.GET,url,null,

             { response ->
                 currentImage=response.getString("url")
                 Glide.with(this).load(currentImage).listener(object: RequestListener<Drawable>{

                     // down side two function are object function my self
                     override fun onLoadFailed(
                         e: GlideException?,
                         model: Any?,
                         target: Target<Drawable>?,
                         isFirstResource: Boolean
                     ): Boolean {

                         progressBar.visibility=View.GONE
                        return false
                     }

                     override fun onResourceReady(
                         resource: Drawable?,
                         model: Any?,
                         target: Target<Drawable>?,
                         dataSource: DataSource?,
                         isFirstResource: Boolean
                     ): Boolean {

                         progressBar.visibility=View.GONE
                            return false
                     }

                 }).into(image)

                 Toast.makeText(this, "Created by virendra ", Toast.LENGTH_SHORT).show()

             },
             {

                 Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()

             })

// Add the request to the RequestQueue.
         queue.add(JsonObjectRequest)


     }


    fun next(view: android.view.View) {

    loadMeme()
    }

    fun sharememe(view: android.view.View) {

        val intent=Intent(Intent.ACTION_SEND)

        intent.type="text/plain"

        intent.putExtra(Intent.EXTRA_TEXT,"Hey,checkout this cool meme i got from reddit $currentImage")

        // this is for send it ...

        val chooser = Intent.createChooser(intent,"share this meme using ....")

        startActivity(chooser)


    }


    }
