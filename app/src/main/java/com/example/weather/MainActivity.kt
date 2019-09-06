package com.example.weather

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var NasaData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NasaData = textView
        button.setOnClickListener { getCurrentData() }
    }


    private fun getCurrentData() {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(NasaService::class.java)
        val call = service.getCurrentNasaData(AppId)

        call.enqueue(object : Callback<NasaResponse> {
            override fun onResponse(call: Call<NasaResponse>, response: Response<NasaResponse>) {
                if (response.code() == 200) {
                    val NasaResponse = response.body()!!
                    val stringBuilder = "Date: " +
                            NasaResponse.date +
                            "\n" +
                            "Title: " +
                            NasaResponse.title +
                            "\n" +
                            "explanation: " +
                            NasaResponse.explanation
                    NasaData.text = stringBuilder
                    Picasso.get()
                        .load(NasaResponse.url)
                        .into(imageView)
                }
            }

            override fun onFailure(call: Call<NasaResponse>, t: Throwable) {
                NasaData.text = t.message
            }
        })
    }

    companion object {

        var BaseUrl = "https://api.nasa.gov/planetary/"
        var AppId = "4V7MxGaddZYLgLj71sYviLo6gxValkgJwNVfUBQl"
    }
}
