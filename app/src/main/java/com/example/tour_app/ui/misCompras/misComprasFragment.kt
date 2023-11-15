package com.example.tour_app.ui.misCompras

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour_app.data.tour.TourPackage
import com.example.tour_app.data.transport.Airplane
import com.example.tour_app.databinding.FragmentMycartBinding
import com.example.tour_app.ui.misCompras.adapter.PurchaseItemModel
import com.example.tour_app.ui.misCompras.adapter.PurchasesAdapter
import repositories.DestinationRepository
import repositories.PackageRepository
import repositories.PurchaseRepository

class misComprasFragment : Fragment() {

    private var _binding: FragmentMycartBinding? = null
    private val purchases = mutableListOf<PurchaseItemModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMycartBinding.inflate(inflater, container, false)
        val root: View = binding.root
        PurchaseRepository.get().forEach { purchase ->
            val tempPackage = PackageRepository.getById(purchase.packageId)
            tempPackage?.let { tourpackage ->
                val purchaseItem = PurchaseItemModel(
                    tourpackage.name,
                    purchase.createdDate,
                    purchase.amount,
                    tourpackage.logo
                )
                purchases.add(purchaseItem)
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvMyPurchases.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMyPurchases.adapter = PurchasesAdapter(purchases)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}