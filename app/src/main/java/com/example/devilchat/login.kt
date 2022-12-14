package com.example.devilchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {
    private lateinit var edtEmail:EditText
    private lateinit var edtpassword:EditText
    private lateinit var bl:Button
    private lateinit var bs:Button
    private lateinit var fp:Button
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth=FirebaseAuth.getInstance()

        edtEmail=findViewById(R.id.email)
        edtpassword=findViewById(R.id.password)
        bl=findViewById(R.id.b1)
        bs=findViewById(R.id.b2)
        fp=findViewById(R.id.forgotpassword)
        bs.setOnClickListener {
            val intent=Intent(this,signup::class.java)
            startActivity(intent)
        }
        fp.setOnClickListener{
            val intent=Intent(this,forgot::class.java)
            startActivity(intent)
        }
        bl.setOnClickListener{
            val email=edtEmail.text.toString()
            val password=edtpassword.text.toString()
            login(email,password);
        }


    }
    private fun login(email:String,password:String){
        //code for login
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent=Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"User Does Not Exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}