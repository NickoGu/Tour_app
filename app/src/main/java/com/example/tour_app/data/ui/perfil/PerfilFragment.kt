package com.example.tour_app.ui.paquetes

import User
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tour_app.Constantes
import com.example.tour_app.LoginScreen
import com.example.tour_app.R
import com.example.tour_app.R.*
import com.example.tour_app.data.RegisterActivity
import com.example.tour_app.databinding.FragmentPackagesBinding
import repositories.PackageRepository
import repositories.UserRepository

class PerfilFragment : Fragment() {

    private var _binding: FragmentPackagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvFullName : TextView
    private lateinit var btCargarSaldo: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val userIdArg by navArgs<PerfilFragmentArgs>()
        val user = UserRepository.getById(userIdArg.userIdProfile)
        _binding = FragmentPackagesBinding.inflate(inflater, container, false)
        binding.tvFullName.text = "${user.name} ${user.surname} (${user.nickName})"
        binding.tvMoney.text = "$" + user.money.toString()
        binding.btLogout.setOnClickListener {
            val intent = Intent(requireContext(), LoginScreen::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        btCargarSaldo = binding.root.findViewById(R.id.bt_cargar_saldo)
        btCargarSaldo.setOnClickListener {
            showCargarSaldoDialog(user)
        }


        return binding.root


    }
    private fun showCargarSaldoDialog(user : User) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(layout.dialog_add_money, null)
        dialogBuilder.setView(dialogView)

        val editTextSaldo = dialogView.findViewById<EditText>(R.id.editTextSaldo)
        val buttonCargar = dialogView.findViewById<Button>(R.id.buttonCargar)

        val alertDialog = dialogBuilder.create()

        buttonCargar.setOnClickListener {

            val saldo = editTextSaldo.text.toString().toIntOrNull()
            if (saldo != null) {
                try {
                    UserRepository.addMoney(user.nickName, saldo)
                    binding.tvMoney.text = "$" + user.money.toString()
                    alertDialog.dismiss()
                } catch (e: NoSuchElementException) {
                    Toast.makeText(requireContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Ingrese un monto válido", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

