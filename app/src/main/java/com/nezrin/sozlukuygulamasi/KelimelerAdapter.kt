package com.nezrin.sozlukuygulamasi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class KelimelerAdapter(private val mContext: Context, private val kelimlerListe: List<Kelimeler>) :
    RecyclerView.Adapter<KelimelerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim) {
        var kelime_card: CardView
        var tvIngilizce: TextView
        var tvTurkce: TextView

        init {
            kelime_card = tasarim.findViewById(R.id.kelime_card)
            tvIngilizce = tasarim.findViewById(R.id.tvIngilizce)
            tvTurkce = tasarim.findViewById(R.id.tvTurkce)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_tasarim, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val kelime=kelimlerListe.get(position)
        holder.tvIngilizce.text=kelime.ingilizce
        holder.tvTurkce.text=kelime.turkce

        holder.kelime_card.setOnClickListener {

            val intent=Intent(mContext,DetayActivity::class.java)
            intent.putExtra("nesne",kelime)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return kelimlerListe.size
    }
}