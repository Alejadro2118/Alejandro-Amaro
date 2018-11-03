package com.example.user.alejandrofinal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.AuthProvider

class RegistroUsuario : AppCompatActivity() {
    private lateinit var txtName:EditText
    private lateinit var txtLastName:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar:ProgressBar
    private lateinit var dbReference:DatabaseReference//firebase
    private lateinit var database:FirebaseDatabase//para usar la bd
    private lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)
        //llamammos vistas
        txtName=findViewById(R.id.txtName)
        txtLastName=findViewById(R.id.txtLastName)
        txtEmail=findViewById(R.id.txtEmail)
        txtPassword=findViewById(R.id.txtPassword)

        progressBar=ProgressBar(this)

        progressBar=findViewById(R.id.progressBar2)
        //Instamcia para la base de dtos

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        //instancia para autenticación, con una referenica para leer o excribir en una ubicación
        dbReference=database.reference.child("User")




    }

    fun Register(view:View){
        createNewAccount()
    }

    private fun createNewAccount(){
        val name:String=txtName.getText().toString()
        val lastName:String=txtLastName.getText().toString()
        val email:String=txtEmail.getText().toString()
        val password:String=txtPassword.getText().toString()


        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastName) &&!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            progressBar.visibility=View.VISIBLE

            Log.d("RegistroUsuario", "ESTEEEEEEE"+email)
            Log.d("RegistroUsuario", "ESTEEEEEEE"+password)

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                task ->
                    if(task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                       // verifyEmail(user)
                        val userBD=dbReference.child(user?.uid)

                        userBD.child("Name").setValue(name)
                        userBD.child("LastName").setValue(lastName)
                        action()

            }
            }
        }
    }
    private  fun action(){
        startActivity(Intent(this, MainActivity::class.java))
        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show()
        finish()
    }
   /*
    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
                ?.addOnCompleteListener(this){
                    task ->
                    if (task.isComplete){
                        Toast.makeText(this, "Email enviado", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Error  Enviado", Toast.LENGTH_LONG).show()
                    }
                }
    }*/
}
