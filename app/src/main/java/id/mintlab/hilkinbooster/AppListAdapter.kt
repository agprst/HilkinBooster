package id.mintlab.hilkinbooster

import java.util.ArrayList

import android.content.Context
import android.content.pm.ResolveInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AppListAdapter(private val context: Context) : BaseAdapter() {

    private val objects = ArrayList<ResolveInfo>()
    private val layoutInflater: LayoutInflater

    init {
        this.layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return objects.size
    }

    override fun getItem(position: Int): ResolveInfo {
        return objects[position]
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


    private fun initializeViews(`object`: ResolveInfo, holder: ViewHolder) {
        //TODO implement
    }

    protected inner class ViewHolder(view: View) {
        private val ivAppIcon: ImageView
        private val tvAppLabel: TextView

        init {
            ivAppIcon = view.findViewById<View>(R.id.ivAppIcon) as ImageView
            tvAppLabel = view.findViewById<View>(R.id.tvAppLabel) as TextView
        }
    }
}
