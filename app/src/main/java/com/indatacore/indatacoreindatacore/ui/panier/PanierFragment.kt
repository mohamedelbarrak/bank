package com.indatacore.indatacoreindatacore.ui.panier

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.indatacore.indatacoreindatacore.data.SliderItem
import com.indatacore.indatacoreindatacore.databinding.FragmentPanierBinding
import com.indatacore.indatacoreindatacore.ui.packs.adapter.SliderAdapter2
import com.indatacore.indatacoreindatacore.ui.packs.pop.CustomDialogFragment
import java.lang.Math.abs

class PanierFragment : Fragment() {
    private lateinit var binding: FragmentPanierBinding
    private lateinit var viewModel: PanierViewModel

    private lateinit var sliderItemList: ArrayList<SliderItem>
    private lateinit var sliderAdapter: SliderAdapter2
    private lateinit var sliderHandler: Handler
    private lateinit var sliderRun: Runnable
    private var position: Int = 0
    private var tot: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPanierBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[PanierViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

/*        if ((activity as MainActivity).supportActionBar!!.isShowing)
            (activity as MainActivity).supportActionBar!!.hide()*/

        itemSliderView()
        sliderItems()



        binding.imageViewBack.setOnClickListener{
            getActivity()?.onBackPressed();
        }

        return binding.root
    }

    private fun dilog(sliderItem: SliderItem) {
        var dialog = CustomDialogFragment(sliderItem)

        //dialog.show(parentFragmentManager, "")
        dialog.show(childFragmentManager, "")

    }

    private fun sliderItems() {
        Log.e("sliderItems", "1")
        sliderItemList = ArrayList()
        sliderAdapter = SliderAdapter2(binding.viewPagerImgSlider, sliderItemList)
        binding.viewPagerImgSlider.adapter = sliderAdapter
        binding.viewPagerImgSlider.clipToPadding = false
        binding.viewPagerImgSlider.clipChildren = false
        binding.viewPagerImgSlider.offscreenPageLimit = 3
        binding.viewPagerImgSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        binding.viewPagerImgSlider.setPageTransformer(comPosPageTarn)
        sliderHandler = Handler()
        sliderRun = Runnable {
            binding.viewPagerImgSlider.currentItem = binding.viewPagerImgSlider.currentItem + 1
        }

        Log.e("sliderItems", "2"+binding.viewPagerImgSlider.id)
        binding.viewPagerImgSlider.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){

                @SuppressLint("ResourceAsColor")
                override fun onPageSelected(position: Int) {
                    Log.e("sliderItems", sliderItemList[position].packName)
                    this@PanierFragment.position = sliderItemList[position].id

                    totProduit()



                    super.onPageSelected(position)
                    //sliderHandler.removeCallbacks(sliderRun)
                    //sliderHandler.postDelayed(sliderRun, 2000)

                }
            }
        )
    }

    private fun totProduit(): Int {
        var tot = 0
        //sliderItemList.clear()
        for (i in sliderItemList.subList(0, 5)) {
            if(i.panier){
                tot += 1
                Log.e(".tot", tot.toString())
                Log.e("i:", i.id.toString())
            }
        }
        Log.e("..tot", sliderItemList.size.toString())
        return tot
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRun)
    }

    override fun onResume() {
        super.onResume()
        //sliderHandler.postDelayed(sliderRun, 2000)
    }
    private fun itemSliderView() {
        PanierViewModel().getSliderItemList()
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    sliderItemList.clear()
                    for (item in 0 until it.size) {
                        sliderItemList.add(it[item])
                    }
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


}
