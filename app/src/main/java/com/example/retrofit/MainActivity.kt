package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    //instance to our binding instance
    private lateinit var binding: ActivityMainBinding
    // creating an instance of our recycler view adapter
    private  lateinit var todoAdapter:TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        //make our api request by launching a Coroutine

        lifecycleScope.launchWhenCreated {
            //when making our api request our progressBar should be visible
            binding.progressBar.isVisible = true
            //putting our response in a try and catch block
            val response=try{
                Retrofitinstance.api.getJsonTodos()
            }catch(e: IOException){
                Log.e(TAG,"IOException ,you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }catch(e: HttpException){
                Log.e(TAG,"HttpException ,unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated

            }
            //checking if response is successful
            if(response.isSuccessful && response.body()!=null){
                //set our todos list to our adapter
                todoAdapter.todos = response.body()!!
            }else{
                Log.e(TAG,"Response not successful")
            }
            binding.progressBar.isVisible = false
        }




    }
    //function to initialize the recycler view and setting it up
    private fun setupRecyclerView() = binding.rtodos.apply{
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}