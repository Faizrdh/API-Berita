package com.example.BelajarAPI.ui.detailnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.BelajarAPI.databinding.ActivityDetailNewsBinding
import com.example.BelajarAPI.ui.home.HomeViewModel
import com.example.BelajarAPI.ui.home.ViewModelFactory

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: HomeViewModel by viewModels {
            factory
        }

        val content = intent.getStringExtra(ID) ?: ""

        val bundle = Bundle()
        bundle.putString(ID, content)

        viewModel.getHeadlineNews().observe(this) { result ->
            when(result) {
                is com.example.BelajarAPI.data.Result.Error -> {
                    Toast.makeText(this, "eror", Toast.LENGTH_SHORT).show()
                }
                is com.example.BelajarAPI.data.Result.Success -> {
                    val data = result.data
                    binding.tvContent.text = data.toString()
                }
                is com.example.BelajarAPI.data.Result.Loading -> {
                    Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    companion object {
        const val TITLE = "TITLE"
        const val IMG = "IMG"
        const val ID = "ID"
    }
}