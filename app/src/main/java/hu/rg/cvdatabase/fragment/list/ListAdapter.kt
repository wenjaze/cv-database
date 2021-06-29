package hu.rg.cvdatabase.fragment.list

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.CVDatabase
import hu.rg.cvdatabase.data.entities.CV
import kotlinx.android.synthetic.main.cv_preview_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var cvList = emptyList<CV>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cv_preview_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = cvList[position]

        holder.itemView.nameText.text = currentItem.name
        holder.itemView.cvPreviewItem.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.cvPreviewItem.imageView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToCVFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = cvList.size

    fun setList(cvs : List<CV>){
        this.cvList = cvs
        notifyDataSetChanged()
    }

}