package hu.rg.cvdatabase.fragment.preview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.recyclerview.widget.RecyclerView
import hu.rg.cvdatabase.R
import hu.rg.cvdatabase.data.entities.School
import kotlinx.android.synthetic.main.school_preview_layout.view.*


class EducationListAdapter : RecyclerView.Adapter<EducationListAdapter.ViewHolder>() {

        private var schoolList = emptyList<School>()

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.job_preview_layout,parent,false)
            )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentItem = schoolList[position]

            holder.itemView.schoolNamePreview.text = currentItem.name
            holder.itemView.durationText.text = currentItem.from+" - "+currentItem.to
            holder.itemView.subjectText.text = currentItem.subject

        }

        override fun getItemCount(): Int = schoolList.size

        fun setList(schools: List<School>) {
            this.schoolList = schools
            notifyDataSetChanged()
        }

}

