
package com.anushka.roomdemo


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.anushka.roomdemo.databinding.ListItemBinding
import com.anushka.roomdemo.db.Subscriber
import com.anushka.roomdemo.db.SubscriberDAO
import com.anushka.roomdemo.db.SubscriberRepository
import com.anushka.roomdemo.generated.callback.OnClickListener

//private val clickListener:(Subscriber)->Unit,
class MyRecyclerViewAdapter(private val context : Context, private val subscriberViewModel: SubscriberViewModel)
    : RecyclerView.Adapter<MyViewHolder>()
{
    private var subscribersList: LiveData<List<Subscriber>>

    init{
        subscribersList = subscriberViewModel.subscribers
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding : ListItemBinding =
          DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
      return MyViewHolder(binding, context, subscriberViewModel)
    }

    override fun getItemCount(): Int {
       return subscribersList.value!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //,clickListener
      holder.bind(subscribersList.value!![position])
    }

}

class MyViewHolder(val binding: ListItemBinding, val context: Context, private val subscriberViewModel : SubscriberViewModel):RecyclerView.ViewHolder(binding.root){
//,clickListener:(Subscriber)->Unit
    fun bind(subscriber: Subscriber){
          binding.nameTextView.text = subscriber.name
          binding.emailTextView.text = subscriber.email
          binding.listItemLayout.setOnClickListener{
              Toast.makeText(context,"selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
             //clickListener(subscriber)
              subscriberViewModel.initUpdateAndDelete(subscriber)
          }
    }
}