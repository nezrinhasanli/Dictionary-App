package com.nezrin.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import com.nezrin.sozlukuygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var kelimelerListe: ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    private lateinit var vt: VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        veritabaniKopyala()

        binding.toolbar.title = "Sözlük Uygulaması"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)

        vt= VeritabaniYardimcisi(this)

      kelimelerListe=KelimelerDao().tumKelimeler(vt)

        adapter = KelimelerAdapter(this, kelimelerListe)
        binding.rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val item = menu?.findItem(R.id.actionAra)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean { //ara'ya basanda verini gonderir
        Log.e("gonderilen arama", query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean { //her yazdigimiz herfde arama islemi gorur
        Log.e("harf girdikce", newText!!)
        return true
    }

    fun veritabaniKopyala() {
        val copyHelper = DatabaseCopyHelper(this)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}