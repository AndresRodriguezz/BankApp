package com.example.ganasteapp.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.example.ganasteapp.databinding.BankItemBinding
import com.example.ganasteapp.domain.model.Bank

class BankAdapter(private var list: List<Bank>, private var onSelect: (Bank) -> Unit) :
    RecyclerView.Adapter<BankAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: BankItemBinding) :
        ViewHolder(binding.root) {
        fun bind(bank: Bank) {
            binding.title.text = bank.name
            binding.description.text = bank.description
            binding.age.text = bank.age
            binding.image.load(bank.url) {
                precision(Precision.EXACT)
                scale(Scale.FILL)
            }
            binding.card.setOnClickListener {
                onSelect(bank)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankAdapter.MyViewHolder {
        return MyViewHolder(
            BankItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BankAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}
