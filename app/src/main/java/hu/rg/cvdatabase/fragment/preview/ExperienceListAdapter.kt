package hu.rg.cvdatabase.fragment.preview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.entities.Job
import kotlinx.android.synthetic.main.school_preview_layout.view.*


class ExperienceListAdapter : RecyclerView.Adapter<ExperienceListAdapter.ViewHolder>() {

    private var jobList = emptyList<Job>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.school_preview_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = jobList[position]

        holder.itemView.schoolNamePreview.text = currentItem.title
        holder.itemView.durationText.text = currentItem.from+" - "+currentItem.to
        holder.itemView.subjectText.text = currentItem.company

    }

    override fun getItemCount(): Int = jobList.size

    fun setList(jobs: List<Job>) {
        this.jobList = jobs
        notifyDataSetChanged()
    }

}
