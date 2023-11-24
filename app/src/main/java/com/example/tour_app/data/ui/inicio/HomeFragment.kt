package com.example.tour_app.ui.inicio

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour_app.Constantes
import com.example.tour_app.data.ui.packagedetail.PackageDetail
import com.example.tour_app.databinding.FragmentHomeBinding
import com.example.tour_app.ui.misCompras.UserPurchasesFragmentArgs
import com.example.tour_app.ui.misCompras.adapter.PackageItemModel
import com.example.tour_app.ui.misCompras.adapter.PackagesAdapter
import repositories.PackageRepository
import repositories.PurchaseRepository

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val packages = mutableListOf<PackageItemModel>()
    private var userId : Long? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val args by navArgs<HomeFragmentArgs>()
//        userId = args.userIdHome
        userId = arguments?.getLong(Constantes.USER_ID_ARG_HOME)
        PackageRepository.get().forEach { packages ->
            val tempPackage = PackageRepository.getById(packages.id)
            tempPackage?.let { tourpackage ->
                val packageItem = PackageItemModel(
                    tourpackage.name,
                    tourpackage.duration,
                    packages.price,
                    tourpackage.logo,
                    packages.id
                )
                this.packages.add(packageItem)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvPackages.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPackages.adapter = PackagesAdapter(packages){ packageChoosenId ->
            val intent = Intent(requireActivity(), PackageDetail::class.java).apply {
                putExtra(Constantes.PACKAGE_TO_BUY_ID, packageChoosenId)
                putExtra(Constantes.USER_ID_DETAIL_EXTRA, userId)
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}