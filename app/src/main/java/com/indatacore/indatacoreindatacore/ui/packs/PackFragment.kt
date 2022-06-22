package com.indatacore.indatacoreindatacore.ui.packs

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.indatacore.indatacoreindatacore.R
import com.indatacore.indatacoreindatacore.data.SliderItem
import com.indatacore.indatacoreindatacore.databinding.FragmentPackBinding
import com.indatacore.indatacoreindatacore.ui.packs.adapter.SliderAdapter
import com.indatacore.indatacoreindatacore.ui.packs.pop.CustomDialogFragment
import java.lang.Math.abs

class PackFragment : Fragment() {
    private lateinit var binding: FragmentPackBinding
    private lateinit var viewModel: PackViewModel

    private lateinit var sliderItemList: ArrayList<SliderItem>
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var sliderHandler: Handler
    private lateinit var sliderRun: Runnable
    private var position: Int = 0
    private var tot: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPackBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[PackViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

/*        if ((activity as MainActivity).supportActionBar!!.isShowing)
            (activity as MainActivity).supportActionBar!!.hide()*/

        itemSliderView()
        sliderItems()
        binding.buttonDetails.setOnClickListener {
            Log.e("buttonDetails 0", "buttonDetails="+position)
            Log.e("buttonDetails 1", "buttonDetails="+sliderItemList[position].id+sliderItemList[position].packName)
            //binding.viewPagerImgSlider.setCurrentItem(binding.viewPagerImgSlider.currentItem - 1)
            dilog(sliderItemList[position - 1])
        }

        binding.buttonPanier.setOnClickListener{

        }

        binding.imageViewLeft.setOnClickListener{
            binding.viewPagerImgSlider.setCurrentItem(binding.viewPagerImgSlider.currentItem - 1)
        }

        binding.imageViewRight.setOnClickListener{
            binding.viewPagerImgSlider.setCurrentItem(binding.viewPagerImgSlider.currentItem + 1)
        }

        binding.buttonContinuer.setOnClickListener{
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_packFragment_to_panierFragment)
        }
        binding.buttonPanier.setOnClickListener{
            if(sliderItemList[position - 1].panier){
                sliderItemList[position - 1].panier = false

                binding.buttonPanier.setTextColor(Color.parseColor("#FB8704"))
                binding.buttonPanier.text = "Ajouter au panier"
            }else{
                sliderItemList[position - 1].panier = true
                binding.buttonPanier.setTextColor(Color.parseColor("#D50000"))
                binding.buttonPanier.text = "Retirer du panier"
            }
            totProduit()
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
        sliderAdapter = SliderAdapter(binding.viewPagerImgSlider, sliderItemList)
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
                    this@PackFragment.position = sliderItemList[position].id
                    binding.textViewPackName.text = sliderItemList[position].packName
                    binding.textViewType.text = sliderItemList[position].typeCard
                    binding.textViewCharacteristicOn.text = ""+sliderItemList[position].characteristic[0]
                    binding.textViewCharacteristicTwo.text = sliderItemList[position].characteristic[1]

                    totProduit()



                    if(sliderItemList[position].panier){
                        binding.buttonPanier.setTextColor(Color.parseColor("#D50000"))
                        binding.buttonPanier.text = "Retirer du panier"
                    }else{
                        binding.buttonPanier.setTextColor(Color.parseColor("#FB8704"))
                        binding.buttonPanier.text = "Ajouter au panier"
                    }

                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRun)
                    sliderHandler.postDelayed(sliderRun, 2000)

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
        binding.totButton.text = tot.toString()+" produit(s)"
        return tot
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRun)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRun, 2000)
    }
    private fun itemSliderView() {
        PackViewModel().getSliderItemList()
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