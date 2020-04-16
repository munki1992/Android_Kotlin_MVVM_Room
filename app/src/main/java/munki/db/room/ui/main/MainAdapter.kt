package munki.db.room.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import munki.db.room.R
import munki.db.room.data.dao.Word
import munki.db.room.databinding.RecyclerviewItemBinding
import munki.db.room.ui.base.BaseViewHolder

/**
 * MainAdapter
 * @author 나비이쁜이
 * @since 2020.04.16
 */
class MainAdapter internal constructor (private val mContext: Context) : RecyclerView.Adapter<BaseViewHolder<Word?>>() {

    /**
     * Word List
     */
    private var mWords: List<Word?>? = null

    /**
     * onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false))
    }

    /**
     * onBindViewHolder
     */
    override fun onBindViewHolder(holder: BaseViewHolder<Word?>, position: Int) {
        holder.bind(mWords!![holder.adapterPosition], position)
    }

    /**
     * Set Word
     */
    fun setWords(words: List<Word?>?) {
        mWords = words
        notifyDataSetChanged()
    }

    /**
     * Item Count
     */
    override fun getItemCount(): Int {
        return if (mWords != null) mWords!!.size else 0
    }

    /**
     * ViewHolder
     */
    inner class ViewHolder constructor(view: View) : BaseViewHolder<Word?>(view) {

        /**
         * databinding
         */
        private val mItemBinding: RecyclerviewItemBinding? = DataBindingUtil.bind(view)

        /**
         * Bind
         */
        override fun bind(itemVo: Word?, position: Int?) {
            mItemBinding!!.textView.text = itemVo?.searchWord ?: "null"
        }
    }
}