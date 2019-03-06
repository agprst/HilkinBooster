package id.mintlab.hilkinbooster

import java.util.ArrayList

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AppListAdapter(private val context: Context?, private val objects : List<ResolveInfo>?) : BaseAdapter() {

    private val layoutInflater: LayoutInflater

    init {
        this.layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return if (objects?.size != null) objects.size else 0
    }

    override fun getItem(position: Int): ResolveInfo? {
        return objects?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.recycler_app_list, null)
            convertView!!.tag = ViewHolder(convertView)
        }
        initializeViews(getItem(position), convertView.tag as ViewHolder)
        return convertView
    }
    private fun initializeViews(`object`: ResolveInfo?, holder: ViewHolder) {
        //TODO implement
        val icon = context?.packageManager?.getApplicationIcon(`object`?.activityInfo?.packageName)
        holder.ivAppIcon.setImageDrawable(icon)
        holder.tvAppLabel.text = `object`?.activityInfo?.packageName


    }

    protected inner class ViewHolder(view: View) {
        open val ivAppIcon: ImageView
        open val tvAppLabel: TextView



        init {
            ivAppIcon = view.findViewById<View>(R.id.ivAppIcon) as ImageView
            tvAppLabel = view.findViewById<View>(R.id.tvAppLabel) as TextView
        }
    }
}
