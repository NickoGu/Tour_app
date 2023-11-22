package com.example.tour_app.ui.misCompras

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour_app.data.user.Purchase
import com.example.tour_app.databinding.FragmentMycartBinding
import com.example.tour_app.ui.misCompras.adapter.PurchaseItemModel
import com.example.tour_app.ui.misCompras.adapter.PurchasesAdapter
import repositories.PackageRepository
import repositories.PurchaseRepository

class UserPurchasesFragment : Fragment() {

    private var _binding: FragmentMycartBinding? = null
    private val purchases = mutableListOf<PurchaseItemModel>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val args by navArgs<UserPurchasesFragmentArgs>()
        val userId = args.userIdPurchase
        _binding = FragmentMycartBinding.inflate(inflater, container, false)
        getPurchasesForUser(userId)
        return binding.root
    }

    private fun getPurchasesForUser(userId: Long) {
        PurchaseRepository.get().forEach { purchase ->
            if (purchase.userId == userId) {
                fillPurchasesList(purchase)
            }
        }
    }

    private fun fillPurchasesList(purchase: Purchase){
        val tourPackage = PackageRepository.getById(purchase.packageId)
        tourPackage?.let {
            val purchaseItem = PurchaseItemModel(
                tourPackage.name,
                purchase.createdDate,
                purchase.amount,
                tourPackage.logo
            )
            purchases.add(purchaseItem)
        }
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