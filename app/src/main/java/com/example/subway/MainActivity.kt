package com.example.subway

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

import com.example.subway.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    var departureStation: String? = null
    var arrivalStation: String? = null
    var transitStation: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Set buttons onClickListener
        setButtonsOnClickListener(binding)
        binding.mainSearchBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        var AdButton1: ImageButton = findViewById(R.id.btn_call)
        var AdButton2: ImageButton = findViewById(R.id.AdButton)
        var AdButton3: ImageButton = findViewById(R.id.AdButton2)
        var Text1: TextView = findViewById(R.id.AdText)

        binding.btnCall.setOnClickListener {
            AdButton1.setVisibility(View.INVISIBLE)
            Text1.setVisibility(View.INVISIBLE)
            AdButton2.setVisibility(View.VISIBLE)
            AdButton3.setVisibility(View.VISIBLE)
        }
        binding.AdButton.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.mju.ac.kr/sites/mjukr/intro/intro.html"));
            startActivity(intent);
        }
        binding.AdButton2.setOnClickListener{
            AdButton2.setVisibility(View.INVISIBLE)
            Text1.setVisibility(View.VISIBLE)
            AdButton3.setVisibility(View.INVISIBLE)
            AdButton1.setVisibility(View.VISIBLE)
        }
        //Set searchBar
        binding.mainSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null && query?.length != 3) {
                    Toast.makeText(applicationContext, "세자리 수를 입력하시오.", Toast.LENGTH_SHORT).show()
                }
                when(query?.toInt()) {
                    101	-> binding.station101.callOnClick()
                    102	-> binding.station102.callOnClick()
                    103	-> binding.station103.callOnClick()
                    104	-> binding.station104.callOnClick()
                    105	-> binding.station105.callOnClick()
                    106	-> binding.station106.callOnClick()
                    107	-> binding.station107.callOnClick()
                    108	-> binding.station108.callOnClick()
                    109	-> binding.station109.callOnClick()
                    110	-> binding.station110.callOnClick()
                    111	-> binding.station111.callOnClick()
                    112	-> binding.station112.callOnClick()
                    113	-> binding.station113.callOnClick()
                    114	-> binding.station114.callOnClick()
                    115	-> binding.station115.callOnClick()
                    116	-> binding.station116.callOnClick()
                    117	-> binding.station117.callOnClick()
                    118	-> binding.station118.callOnClick()
                    119	-> binding.station119.callOnClick()
                    120	-> binding.station120.callOnClick()
                    121	-> binding.station121.callOnClick()
                    122	-> binding.station122.callOnClick()
                    123	-> binding.station123.callOnClick()
                    201	-> binding.station201.callOnClick()
                    202	-> binding.station202.callOnClick()
                    203	-> binding.station203.callOnClick()
                    204	-> binding.station204.callOnClick()
                    205	-> binding.station205.callOnClick()
                    206	-> binding.station206.callOnClick()
                    207	-> binding.station207.callOnClick()
                    208	-> binding.station208.callOnClick()
                    209	-> binding.station209.callOnClick()
                    210	-> binding.station210.callOnClick()
                    211	-> binding.station211.callOnClick()
                    212	-> binding.station212.callOnClick()
                    213	-> binding.station213.callOnClick()
                    214	-> binding.station214.callOnClick()
                    215	-> binding.station215.callOnClick()
                    216	-> binding.station216.callOnClick()
                    217	-> binding.station217.callOnClick()
                    301	-> binding.station301.callOnClick()
                    302	-> binding.station302.callOnClick()
                    303	-> binding.station303.callOnClick()
                    304	-> binding.station304.callOnClick()
                    305	-> binding.station305.callOnClick()
                    306	-> binding.station306.callOnClick()
                    307	-> binding.station307.callOnClick()
                    308	-> binding.station308.callOnClick()
                    401	-> binding.station401.callOnClick()
                    402	-> binding.station402.callOnClick()
                    403	-> binding.station403.callOnClick()
                    404	-> binding.station404.callOnClick()
                    405	-> binding.station405.callOnClick()
                    406	-> binding.station406.callOnClick()
                    407	-> binding.station407.callOnClick()
                    408	-> binding.station408.callOnClick()
                    409	-> binding.station409.callOnClick()
                    410	-> binding.station410.callOnClick()
                    411	-> binding.station411.callOnClick()
                    412	-> binding.station412.callOnClick()
                    413	-> binding.station413.callOnClick()
                    414	-> binding.station414.callOnClick()
                    415	-> binding.station415.callOnClick()
                    416	-> binding.station416.callOnClick()
                    417	-> binding.station417.callOnClick()
                    501	-> binding.station501.callOnClick()
                    502	-> binding.station502.callOnClick()
                    503	-> binding.station503.callOnClick()
                    504	-> binding.station504.callOnClick()
                    505	-> binding.station505.callOnClick()
                    506	-> binding.station506.callOnClick()
                    507	-> binding.station507.callOnClick()
                    601	-> binding.station601.callOnClick()
                    602	-> binding.station602.callOnClick()
                    603	-> binding.station603.callOnClick()
                    604	-> binding.station604.callOnClick()
                    605	-> binding.station605.callOnClick()
                    606	-> binding.station606.callOnClick()
                    607	-> binding.station607.callOnClick()
                    608	-> binding.station608.callOnClick()
                    609	-> binding.station609.callOnClick()
                    610	-> binding.station610.callOnClick()
                    611	-> binding.station611.callOnClick()
                    612	-> binding.station612.callOnClick()
                    613	-> binding.station613.callOnClick()
                    614	-> binding.station614.callOnClick()
                    615	-> binding.station615.callOnClick()
                    616	-> binding.station616.callOnClick()
                    617	-> binding.station617.callOnClick()
                    618	-> binding.station618.callOnClick()
                    619	-> binding.station619.callOnClick()
                    620	-> binding.station620.callOnClick()
                    621	-> binding.station621.callOnClick()
                    622	-> binding.station622.callOnClick()
                    701	-> binding.station701.callOnClick()
                    702	-> binding.station702.callOnClick()
                    703	-> binding.station703.callOnClick()
                    704	-> binding.station704.callOnClick()
                    705	-> binding.station705.callOnClick()
                    706	-> binding.station706.callOnClick()
                    707	-> binding.station707.callOnClick()
                    801	-> binding.station801.callOnClick()
                    802	-> binding.station802.callOnClick()
                    803	-> binding.station803.callOnClick()
                    804	-> binding.station804.callOnClick()
                    805	-> binding.station805.callOnClick()
                    806	-> binding.station806.callOnClick()
                    901	-> binding.station901.callOnClick()
                    902	-> binding.station902.callOnClick()
                    903	-> binding.station903.callOnClick()
                    904	-> binding.station904.callOnClick()
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
    
    override fun onClick(v: View?) {
        var station: String? = null
        station = getButtonsStation(v)
        var fragment = StationInfoFragment()
        var bundle = Bundle()
        bundle.putString("station", station)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_station_info, fragment)
            .addToBackStack(null).commit()
    }
    fun setButtonsOnClickListener(binding: ActivityMainBinding) {
        binding.station101.setOnClickListener(this)
        binding.station201.setOnClickListener(this)
        binding.station301.setOnClickListener(this)
        binding.station401.setOnClickListener(this)
        binding.station501.setOnClickListener(this)
        binding.station601.setOnClickListener(this)
        binding.station701.setOnClickListener(this)
        binding.station801.setOnClickListener(this)
        binding.station901.setOnClickListener(this)
        binding.station303.setOnClickListener(this)
        binding.station307.setOnClickListener(this)
        binding.station306.setOnClickListener(this)
        binding.station101.setOnClickListener(this)
        binding.station102.setOnClickListener(this)
        binding.station103.setOnClickListener(this)
        binding.station104.setOnClickListener(this)
        binding.station105.setOnClickListener(this)
        binding.station106.setOnClickListener(this)
        binding.station107.setOnClickListener(this)
        binding.station108.setOnClickListener(this)
        binding.station109.setOnClickListener(this)
        binding.station110.setOnClickListener(this)
        binding.station111.setOnClickListener(this)
        binding.station112.setOnClickListener(this)
        binding.station113.setOnClickListener(this)
        binding.station114.setOnClickListener(this)
        binding.station115.setOnClickListener(this)
        binding.station116.setOnClickListener(this)
        binding.station117.setOnClickListener(this)
        binding.station118.setOnClickListener(this)
        binding.station119.setOnClickListener(this)
        binding.station120.setOnClickListener(this)
        binding.station121.setOnClickListener(this)
        binding.station122.setOnClickListener(this)
        binding.station123.setOnClickListener(this)
        binding.station201.setOnClickListener(this)
        binding.station202.setOnClickListener(this)
        binding.station203.setOnClickListener(this)
        binding.station204.setOnClickListener(this)
        binding.station205.setOnClickListener(this)
        binding.station206.setOnClickListener(this)
        binding.station207.setOnClickListener(this)
        binding.station208.setOnClickListener(this)
        binding.station209.setOnClickListener(this)
        binding.station210.setOnClickListener(this)
        binding.station211.setOnClickListener(this)
        binding.station212.setOnClickListener(this)
        binding.station213.setOnClickListener(this)
        binding.station214.setOnClickListener(this)
        binding.station215.setOnClickListener(this)
        binding.station216.setOnClickListener(this)
        binding.station217.setOnClickListener(this)
        binding.station301.setOnClickListener(this)
        binding.station302.setOnClickListener(this)
        binding.station303.setOnClickListener(this)
        binding.station304.setOnClickListener(this)
        binding.station305.setOnClickListener(this)
        binding.station306.setOnClickListener(this)
        binding.station307.setOnClickListener(this)
        binding.station308.setOnClickListener(this)
        binding.station401.setOnClickListener(this)
        binding.station402.setOnClickListener(this)
        binding.station403.setOnClickListener(this)
        binding.station404.setOnClickListener(this)
        binding.station405.setOnClickListener(this)
        binding.station406.setOnClickListener(this)
        binding.station407.setOnClickListener(this)
        binding.station408.setOnClickListener(this)
        binding.station409.setOnClickListener(this)
        binding.station410.setOnClickListener(this)
        binding.station411.setOnClickListener(this)
        binding.station412.setOnClickListener(this)
        binding.station413.setOnClickListener(this)
        binding.station414.setOnClickListener(this)
        binding.station415.setOnClickListener(this)
        binding.station416.setOnClickListener(this)
        binding.station417.setOnClickListener(this)
        binding.station501.setOnClickListener(this)
        binding.station502.setOnClickListener(this)
        binding.station503.setOnClickListener(this)
        binding.station504.setOnClickListener(this)
        binding.station505.setOnClickListener(this)
        binding.station506.setOnClickListener(this)
        binding.station507.setOnClickListener(this)
        binding.station601.setOnClickListener(this)
        binding.station602.setOnClickListener(this)
        binding.station603.setOnClickListener(this)
        binding.station604.setOnClickListener(this)
        binding.station605.setOnClickListener(this)
        binding.station606.setOnClickListener(this)
        binding.station607.setOnClickListener(this)
        binding.station608.setOnClickListener(this)
        binding.station609.setOnClickListener(this)
        binding.station610.setOnClickListener(this)
        binding.station611.setOnClickListener(this)
        binding.station612.setOnClickListener(this)
        binding.station613.setOnClickListener(this)
        binding.station614.setOnClickListener(this)
        binding.station615.setOnClickListener(this)
        binding.station616.setOnClickListener(this)
        binding.station617.setOnClickListener(this)
        binding.station618.setOnClickListener(this)
        binding.station619.setOnClickListener(this)
        binding.station620.setOnClickListener(this)
        binding.station621.setOnClickListener(this)
        binding.station622.setOnClickListener(this)
        binding.station701.setOnClickListener(this)
        binding.station702.setOnClickListener(this)
        binding.station703.setOnClickListener(this)
        binding.station704.setOnClickListener(this)
        binding.station705.setOnClickListener(this)
        binding.station706.setOnClickListener(this)
        binding.station707.setOnClickListener(this)
        binding.station801.setOnClickListener(this)
        binding.station802.setOnClickListener(this)
        binding.station803.setOnClickListener(this)
        binding.station804.setOnClickListener(this)
        binding.station805.setOnClickListener(this)
        binding.station806.setOnClickListener(this)
        binding.station901.setOnClickListener(this)
        binding.station902.setOnClickListener(this)
        binding.station903.setOnClickListener(this)
        binding.station904.setOnClickListener(this)
    }
    fun getButtonsStation(v: View?): String {
        when (v?.id) {
            binding.station101.id -> return "101"
            binding.station201.id -> return "201"
            binding.station301.id -> return "301"
            binding.station401.id -> return "401"
            binding.station501.id -> return "501"
            binding.station601.id -> return "601"
            binding.station701.id -> return "701"
            binding.station801.id -> return "801"
            binding.station901.id -> return "901"
            binding.station303.id -> return "303"
            binding.station307.id -> return "307"
            binding.station306.id -> return "306"
            binding.station101.id -> return "101"
            binding.station102.id -> return "102"
            binding.station103.id -> return "103"
            binding.station104.id -> return "104"
            binding.station105.id -> return "105"
            binding.station106.id -> return "106"
            binding.station107.id -> return "107"
            binding.station108.id -> return "108"
            binding.station109.id -> return "109"
            binding.station110.id -> return "110"
            binding.station111.id -> return "111"
            binding.station112.id -> return "112"
            binding.station113.id -> return "113"
            binding.station114.id -> return "114"
            binding.station115.id -> return "115"
            binding.station116.id -> return "116"
            binding.station117.id -> return "117"
            binding.station118.id -> return "118"
            binding.station119.id -> return "119"
            binding.station120.id -> return "120"
            binding.station121.id -> return "121"
            binding.station122.id -> return "122"
            binding.station123.id -> return "123"
            binding.station201.id -> return "201"
            binding.station202.id -> return "202"
            binding.station203.id -> return "203"
            binding.station204.id -> return "204"
            binding.station205.id -> return "205"
            binding.station206.id -> return "206"
            binding.station207.id -> return "207"
            binding.station208.id -> return "208"
            binding.station209.id -> return "209"
            binding.station210.id -> return "210"
            binding.station211.id -> return "211"
            binding.station212.id -> return "212"
            binding.station213.id -> return "213"
            binding.station214.id -> return "214"
            binding.station215.id -> return "215"
            binding.station216.id -> return "216"
            binding.station217.id -> return "217"
            binding.station301.id -> return "301"
            binding.station302.id -> return "302"
            binding.station303.id -> return "303"
            binding.station304.id -> return "304"
            binding.station305.id -> return "305"
            binding.station306.id -> return "306"
            binding.station307.id -> return "307"
            binding.station308.id -> return "308"
            binding.station401.id -> return "401"
            binding.station402.id -> return "402"
            binding.station403.id -> return "403"
            binding.station404.id -> return "404"
            binding.station405.id -> return "405"
            binding.station406.id -> return "406"
            binding.station407.id -> return "407"
            binding.station408.id -> return "408"
            binding.station409.id -> return "409"
            binding.station410.id -> return "410"
            binding.station411.id -> return "411"
            binding.station412.id -> return "412"
            binding.station413.id -> return "413"
            binding.station414.id -> return "414"
            binding.station415.id -> return "415"
            binding.station416.id -> return "416"
            binding.station417.id -> return "417"
            binding.station501.id -> return "501"
            binding.station502.id -> return "502"
            binding.station503.id -> return "503"
            binding.station504.id -> return "504"
            binding.station505.id -> return "505"
            binding.station506.id -> return "506"
            binding.station507.id -> return "507"
            binding.station601.id -> return "601"
            binding.station602.id -> return "602"
            binding.station603.id -> return "603"
            binding.station604.id -> return "604"
            binding.station605.id -> return "605"
            binding.station606.id -> return "606"
            binding.station607.id -> return "607"
            binding.station608.id -> return "608"
            binding.station609.id -> return "609"
            binding.station610.id -> return "610"
            binding.station611.id -> return "611"
            binding.station612.id -> return "612"
            binding.station613.id -> return "613"
            binding.station614.id -> return "614"
            binding.station615.id -> return "615"
            binding.station616.id -> return "616"
            binding.station617.id -> return "617"
            binding.station618.id -> return "618"
            binding.station619.id -> return "619"
            binding.station620.id -> return "620"
            binding.station621.id -> return "621"
            binding.station622.id -> return "622"
            binding.station701.id -> return "701"
            binding.station702.id -> return "702"
            binding.station703.id -> return "703"
            binding.station704.id -> return "704"
            binding.station705.id -> return "705"
            binding.station706.id -> return "706"
            binding.station707.id -> return "707"
            binding.station801.id -> return "801"
            binding.station802.id -> return "802"
            binding.station803.id -> return "803"
            binding.station804.id -> return "804"
            binding.station805.id -> return "805"
            binding.station806.id -> return "806"
            binding.station901.id -> return "901"
            binding.station902.id -> return "902"
            binding.station903.id -> return "903"
            binding.station904.id -> return "904"
        }
        return "null"
    }
}