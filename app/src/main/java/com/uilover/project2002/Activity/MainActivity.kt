package com.uilover.project2002.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.uilover.project2002.Adapter.CategoryAdapter
import com.uilover.project2002.Adapter.RecommendedAdapter
import com.uilover.project2002.Adapter.SliderAdapter
import com.uilover.project2002.Model.SliderModel
import com.uilover.project2002.ViewModel.MainViewModel
import com.uilover.project2002.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel=MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding=ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initBanner()
    initCategory()
    initRecommeded()
    }

    private fun initRecommeded() {
        binding.progressBarRecommend.visibility = View.VISIBLE
        viewModel.recommended.observe(this, Observer {
            binding.viewRecommendation.layoutManager=GridLayoutManager(this@MainActivity, 2)
            binding.viewRecommendation.adapter=RecommendedAdapter(it)
            binding.progressBarRecommend.visibility=View.VISIBLE
        })
        viewModel.loadRecommended()
    }
    private fun initCategory() {
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.categories.observe(this, Observer {
            binding.viewCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
            binding.viewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }

    private fun banners(image:List<SliderModel>){
        binding.viewPager2.adapter=SliderAdapter(image,binding.viewPager2)
        binding.viewPager2.clipToPadding=false
        binding.viewPager2.clipChildren=false
        binding.viewPager2.offscreenPageLimit=3
        binding.viewPager2.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER

        val  compositePageTransformer=CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPager2.setPageTransformer(compositePageTransformer)

        if (image.size > 1){
            binding.doIndicator.visibility= View.VISIBLE
            binding.doIndicator.attachTo(binding.viewPager2)
        }
    }

    private fun initBanner(){
        binding.progressBarSilder.visibility=View.VISIBLE
        viewModel.banners.observe(this, Observer {
            banners(it)
            binding.progressBarSilder.visibility=View.GONE
        })
        viewModel.loadBanners()
    }
}

