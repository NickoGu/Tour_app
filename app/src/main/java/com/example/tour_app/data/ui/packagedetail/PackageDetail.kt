package com.example.tour_app.data.ui.packagedetail

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.lifecycleScope
import com.example.tour_app.Constantes
import com.example.tour_app.R
import com.example.tour_app.data.tour.TourPackage
import com.example.tour_app.databinding.ActivityPackageDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repositories.PackageRepository

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
        //setSupportActionBar(binding.detailToolbar)
        if (packageToBuyId != null) {
            PackageRepository.getById(packageToBuyId)?.let {
                initUI(it)
            }
        }

    }

    private fun initUI(packageToBuy: TourPackage) {
        Picasso.get().load(packageToBuy.logo).into(binding.ivPackageDetailImage)
        binding.tvPackageDetailTitle.text = packageToBuy.name
        binding.tvPackageDetailRating.text =
            getString(R.string.detail_package_rating, packageToBuy.stars.toString())
        binding.tvPackageDetailTransport.text =
            getString(R.string.transporte_package_detail, packageToBuy.transport)
        binding.tvPackageDetailDuration.text =
            getString(R.string.tour_duration_detail_text, packageToBuy.duration.toString())
        binding.tvPackageDetailPrice.text =
            getString(R.string.precio_package_detail, packageToBuy.price.toString())
        //TODO Price after comission
        binding.tvPackageDetailDescription.text = packageToBuy.destination.description
        binding.tvPackageDetailDestiny.text =
            getString(R.string.destination_name_detail, packageToBuy.destination.name)
        lifecycleScope.launch {
            downloadImages(packageToBuy.destination.pictures)
            setUpImageSlider()
        }
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
        binding.carrouselPackageDetailDestinationImages.setImageDrawable(destinationImageList[0])
        setUpSliderControllers()
    }

    private fun setUpSliderControllers() {
        binding.arrowNextImage.setOnClickListener {
            destinationImageListIndex =
                if (destinationImageListIndex + 1 < destinationImageList.size) destinationImageListIndex + 1 else 0
            binding.carrouselPackageDetailDestinationImages.setImageDrawable(destinationImageList[destinationImageListIndex])
        }
        binding.arrowPreviousImage.setOnClickListener {
            destinationImageListIndex =
                if (destinationImageListIndex - 1 >= 0) destinationImageListIndex - 1 else 2
            binding.carrouselPackageDetailDestinationImages.setImageDrawable(destinationImageList[destinationImageListIndex])
        }
    }

    private suspend fun downloadImages(imagesUrl: List<String>) {
        withContext(Dispatchers.IO) {
            val downloadResults = mutableListOf<Deferred<BitmapDrawable>>()
            imagesUrl.forEach { imageUrl ->
                val request = async { Picasso.get().load(imageUrl).get().toDrawable(resources) }
                downloadResults.add(request)
            }
            try {
                downloadResults.awaitAll().toCollection(destinationImageList)
            }catch (e: Exception){
                //TODO handle error and fill with images without error
            }
        }
    }
}