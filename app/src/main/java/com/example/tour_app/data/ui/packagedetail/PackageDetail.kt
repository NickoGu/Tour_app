package com.example.tour_app.data.ui.packagedetail

import User
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.lifecycleScope
import com.example.tour_app.Constantes
import com.example.tour_app.R
import com.example.tour_app.data.tour.TourPackage
import com.example.tour_app.data.user.Purchase
import com.example.tour_app.databinding.ActivityPackageDetailBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import repositories.PackageRepository
import repositories.PurchaseRepository
import repositories.UserRepository
import java.lang.Exception
import java.time.LocalDate

class PackageDetail : AppCompatActivity() {

    private lateinit var binding: ActivityPackageDetailBinding
    private val destinationImageList = mutableListOf<Drawable>()
    private var destinationImageListIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPackageDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        val packageToBuyId = intent?.getLongExtra(Constantes.PACKAGE_TO_BUY_ID, 0)
        val user = intent?.getLongExtra(Constantes.USER_ID_DETAIL_EXTRA, 0)
            ?.let { UserRepository.getById(it) }
        if (packageToBuyId != null && user != null) {
            PackageRepository.getById(packageToBuyId)?.let {
                initUI(it, user)
            }
        }

    }

    private fun initUI(packageToBuy: TourPackage, user: User) {
        Picasso.get().load(packageToBuy.logo).into(binding.ivPackageDetailImage)
        binding.tvPackageDetailTitle.text = packageToBuy.name
        binding.tvPackageDetailRating.text =
            getString(R.string.detail_package_rating, packageToBuy.stars.toString())
        binding.tvPackageDetailTransport.text =
            getString(R.string.transporte_package_detail, packageToBuy.transport)
        binding.tvPackageDetailDuration.text =
            getString(R.string.tour_duration_detail_text, packageToBuy.duration.toString())
        binding.tvPackageDetailPrice.text =
            "Precio: $${packageToBuy.price.toString()}"
        binding.tvPackageDetailPrice.paintFlags = binding.tvPackageDetailPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        val finalPriceWithFee = getPriceWithFee(packageToBuy)
        binding.tvPackageDetailPriceWithFee.text =
           "$${finalPriceWithFee.toString()}"
        binding.warningFee.text = "(El precio puede verse afectado debido a las comisiones)"
        binding.tvPackageDetailDescription.text = packageToBuy.destination.description
        binding.tvPackageDetailDestiny.text =
            getString(R.string.destination_name_detail, packageToBuy.destination.name)
        binding.buttonBuyPackage.setOnClickListener {
            makePurchase(user, packageToBuy, finalPriceWithFee)
        }
        downloadImages(packageToBuy.destination.pictures)
        setUpImageSlider()
    }

    private fun setUpImageSlider() {
        println(destinationImageList.size)
        binding.carrouselPackageDetailDestinationImages.setFactory {
            val imgView = ImageView(applicationContext)
            imgView.scaleType = ImageView.ScaleType.CENTER_CROP
            //imgView.setPadding(8, 8, 8, 8)
            imgView
        }
        binding.carrouselPackageDetailDestinationImages.setInAnimation(
            this,
            android.R.anim.fade_in
        );
        binding.carrouselPackageDetailDestinationImages.setOutAnimation(
            this,
            android.R.anim.fade_out
        );
        setUpSliderControllers()
    }

    private fun setUpSliderControllers() {
        binding.arrowNextImage.setOnClickListener {
            destinationImageListIndex =
                if (destinationImageListIndex + 1 < destinationImageList.size) destinationImageListIndex + 1 else 0
            if (destinationImageList.isNotEmpty()) {
                binding.carrouselPackageDetailDestinationImages.setImageDrawable(
                    destinationImageList[destinationImageListIndex]
                )

            }
        }
        binding.arrowPreviousImage.setOnClickListener {
            destinationImageListIndex =
                if (destinationImageListIndex - 1 >= 0) destinationImageListIndex - 1 else 2
            if (destinationImageList.isNotEmpty()) {
                binding.carrouselPackageDetailDestinationImages.setImageDrawable(
                    destinationImageList[destinationImageListIndex]
                )

            }
        }
    }

    private fun downloadImages(imagesUrl: List<String>) {
        imagesUrl.forEach { url ->
            Picasso.get().load(url).into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    bitmap?.let {
                        destinationImageList.add(it.toDrawable(resources))
                        binding.carrouselPackageDetailDestinationImages.setImageDrawable(
                            destinationImageList[0]
                        )
                    }
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }

            })
        }

    }

    private fun getPriceWithFee(tourPackage: TourPackage): Double {

        val getFinalPrice: (Double, Double) -> Double =
            { packagePrice, commission -> packagePrice + commission }

        return getFinalPrice(
            tourPackage.price,
            tourPackage.transport.calculateCommission(tourPackage.price)
        )

    }

    private fun makePurchase(user: User, tourPackage: TourPackage, finalPrice: Double) {
        if (user.money >= finalPrice) {
            PurchaseRepository.add(
                Purchase(
                    tourPackage.hashCode().toLong(),
                    user.id,
                    tourPackage.id,
                    finalPrice,
                    LocalDate.now().toString()
                )
            )
            user.descontar(user.money, finalPrice)
            Toast.makeText(this@PackageDetail, "Compra realizada", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(
                this@PackageDetail,
                "Saldo insuficiente, tiene $${user.money}",
                Toast.LENGTH_SHORT
            )
                .show()
        }

    }

}