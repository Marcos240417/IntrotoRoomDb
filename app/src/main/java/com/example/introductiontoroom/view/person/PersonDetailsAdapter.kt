package com.example.introductiontoroom.view.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.introductiontoroom.data.model.PersonEntity
import com.example.introductiontoroom.databinding.SingleItemBinding

class PersonDetailsAdapter(private val listener: PersonDetailsClickListener) :
    ListAdapter<PersonEntity, PersonDetailsAdapter.PersonDetailsViewHolder>(DiffUtilCallback()) {

    inner class PersonDetailsViewHolder(private val binding: SingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.editBtn.setOnClickListener {
                listener.onEditPersonClick(getItem(adapterPosition))
            }
            binding.deleteBtn.setOnClickListener {
                listener.onDeletePersonClick(getItem(adapterPosition))
            }
        }

        fun bindi(personEntity: PersonEntity) {
            binding.personNameTv.text = personEntity.name
            binding.personAgeTv.text = personEntity.age.toString()
            binding.personCityTv.text = personEntity.city
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<PersonEntity>() {
        override fun areItemsTheSame(oldItem: PersonEntity, newItem: PersonEntity) = oldItem.pId == newItem.pId

        override fun areContentsTheSame(oldItem: PersonEntity, newItem: PersonEntity) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonDetailsViewHolder {
        return PersonDetailsViewHolder(
            SingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PersonDetailsViewHolder, position: Int) {
        holder.bindi(getItem(position))
    }

    interface PersonDetailsClickListener{
        fun onEditPersonClick(personEntity: PersonEntity)
        fun onDeletePersonClick(personEntity: PersonEntity)
    }
}