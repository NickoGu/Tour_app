package com.example.tour_app.ui.paquetes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tour_app.databinding.FragmentPackagesBinding
import repositories.UserRepository

class PerfilFragment : Fragment() {

    private var _binding: FragmentPackagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val userIdArg by navArgs<PerfilFragmentArgs>()
        println(userIdArg.userIdProfile)
        val user = UserRepository.getById(userIdArg.userIdProfile)

        _binding = FragmentPackagesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}