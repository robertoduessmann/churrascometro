package com.crave.bbq

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.crave.bbq.calc.BarbecueCalc
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val quantityMap = mapOf<Int, TextView>(
            menSeek.id to menQuantity,
            womenSeek.id to womenQuantity,
            childrenSeek.id to childrenQuantity
    )

    private val onSeek: SeekBar.OnSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            quantityMap[seekBar.id]?.text = "${seekBar.progress}"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setListeners()

        MobileAds.initialize(this, BuildConfig.AdsAPIKey)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    private fun setListeners() {
        barbecueCalc.setOnClickListener {
            calculate()
        }
        menSeek.setOnSeekBarChangeListener(onSeek)
        womenSeek.setOnSeekBarChangeListener(onSeek)
        childrenSeek.setOnSeekBarChangeListener(onSeek)
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {
        val barbecue = BarbecueCalc(
                menSeek.doubleProgress,
                womenSeek.doubleProgress,
                childrenSeek.doubleProgress
        )
        meatQuantity.text = "${barbecue.meatQuantity} $DEFAULT_UNIT"
        sausageQuantity.text = "${barbecue.sausageQuantity} $DEFAULT_UNIT"
    }

    companion object {
        private const val DEFAULT_UNIT = "Kg"
    }
}

private val SeekBar.doubleProgress: Double
    get() {
        return progress.toDouble()
    }
