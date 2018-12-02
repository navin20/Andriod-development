package com.shota.android.readjsonfromapi

import android.os.Debug
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonElement

class DogIndex(){
    var index:ArrayList<Dog> = ArrayList<Dog>();

    constructor(jsonStr:String):this(){
        val gson = Gson()
        val json = gson.fromJson(jsonStr, JsonObject::class.java)
        val entrySet = json.getAsJsonObject("message").entrySet()
        for (entry in entrySet) {

            val subType = entry.value.asJsonArray
            if(0<subType.size()){
                for(subName in subType){
                    val dog = Dog(subName.toString() + " " + entry.key);
                }
            }
            else{
                val dog = Dog(entry.key);
                index.add(dog);
            }
        }
    }
}