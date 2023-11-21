package  com.example.tour_app

import User
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tour_app.data.excepciones.ErrorIniciarSesionException
import com.example.tour_app.databinding.ActivityLoginScreenBinding
import repositories.UserRepository


class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)


        binding.ingresar.setOnClickListener {
            val contrasenia = binding.contraseA.text.toString()
            val nickname = binding.nickname.text.toString()
            var user: User? = null

            var error = 0

            user = UserRepository.login(nickname, contrasenia)
            userViewModel.user = user


                try {
                    if(user!=null){
                        Toast.makeText(this,"¡Bienvenido, ${nickname}",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Toast.makeText(this,"Usuario o contraseña incorrectos.",Toast.LENGTH_SHORT).show()

                        if(error < 3){
                            error++
                            if(error == 3){
                                throw ErrorIniciarSesionException("")

                            }
                        }

                    }

                }catch (  _: ErrorIniciarSesionException  ){
                    Toast.makeText(this,"El usuario ingresó 3 veces mal el nombre de usuario",Toast.LENGTH_SHORT).show()

                }





        }
    }
}



            /*



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