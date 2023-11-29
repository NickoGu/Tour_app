package com.example.tour_app.ui.paquetes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tour_app.Constantes
import com.example.tour_app.LoginScreen
import com.example.tour_app.R
import com.example.tour_app.data.RegisterActivity
import com.example.tour_app.databinding.FragmentPackagesBinding
import repositories.PackageRepository
import repositories.UserRepository

class PerfilFragment : Fragment() {

    private var _binding: FragmentPackagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvFullName : TextView

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

       return binding.root


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}