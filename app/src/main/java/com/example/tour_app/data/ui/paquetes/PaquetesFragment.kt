package com.example.tour_app.ui.paquetes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour_app.databinding.FragmentMycartBinding
import com.example.tour_app.databinding.FragmentPackagesBinding
import com.example.tour_app.ui.misCompras.adapter.PackageItemModel
import com.example.tour_app.ui.misCompras.adapter.PackagesAdapter
import com.example.tour_app.ui.misCompras.adapter.PurchaseItemModel
import com.example.tour_app.ui.misCompras.adapter.PurchasesAdapter
import repositories.PackageRepository
import repositories.PurchaseRepository

class PaquetesFragment : Fragment() {

private var _binding: FragmentPackagesBinding? = null
    private val paquetes = mutableListOf<PackageItemModel>()
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPackagesBinding.inflate(inflater, container, false)
        val root: View = binding.root
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
        return root
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