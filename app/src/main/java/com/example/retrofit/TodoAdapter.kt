package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)
    private val diffcallback = object : DiffUtil.ItemCallback<JsonTodo>(){
        override fun areItemsTheSame(oldItem: JsonTodo, newItem: JsonTodo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JsonTodo, newItem: JsonTodo): Boolean {
            return  oldItem.id == newItem.id
        }
    }
    private val differ = AsyncListDiffer(this,diffcallback)
    var todos:List<JsonTodo>
        get() = differ.currentList
    set(value) {differ.submitList(value)}

    override fun getItemCount() = todos.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }
    // set the data in our recycler view
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
           holder.binding.apply{
               val JsonTodo = todos[position]
               tvTitle.text = JsonTodo.title
               cbDone.isChecked = JsonTodo.completed
           }
    }

}