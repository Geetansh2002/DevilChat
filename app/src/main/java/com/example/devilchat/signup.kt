package com.example.devilchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signup : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtName: EditText
    private lateinit var edtpassword: EditText
    private lateinit var bs: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mdatabase:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()


        mAuth=FirebaseAuth.getInstance()
        edtEmail=findViewById(R.id.email)
        edtName=findViewById(R.id.name)
        edtpassword=findViewById(R.id.password)

        bs=findViewById(R.id.b2)
        bs.setOnClickListener{
            val name=edtName.text.toString()
            val email=edtEmail.text.toString()
            val password=edtpassword.text.toString()
            signup(name,email,password)
        }
    }
    private fun signup(name:String,email:String,password:String ){
        //code for signup
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent=Intent( this, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Some Error Occured",Toast.LENGTH_SHORT).show()

                }
            }
    }
    private fun addUserToDatabase(name: String,email: String,uid:String){

           mdatabase=FirebaseDatabase.getInstance().getReference()

        mdatabase.child("user").child(uid).setValue(user(name,email,uid))
    }
}