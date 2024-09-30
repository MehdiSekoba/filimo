package com.mehdisekoba.filimo.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.imageview.ShapeableImageView
import com.mehdisekoba.filimo.R
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail.Data.Attributes.ActorCrewData.Profile
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail.Data.Attributes.AparatTrailer
import com.mehdisekoba.filimo.data.model.detail.ResponseDetail.Data.Attributes.OtherCrewData
import com.mehdisekoba.filimo.data.model.home.ResponseHome.Included.Attributes
import com.mehdisekoba.filimo.databinding.FragmentDetailsBinding
import com.mehdisekoba.filimo.ui.detail.adapter.ActorAdapter
import com.mehdisekoba.filimo.ui.detail.adapter.ProductionAdapter
import com.mehdisekoba.filimo.ui.detail.adapter.SimilarAdapter
import com.mehdisekoba.filimo.ui.home.HomeFragmentDirections
import com.mehdisekoba.filimo.utils.DURATION_TEXT
import com.mehdisekoba.filimo.utils.base.BaseFragment
import com.mehdisekoba.filimo.utils.extensions.loadImage
import com.mehdisekoba.filimo.utils.extensions.setupRecyclerview
import com.mehdisekoba.filimo.utils.extensions.toggleVisibility
import com.mehdisekoba.filimo.utils.network.NetworkRequest
import com.mehdisekoba.filimo.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kohii.v1.core.Playback
import kohii.v1.exoplayer.Kohii
import javax.inject.Inject

@AndroidEntryPoint
@Suppress("DEPRECATION")
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    @Inject
    lateinit var similarAdapter: SimilarAdapter

    @Inject
    lateinit var actorAdapter: ActorAdapter

    @Inject
    lateinit var productionAdapter: ProductionAdapter

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailsFragmentArgs>()
    private var moviesId = 0
    private lateinit var kohii: Kohii

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupKohii()
        if (isNetworkAvailable) {
            loadMovieArgs()
            if (moviesId > 0) {
                viewModel.apply {
                    callDetailApi(moviesId)
                    callSimilarDetailApi(moviesId)
                }
                observeMovieDetails()
                observeSimilarMovies()
            }
        }

    }

    private fun setupKohii() {

        kohii = Kohii[this@DetailsFragment]
        kohii.register(this@DetailsFragment).addBucket(binding.movieScroll)

    }

    @SuppressLint("SetTextI18n")
    private fun loadMovieArgs() {
        binding.apply {
            clearMovieDetails()
            args.data?.let { data ->
                moviesId = args.id
                data.pic?.let { cover -> loadImages(cover, data.coverMovie.toString()) }
                movieTitle.text = data.movieTitle
                val descriptionParts = mutableListOf<String>()
                val ageRangeText = data.ageRange?.split("-")?.get(0)
                if (!ageRangeText.isNullOrEmpty()) {
                    descriptionParts.add(
                        "${getString(R.string.range_see)} $ageRangeText ${
                            getString(
                                R.string.txt_year
                            )
                        }"
                    )
                }
                descriptionParts.add(getString(R.string.txt_product))
                val country = data.countries?.firstOrNull()?.country
                if (!country.isNullOrEmpty()) {
                    descriptionParts.add(country)
                }
                val durationText =
                    (data.duration as? Map<*, *>)?.get(DURATION_TEXT) as? String ?: ""
                if (durationText.isNotEmpty()) {
                    descriptionParts.add(durationText)
                }
                val qualityText = if (data.hd == true) getString(R.string.quality_video) else ""
                if (qualityText.isNotEmpty()) {
                    descriptionParts.add(qualityText)
                }
                movieDesTitle.text = descriptionParts.joinToString(" - ")
                loadMovieDescription(data.descr)
            }
        }
    }

    private fun clearMovieDetails() {
        binding.apply {
            movieTitle.text = ""
            movieDesTitle.text = ""
        }
    }

    private fun loadImages(picData: Attributes.Pic, coverMovie: String) {
        binding.apply {
            imgPoster.loadImage(picData.movieImgM)
            movieCoverImg.loadImage(coverMovie)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadMovieDescription(description: String?) {
        binding.apply {
            movieDesc.visibility =
                if (!description.isNullOrEmpty()) View.VISIBLE else View.INVISIBLE
            movieDesc.text = "${getString(R.string.story_movies)}\n${description}"
        }
    }

    private fun observeMovieDetails() {
        viewModel.detailData.observe(viewLifecycleOwner) { response ->
            binding.shimmerView.toggleVisibility(response is NetworkRequest.Loading)
            if (response is NetworkRequest.Success) {
                response.data?.data?.attributes?.let { attributes ->
                    loadMoviesTrailerData(attributes.aparatTrailer)
                    loadMoviesActorData(attributes.actorCrewData?.profile.orEmpty())
                    initRecyclerProduction(attributes.otherCrewData.orEmpty())
                }
            }
        }
    }

    private fun observeSimilarMovies() {
        viewModel.similarDetailData.observe(viewLifecycleOwner) { response ->
            binding.shimmerView.toggleVisibility(response is NetworkRequest.Loading)
            if (response is NetworkRequest.Success && response.data?.included?.isNotEmpty() == true) {
                initSimilarRecyclers(response.data.included.mapNotNull { it.attributes })
            }
        }
    }

    private fun initSimilarRecyclers(similarMovies: List<Attributes>) {
        similarAdapter.setData(similarMovies)
        binding.RcSimilar.setupRecyclerview(
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
            similarAdapter
        )
        similarAdapter.setOnItemClickListener { id, attributes ->
            val direction = HomeFragmentDirections.actionToDetail(id).setData(attributes)
            findNavController().navigate(
                direction,
                NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build())


        }
    }

    private fun loadMoviesTrailerData(aparatTrailer: AparatTrailer?) {
        aparatTrailer?.let {
            val trailerInflate = binding.trailerLay.inflate()
            val player = trailerInflate.findViewById<PlayerView>(R.id.player)
            val thumbnail = trailerInflate.findViewById<ShapeableImageView>(R.id.img_thumbnail)
            val preview = trailerInflate.findViewById<TextView>(R.id.txt_preview)
            val volumeMute = trailerInflate.findViewById<ImageView>(R.id.volume_mute)

            if (it.fileLink.isNullOrEmpty()) {
                player.visibility = View.GONE
                preview.visibility = View.GONE
            } else {
                it.bigPoster?.let { it1 -> thumbnail.loadImage(it1) }
                setupPlayer(player, it.fileLink, thumbnail, volumeMute)
                player.visibility = View.VISIBLE
                preview.visibility = View.VISIBLE
            }
        }
    }

    private fun loadMoviesActorData(actorProfiles: List<Profile>) {
        val actorInflate = binding.ActorLay.inflate()
        val list = actorInflate.findViewById<RecyclerView>(R.id.actor_list)
        val actor = actorInflate.findViewById<TextView>(R.id.actor_txt)
        toggleVisibility(actorProfiles.isEmpty(), actor, list)
        actorAdapter.setData(actorProfiles)
        list.setupRecyclerview(
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
            actorAdapter
        )
    }

    private fun initRecyclerProduction(otherCrewDataList: List<OtherCrewData>) {
        val agentInflate = binding.AgentsLay.inflate()
        val list = agentInflate.findViewById<RecyclerView>(R.id.agents_list)
        val agents = agentInflate.findViewById<TextView>(R.id.agents_txt)
        toggleVisibility(otherCrewDataList.isEmpty(), agents, list)
        productionAdapter.setData(otherCrewDataList)
        list.setupRecyclerview(
            StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL),
            productionAdapter
        )
    }
    private fun toggleVisibility(isEmpty: Boolean, vararg views: View) {
        views.forEach { view ->
            view.visibility = if (isEmpty) View.GONE else View.VISIBLE
        }
    }


    private fun setupPlayer(
        player: PlayerView,
        fileLink: String,
        thumbnail: ShapeableImageView,
        volumeMute: ImageView
    ) {
        player.apply {
            useController = false
            background = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 22f
                setColor(Color.BLACK)
            }
        }
        volumeMute.setOnClickListener {
            player.player?.let { exoPlayer ->
                val newVolume = if (exoPlayer.volume > 0f) 0f else 1f
                exoPlayer.volume = newVolume
                volumeMute.setImageResource(if (newVolume > 0f) R.drawable.volume_mute else R.drawable.volume_slash)
            }
        }

        kohii.setUp(fileLink) {
            artworkHintListener = object : Playback.ArtworkHintListener {
                override fun onArtworkHint(
                    playback: Playback,
                    shouldShow: Boolean,
                    position: Long,
                    state: Int
                ) {
                    thumbnail.isVisible = shouldShow
                }
            }
            preload = true
            threshold = 0.90f
            repeatMode = Player.REPEAT_MODE_ONE
            controller = object : Playback.Controller {
                override fun kohiiCanPause(): Boolean = true
                override fun kohiiCanStart(): Boolean = true
            }
        }.bind(player)
    }

}



