package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.tour_app.data.excepciones.ErrorIniciarSesionException
import com.example.tour_app.data.user.User
import com.example.tour_app.databinding.ActivityLoginScreenBinding
import com.example.tour_app.databinding.ActivityMainBinding
import repositories.UserRepository


class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ingresar.setOnClickListener {
            val contrasenia = binding.contraseA.text.toString()
            val email = binding.email.text.toString()
            var user: User? = null
            var error = 0;

            user = UserRepository.login(email, contrasenia)


            do {
                try {
                    if (user != null) {
                        Toast.makeText(this,"\n\u001B[51m¡Bienvenido, ${email}!\u001B[0m",Toast.LENGTH_SHORT)

                    } else {
                        Toast.makeText(this,"\u001B[31mUsuario o contraseña incorrectos.\n\u001B[0m",Toast.LENGTH_SHORT)
                        error++;
                    }
                    if (error == 3) {
                        throw ErrorIniciarSesionException("El usuario ingresó 3 veces mal el nombre de usuario.")
                    }
                } catch (_: ErrorIniciarSesionException) {
                    // acá enviaría datos a una bdd



                    throw ErrorIniciarSesionException("El usuario ingresó 3 veces mal el nombre de usuario.")

                }

            } while (user == null)




        }
    }
}
/*



    fun iniciarSesion(): User {

        var user: User? = null
        var error = 0;
        do {
            try {
                print("Ingrese su nombre de usuario: ");
                val nombre = readln()
                print("Ingrese su contraseña: ")
                val contrasenia = readln()
                user = UserRepository.login(nombre, contrasenia)
                if (user != null) {
                    println("\n\u001B[51m¡Bienvenido, ${nombre}!\u001B[0m")
                } else {
                    println("\u001B[31mUsuario o contraseña incorrectos.\n\u001B[0m")
                    error++;
                }
                if (error == 3) {
                    throw ErrorIniciarSesionException("El usuario ingresó 3 veces mal el nombre de usuario.")
                }
            } catch (_: ErrorIniciarSesionException) {
                // acá enviaría datos a una bdd
                throw ErrorIniciarSesionException("El usuario ingresó 3 veces mal el nombre de usuario.")

            }

        } while (user == null)

        return user
    }



}
*/