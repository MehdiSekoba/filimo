package com.mehdisekoba.filimo.ui.explore.adapter


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.Player
import com.mehdisekoba.filimo.R
import com.mehdisekoba.filimo.data.model.explore.ResponseExplore.Data
import com.mehdisekoba.filimo.databinding.ItemExploreBinding
import com.mehdisekoba.filimo.utils.base.BaseDiffUtils
import com.mehdisekoba.filimo.utils.extensions.loadImage
import dagger.hilt.android.qualifiers.ApplicationContext
import kohii.v1.core.Playback
import kohii.v1.exoplayer.Kohii
import javax.inject.Inject

class AdapterExplore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val kohii: Kohii
) :
    RecyclerView.Adapter<AdapterExplore.ViewHolder>() {

    private var items = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])


    override fun getItemCount() = items.size


    inner class ViewHolder(private val binding: ItemExploreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.apply {
                imgLogo.loadImage(item.logo!!)
                imgThumbnail.loadImage(item.pic?.imgM!!)
                txtInfo.isVisible = item.badge?.newEpisode == true

                val infoText = item.badge?.info?.firstOrNull()?.text ?: ""
                txtInfo.text = infoText

                val title = item.title ?: ""
                val partString = context.getString(R.string.part)
                val regex = Regex("""$partString\s*(\d+)\s*(.*)""")

                val matchResult = regex.find(title)
                txtTitle.text = matchResult?.let {
                    val part2 = it.groupValues[1]
                    val part3 = it.groupValues[2]
                    "$part3 | $partString $part2"
                } ?: title

                //background
                playerView.useController = false
                val roundedBackground = GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    cornerRadius = 22f
                    setColor(Color.BLACK)
                }
                playerView.background = roundedBackground

                //video
                volumeMute.setOnClickListener {
                    playerView.player?.let { player ->
                        player.volume = if (player.volume > 0f) 0f else 1f
                        volumeMute.setImageResource(
                            if (player.volume > 0f) R.drawable.volume_mute else R.drawable.volume_slash
                        )
                    }
                }
                //close
                imgClose.setOnClickListener {
                    playerView.player?.release()
                    playerView.removeViewAt(bindingAdapterPosition)
                }
                item.trailer?.url?.let { url ->
                    kohii.setUp(url) {
                        preload = true
                        threshold = 0.90f
                        repeatMode = Player.REPEAT_MODE_ONE
                        controller = object : Playback.Controller {
                            override fun kohiiCanPause(): Boolean = true
                            override fun kohiiCanStart(): Boolean = true
                        }
                        //thumbnail
                        artworkHintListener = object : Playback.ArtworkHintListener {
                            override fun onArtworkHint(
                                playback: Playback,
                                shouldShow: Boolean,
                                position: Long,
                                state: Int
                            ) {
                                imgThumbnail.isVisible = shouldShow
                            }

                        }
                    }.bind(playerView)
                }

            }
        }


    }


    fun setData(data: List<Data>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}
