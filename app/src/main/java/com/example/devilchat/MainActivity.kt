package com.example.devilchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userlist:ArrayList<user>
    private lateinit var adapter:userAdapter
    private lateinit var mauth: FirebaseAuth
    private lateinit var mOnRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mOnRef=FirebaseDatabase.getInstance().getReference()
        mauth=FirebaseAuth.getInstance()
        userlist=ArrayList()
        adapter=userAdapter(this,userlist)

        userRecyclerView=findViewById(R.id.rec)
        userRecyclerView.layoutManager=LinearLayoutManager(this)
        userRecyclerView.adapter=adapter
        mOnRef.child("user").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userlist.clear()

                for (postSnapshot in snapshot.children){
                    val currentuser=postSnapshot.getValue(user::class.java)

                    if(mauth.currentUser?.uid!=currentuser?.uid){
                        userlist.add(currentuser!!)
                    }

                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout){
            mauth.signOut()
            val intent=Intent(this,login::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}