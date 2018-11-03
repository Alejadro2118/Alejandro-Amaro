package com.example.user.alejandrofinal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*;


class MainActivity : AppCompatActivity() {
    private lateinit var txtNameLogin: EditText
    private lateinit var txtPasswordLogin: EditText
    private lateinit var progressBar: ProgressBar


    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtNameLogin=findViewById(R.id.txNameLogin)
        txtPasswordLogin=findViewById(R.id.txtContraseñaLogin)

        progressBar=ProgressBar(this)


        progressBar=findViewById(R.id.progressBar3)

    auth=FirebaseAuth.getInstance()



        RegistrarUser.setOnClickListener({
            val intento1 = Intent(this@MainActivity, RegistroUsuario::class.java)
            startActivity(intento1)
            finish();
        })
    }
    fun login(view: View){
        loginUser()
    }

    private fun loginUser(){
        val user:String=txtNameLogin.getText().toString()
        val password:String=txtPasswordLogin.getText().toString()
        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            progressBar.visibility=View.VISIBLE

            auth.signInWithEmailAndPassword(user, password)
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isSuccessful){
                            action()
                            Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
                            finish()
                        }else{
                            Toast.makeText(this, "Error al autentificar", Toast.LENGTH_LONG).show()
                        }
                    }

        }
    }
private fun action(){
    startActivity(Intent(this, HomeActivity::class.java))
}


}
