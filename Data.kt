package com.shota.android.btpnavjson

import java.lang.Integer.parseInt
import java.sql.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Data(date: String, preTaxNav:Double, preTaxBid:Double, fundSize:Double,change:Double,percentChange:Double){
    var date=date;
    var preTaxNav = preTaxNav
    var preTaxBid = preTaxBid
    var fundSize = fundSize
    var change = change
    var percentChange = percentChange

    init {

    }

    fun getDate():Calendar{
        val dateTime = date.split("T");
        val date = dateTime[0].split("-");
        val time = dateTime[1].split(":");

        val year:Int = parseInt(date[0]);
        val month:Int = parseInt(date[1]);
        val day:Int = parseInt(date[2]);

        val hour:Int = parseInt(time[0]);
        val min:Int = parseInt(time[1]);
        val sec:Int = parseInt(time[2]);
        var c = Calendar.getInstance()
        val d = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(this.date);
        c.time = d
        return c;
    }
    //data class CustomDate
}