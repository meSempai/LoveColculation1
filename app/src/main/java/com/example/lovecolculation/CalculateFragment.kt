package com.example.lovecolculation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.lovecolculation.databinding.FragmentCalculateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalculateFragment : Fragment() {
    private lateinit var binding: FragmentCalculateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        with(binding) {
            calculate.setOnClickListener {
                RetrofitService().api.percentageNames(
                    firstName.text.toString(),
                    secondName.text.toString()
                ).enqueue(object : Callback<LoveModel> {
                    override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                        if (response.isSuccessful) {
                            Log.e("ololo", "onResponse:${response.body()} ")
                            findNavController().navigate(R.id.resultFragment,
                                bundleOf("Result" to response.body()))
                        }
                    }

                    override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                        Log.e("ololo", "onFailure:${t.message} ")
                    }

                })
            }
        }
    }
}