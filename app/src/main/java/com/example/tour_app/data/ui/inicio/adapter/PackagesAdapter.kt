package com.example.tour_app.ui.misCompras.adapter

import android.view.*
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.tour_app.databinding.PackagesListItemBinding
import com.squareup.picasso.Picasso

class PackagesAdapter(
    private val packages: List<PackageItemModel>,
    private val onBuyClicked: (Long) -> Unit
) :
    RecyclerView.Adapter<PackageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        val binding =
            PackagesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PackageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        holder.bind(packages[position], onBuyClicked)
    }

    override fun getItemCount(): Int {
        return packages.size
    }

}

class PackageViewHolder(private val binding: PackagesListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(packages: PackageItemModel, onBuyClicked: (Long) -> Unit) {
        Picasso.get().load(packages.image).into(binding.ivPackageTourImage)
        binding.tvPackageTourTitle.text = packages.title
        binding.tvPackageTourDate.text = "Duración del viaje: ${packages.duration} días"
        binding.tvPackageTourPrice.text = "$${packages.price}"
        binding.buttonBuyPackage.setOnClickListener{
            onBuyClicked(packages.id)
        }
    }


}