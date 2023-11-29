package com.example.tour_app.ui.misCompras.adapter

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tour_app.data.tour.TourPackage
import com.example.tour_app.data.user.Purchase
import com.example.tour_app.databinding.PurchaseListItemBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

class PurchasesAdapter(
    private val myPurchases: List<PurchaseItemModel>
) :
    RecyclerView.Adapter<PurchasesVieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasesVieHolder {
        val binding =
            PurchaseListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PurchasesVieHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchasesVieHolder, position: Int) {
        holder.bind(myPurchases[position])
    }

    override fun getItemCount(): Int {
        return myPurchases.size
    }

}

class PurchasesVieHolder(private val binding: PurchaseListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(purchase: PurchaseItemModel) {
        Picasso.get().load(purchase.image).into(binding.ivPurchaseTourImage)
        binding.tvPurchaseTourTitle.text = purchase.title
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        val date = parser.parse(purchase.date)!!
        val formattedDate = formatter.format(date)
        binding.tvPurchaseTourDate.text = formattedDate
        binding.tvPurchaseTourPrice.text = "$${purchase.price}"
    }
}