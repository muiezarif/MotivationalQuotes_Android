package com.muiezarif.motivationalquotes.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.muiezarif.motivationalquotes.R
import com.muiezarif.motivationalquotes.adapters.QuotesVPAdapter
import com.muiezarif.motivationalquotes.databinding.ActivityFullQuoteViewBinding
import com.muiezarif.motivationalquotes.db.DbQueries
import com.muiezarif.motivationalquotes.db.models.QuotesModel
import com.muiezarif.motivationalquotes.fragments.QuotesFragment
import com.muiezarif.motivationalquotes.utils.AdHelper


class FullQuoteViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullQuoteViewBinding
    private lateinit var dbQuery: DbQueries
    private lateinit var quoteList:ArrayList<QuotesModel>
    private var adapter: QuotesVPAdapter?=null
    private var position=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_full_quote_view)
        Log.i("TESTING","InsideFullQUOTE 4")
        binding.fullQuoteHeader.ivBack.visibility= View.VISIBLE
        binding.fullQuoteHeader.tvHomeTitle.text= "Quotes"
        binding.fullQuoteHeader.ivBack.setOnClickListener {
            finish()
        }
        if(intent.hasExtra("from")){
            if (intent.extras?.getString("from").equals("quotesFrag")){
                position= intent?.extras?.getInt("quotePos",0)!!
                quoteList= QuotesFragment.awesomelist!!
                adapter=  QuotesVPAdapter(this,quoteList)
                binding.vpQuotes.adapter=adapter
                binding.vpQuotes.currentItem = position
            }else if(intent.extras?.getString("from").equals("listQuotes")){
                position= intent.extras?.getInt("quotePos",0)!!
                quoteList= intent.getSerializableExtra("list") as ArrayList<QuotesModel>
                adapter=  QuotesVPAdapter(this,quoteList)
                binding.vpQuotes.adapter=adapter
                binding.vpQuotes.currentItem = position
            }
        }
        AdHelper.loadBannerAd(binding.adView)


    }
}
