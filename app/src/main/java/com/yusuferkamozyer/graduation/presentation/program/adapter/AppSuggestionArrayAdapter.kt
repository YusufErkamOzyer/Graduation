package com.yusuferkamozyer.graduation.presentation.program.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.yusuferkamozyer.graduation.R
import com.yusuferkamozyer.graduation.presentation.program.model.AppItem

class AppSuggestionArrayAdapter(context: Context, resource: Int, objects: ArrayList<AppItem>) :
    ArrayAdapter<AppItem>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.item_dropdown, parent, false)
        }
        val item = getItem(position)
        if (!item!!.isSelected) {
            val textView = convertView!!.findViewById<TextView>(R.id.adapterTextView)
            val imageView = convertView.findViewById<ImageView>(R.id.adapterImageView)
            textView.text = item?.appName
            imageView.setImageDrawable(item?.appIcon)
        } else {
            convertView = View(context)
        }
        return convertView
    }
}