package com.denobaba.kriptoparauygulamasi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denobaba.kriptoparauygulamasi.databinding.RecycleRowBinding
import com.denobaba.kriptoparauygulamasi.model.CryptoModel

class recycleviewadaptor(private val cryptoList: ArrayList<CryptoModel>?) : RecyclerView.Adapter<recycleviewadaptor.RowHolder>() {

    inner class RowHolder(val binding: RecycleRowBinding): RecyclerView.ViewHolder(binding.root) {


    }

    fun updateData(newData: List<CryptoModel>) {
        cryptoList?.clear()
        cryptoList?.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {

        val binding = RecycleRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList!!.size


    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        cryptoList?.let { list ->
            val cryptoModel = list.getOrNull(position)
            if (cryptoModel != null) {
                holder.binding.name.text = cryptoModel.currency
                holder.binding.price.text = cryptoModel.price.toString()
                Log.i("onBindViewHolder", "Bound data at position $position: ${cryptoModel.currency}")
            } else {
                Log.e("onBindViewHolder", "No data at position $position")
            }
        } ?: run {
            Log.e("onBindViewHolder", "cryptoList is null")
        }
    }


}