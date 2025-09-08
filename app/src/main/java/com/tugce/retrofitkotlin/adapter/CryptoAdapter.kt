package com.tugce.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugce.retrofitkotlin.model.Crypto
import com.tugce.retrofitkotlin.R
import com.tugce.retrofitkotlin.databinding.RowLayoutBinding

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    private val cryptoList = ArrayList<Crypto>()

    class CryptoViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val crypto = cryptoList[position]
        holder.binding.apply {
            textCurrency.text = crypto.currency
            textPrice.text = crypto.price
            try {
                val value = crypto.price.replace("$", "").toDouble()
                textPrice.setTextColor(if (value >= 0) Color.parseColor("#4CAF50") else Color.RED)
            } catch (e: Exception) {
                textPrice.setTextColor(Color.BLACK)
            }
        }
    }

    override fun getItemCount(): Int = cryptoList.size

    fun updateList(newList: List<Crypto>) {
        cryptoList.clear()
        cryptoList.addAll(newList)
        notifyDataSetChanged()
    }
}