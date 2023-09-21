package com.example.ganasteapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ganasteapp.databinding.FragmentBanksBinding
import com.example.ganasteapp.ui.viewmodel.BankViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BanksFragment : Fragment() {
    private var _binding: FragmentBanksBinding? = null
    private val viewModel: BankViewModel by viewModels()
    private lateinit var bankAdapter: BankAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBanksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    @SuppressLint("VisibleForTests")
    private fun initView() {
        viewModel.isLoading.observe(viewLifecycleOwner) { show ->
            binding.progress.isVisible = show
        }

        viewModel.banks.observe(viewLifecycleOwner) { banks ->
            bankAdapter = BankAdapter(banks) { bank ->
                Toast.makeText(context, "bank: ${bank.name}", Toast.LENGTH_SHORT).show()
            }
            binding.recyclerView.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                adapter = this@BanksFragment.bankAdapter
            }
        }

        binding.swiperefresh.setOnRefreshListener {
            viewModel.getAllBanksFromDatabase()
            binding.swiperefresh.isRefreshing = false
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
