package com.shota.android.readjsonfromapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        "https://dog.ceo/api/breeds/list/all".httpGet().responseString { request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get()
                    val gson = Gson()
                    val temp = gson.fromJson(data,temp::class.java);
                    val dogIndex = DogIndex(data);
                    val rView = recyclerView
                    rView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                    val student = dogIndex.index

                    var adapter = DogAdapter(student);
                    rView.adapter = adapter;
                }
            }
        }
    }
    class temp(status:String, message:String){}
}
