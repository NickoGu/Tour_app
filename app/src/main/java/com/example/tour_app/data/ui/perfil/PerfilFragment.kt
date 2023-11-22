package com.example.tour_app.ui.paquetes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tour_app.Constantes
import com.example.tour_app.databinding.FragmentPackagesBinding
import repositories.UserRepository

class PerfilFragment : Fragment() {

    private var _binding: FragmentPackagesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val user = UserRepository.getUserById(requireArguments().getLong(Constantes.USER_ID_FRAGMENT_EXTRA))
        _binding = FragmentPackagesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}