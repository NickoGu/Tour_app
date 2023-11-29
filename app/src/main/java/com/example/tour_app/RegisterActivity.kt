package com.example.tour_app

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
import com.example.tour_app.databinding.ActivityRegisterBinding
import repositories.UserRepository
import java.time.LocalDateTime

class RegisterActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)

    private lateinit var bindingRegister : ActivityRegisterBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRegister = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bindingRegister.root)


        bindingRegister.loginNavigationTv.setOnClickListener {

            val navigateLoginIntent = Intent(this, LoginScreen::class.java)
            startActivity(navigateLoginIntent)

        }

        bindingRegister.botonCrearCuenta.setOnClickListener {

            val nombreUsuario = bindingRegister.nombreDelUsuario.text.toString()
            val apellidoUsuario = bindingRegister.apellidoDelUsuario.text.toString()
            val nicknameUsuario = bindingRegister.nicknameDelUsuario.text.toString()
            val passwordUsuario = bindingRegister.passwordDelUsuario.text.toString()

            if (nombreUsuario.isEmpty() || apellidoUsuario.isEmpty() || nicknameUsuario.isEmpty() || passwordUsuario.isEmpty()){
                Toast.makeText(this, "Aun quedan campos por completar", Toast.LENGTH_SHORT).show()
            }   else {

                val validarUsuario = UserRepository.findUserByNickname(nicknameUsuario)
                if (validarUsuario != null) {
                    Toast.makeText(this, "El nombre de usuario se encuentra en uso", Toast.LENGTH_SHORT).show()
                } else {
                    val usuarioNuevo = User(LocalDateTime.now().toString(), nicknameUsuario, passwordUsuario, nombreUsuario, apellidoUsuario)
                    UserRepository.add(usuarioNuevo)
                    Toast.makeText(this, "El usuario fue registrado!", Toast.LENGTH_SHORT).show()
                    val navigateLoginIntent = Intent(this, LoginScreen::class.java)
                    startActivity(navigateLoginIntent)
                }

            }

        }



    }
}