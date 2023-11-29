package com.example.tour_app.data

import User
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.tour_app.LoginScreen
import com.example.tour_app.R
import repositories.UserRepository
import java.time.LocalDateTime

class RegisterActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tv_title = findViewById<TextView>(R.id.titulo_registro)
        val et_name = findViewById<EditText>(R.id.nombre_del_usuario)
        val et_surname = findViewById<EditText>(R.id.apellido_del_usuario)
        val et_nickname = findViewById<EditText>(R.id.nickname_del_usuario)
        val et_password = findViewById<EditText>(R.id.contraseña_del_usuario)
        val btn_create_user = findViewById<Button>(R.id.boton_crear_cuenta)
        val tv_loginActivity_nav = findViewById<TextView>(R.id.login_navigation_tv)

        tv_loginActivity_nav.setOnClickListener {

            val navigateLoginIntent = Intent(this, LoginScreen::class.java)
            startActivity(navigateLoginIntent)

        }

        btn_create_user.setOnClickListener {

            val name = et_name.text.toString()
            val surname = et_surname.text.toString()
            val nickname = et_nickname.text.toString()
            val password = et_password.text.toString()

            if (name.isEmpty() || surname.isEmpty() || nickname.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Aun quedan campos por completar", Toast.LENGTH_SHORT).show()
            }   else {

                val validateUser = UserRepository.findUserByNickname(nickname)
                if (validateUser != null) {
                    Toast.makeText(this, "El nombre de usuario se encuentra en uso", Toast.LENGTH_SHORT).show()
                } else {
                    val newUser = User(LocalDateTime.now().toString(), nickname, password, name, surname)
                    UserRepository.add(newUser)
                    Toast.makeText(this, "El usuario fue registrado!", Toast.LENGTH_SHORT).show()
                    val navigateLoginIntent = Intent(this, LoginScreen::class.java)
                    startActivity(navigateLoginIntent)
                }

            }

        }



    }
}