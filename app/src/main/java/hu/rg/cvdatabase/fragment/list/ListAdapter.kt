package hu.rg.cvdatabase.fragment.list

import android.app.AlertDialog
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.CVDatabase
import hu.rg.cvdatabase.data.entities.CV
import hu.rg.cvdatabase.data.viewmodel.CVViewModel
import kotlinx.android.synthetic.main.cv_preview_layout.view.*

class ListAdapter(mCVViewModel: CVViewModel,context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private val listAdapterCVViewModel = mCVViewModel
    private val currentContext  = context
    private var cvList = emptyList<CV>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cv_preview_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = cvList[position]

        holder.itemView.nameText.text = currentItem.name
        holder.itemView.cvPreviewItem.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.cvPreviewItem.imageView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToPreviewFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.cvPreviewItem.binIcon.setOnClickListener {
            deleteCV(currentItem)
        }

    }


    private fun deleteCV(cvToDelete : CV) {
        val builder = AlertDialog.Builder(currentContext)
        builder.setPositiveButton("Yes") { _, _ ->
            listAdapterCVViewModel.deleteCV(cvToDelete)
            Toast.makeText(
                currentContext,
                "CV : "
                        + cvToDelete.name +
                        " succesfully deleted.",
                Toast.LENGTH_LONG
            )
                .show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${cvToDelete.name}?")
        builder.setMessage("Are you sure you want to delete ${cvToDelete.name} from the CVs?")
        builder.create().show()
    }

    override fun getItemCount(): Int = cvList.size

    fun setList(cvs: List<CV>) {
        this.cvList = cvs
        notifyDataSetChanged()
    }

}