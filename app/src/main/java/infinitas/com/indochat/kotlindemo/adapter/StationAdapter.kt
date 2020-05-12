package infinitas.com.indochat.kotlindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import infinitas.com.indochat.kotlindemo.R
import infinitas.com.indochat.kotlindemo.data.StationArray

class StationAdapter(val stationList: StationArray, val context: Context): RecyclerView.Adapter<StationAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return stationList.datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item_station, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvStationId?.text = stationList.datas.get(position)?.id.toString()
        holder?.tvStationName?.text = stationList.datas.get(position)?.name
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val tvStationId = view.findViewById<TextView>(R.id.tv_item_id)
        val tvStationName = view.findViewById<TextView>(R.id.tv_item_name)
    }
}