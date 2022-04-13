package com.study.crawling.ui.grid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.study.crawling.databinding.ActivityGridViewBinding
import com.study.crawling.ui.grid.adapter.GridAdapter
import com.study.crawling.util.RecyclerScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GridActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGridViewBinding
    private val viewModel : GridViewModel by viewModels()
    private lateinit var gridAdapter: GridAdapter

    //크롤링 페이지 숫자, 1부터 시작
    //최대 페이지는 100
    private var page : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewBinding()

        viewModel.getCrawlingImages(page)
        
        //크롤링이 끝나면 어댑터에 아이템 추가
        viewModel.crawlingImageData.observe(this) {
            gridAdapter.addItem(it)
        }

        //리사이클러뷰 스크롤 끝까지 도달 확인
        gridAdapter.setEndScroll(object: RecyclerScrollListener {
            override fun onEndScroll() {
                    viewModel.getCrawlingImages(++page)
            }
        })

        viewModel.toastMessage.observe(this) {
            Toast.makeText(this, "페이지를 불러오기 실패했습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViewBinding() {
        binding = ActivityGridViewBinding.inflate(layoutInflater)
        binding.viewModel = viewModel

        val layoutManager = GridLayoutManager(this,3)
        binding.recyclerView.layoutManager = layoutManager

        binding.lifecycleOwner = this@GridActivity

        gridAdapter = GridAdapter()
        binding.recyclerView.adapter = gridAdapter

        val view = binding.root
        setContentView(view)
    }
}