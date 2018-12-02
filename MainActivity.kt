package navin.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

import navin.sample.R.id.text
import com.google.firebase.database.DatabaseReference




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("tickers")

        val rView =recyclerView

        rView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        ref.addValueEventListener(object:ValueEventListener{//to add
            override fun onDataChange(snapshot: DataSnapshot) {//try
                val list:ArrayList<Bat> = arrayListOf()
            for(ticker in snapshot.children){
                    val last = ticker.child("last").value.toString()
                    val cPair = ticker.child("currencyPair").value.toString()
                    val bVolume = ticker.child("baseVolume").value.toString()
                    val pChange = ticker.child("percentChange").value.toString()
                    list.add(Bat(last,cPair,bVolume,pChange))
                }

                rView.adapter = BatAdapter(list);
            }

            override fun onCancelled(p0: DatabaseError) {//catch cancellation

            }
        })
    }
}