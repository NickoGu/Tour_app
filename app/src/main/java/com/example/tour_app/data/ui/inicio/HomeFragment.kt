package com.example.tour_app.ui.inicio

import User
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.NavArgs
import androidx.navigation.NavArgument
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBindings
import com.example.tour_app.Constantes
import com.example.tour_app.R
import com.example.tour_app.databinding.FragmentHomeBinding
import com.example.tour_app.databinding.FragmentPackagesBinding
import com.example.tour_app.ui.misCompras.adapter.PackageItemModel
import com.example.tour_app.ui.misCompras.adapter.PackagesAdapter
import repositories.PackageRepository
import repositories.UserRepository
import java.util.prefs.Preferences
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val paquetes = mutableListOf<PackageItemModel>()
    private var userId : Long? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    val btn_comprar = binding.root.findViewById<Button>(R.id.button_buy_package)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        PackageRepository.get().forEach { packages ->
            val tempPackage = PackageRepository.getById(packages.id)
            tempPackage?.let { tourpackage ->
                val packageItem = PackageItemModel(
                    tourpackage.name,
                    tourpackage.duration,
                    packages.price,
                    tourpackage.logo
                )

                paquetes.add(packageItem)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvPackages.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPackages.adapter = PackagesAdapter(paquetes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}