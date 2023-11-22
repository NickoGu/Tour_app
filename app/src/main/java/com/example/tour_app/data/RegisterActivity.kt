package com.example.tour_app.data

import User
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.tour_app.R
import org.w3c.dom.Text
import repositories.UserRepository
import java.time.LocalDate
import java.time.LocalDateTime

class RegisterActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tv_titulo_menu_registro = findViewById<TextView>(R.id.titulo_registro)
        val et_nombre_de_usuario = findViewById<EditText>(R.id.nombre_del_usuario)
        val et_apellido_del_usuario = findViewById<EditText>(R.id.apellido_del_usuario)
        val et_nickname_del_usuario = findViewById<EditText>(R.id.nickname_del_usuario)
        val et_password_del_usuario = findViewById<EditText>(R.id.contraseña_del_usuario)
        val btn_crear_usuario = findViewById<Button>(R.id.boton_crear_cuenta)


        btn_crear_usuario.setOnClickListener {

            val nombreUsuario = et_nombre_de_usuario.toString()
            val apellidoUsuario = et_apellido_del_usuario.toString()
            val nicknameUsuario = et_nickname_del_usuario.toString()
            val passwordUsuario = et_password_del_usuario.toString()

            if (nombreUsuario.isEmpty() || apellidoUsuario.isEmpty() || nicknameUsuario.isEmpty() || passwordUsuario.isEmpty()){
                Toast.makeText(this, "Aun quedan campos por completar!", Toast.LENGTH_LONG).show()
            } else {

                val validarUsuario : User? = UserRepository.findUserByNickname(nicknameUsuario)

                if (validarUsuario != null){
                    Toast.makeText(this, "El nombre se usuario se encuentra en uso", Toast.LENGTH_SHORT).show()
                } else {
                    val usuarioRegistrado = User(LocalDate.now().toString(), nicknameUsuario, passwordUsuario,nombreUsuario, apellidoUsuario)
                        UserRepository.add(usuarioRegistrado)
                }

            }

        }



    }
}