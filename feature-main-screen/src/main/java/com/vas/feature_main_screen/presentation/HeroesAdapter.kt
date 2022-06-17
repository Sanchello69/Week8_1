package com.vas.feature_main_screen.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.vas.core.utils.Constants.BASE_URL
import com.vas.feature_main_screen.R
import com.vas.feature_main_screen.domain.model.Hero

class HeroesAdapter: RecyclerView.Adapter<ItemHeroesViewHolder>(){

    var data = listOf<Hero>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListener: OnHeroClickListener? = null

    interface OnHeroClickListener {
        fun onHeroClick(name: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHeroesViewHolder =
        ItemHeroesViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemHeroesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, onClickListener!!)
    }

    override fun getItemCount() = data.size


}

class ItemHeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val iconHero: ImageView = itemView.findViewById(R.id.iconImageView)
    private val nameHero: TextView = itemView.findViewById(R.id.nameTextView)

    fun bind(item: Hero, onHeroClickListener: HeroesAdapter.OnHeroClickListener) {

        nameHero.text = item.name

        iconHero.load(BASE_URL + item.urlIcon) {
            crossfade(true)
            placeholder(R.drawable.ic_dota)
            transformations(CircleCropTransformation())
        }

        itemView.setOnClickListener {
            onHeroClickListener.onHeroClick(item.name)
        }

    }

    companion object {
        fun from(parent: ViewGroup): ItemHeroesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.hero_item, parent, false)
            return ItemHeroesViewHolder(view)
        }
    }
}