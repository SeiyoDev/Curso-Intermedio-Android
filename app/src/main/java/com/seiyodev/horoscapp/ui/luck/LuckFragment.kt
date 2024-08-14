package com.seiyodev.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.seiyodev.horoscapp.R
import com.seiyodev.horoscapp.databinding.FragmentLuckBinding
import com.seiyodev.horoscapp.ui.core.listeners.OnSwipeTouchListener
import com.seiyodev.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {



    private var _binding: FragmentLuckBinding? = null

    // Esta propiedad es solo para lectura del de arriba. Se crea para que se pueda acceder al binding sin que se pueda modificar
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initUI() {
        preparePredictions()
        initListeners()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {

        // Esta parte nos permite detectar gestos de swipe e iniciamos la animacion de las cartas
        binding.ivRoulette.setOnTouchListener(object : OnSwipeTouchListener(requireContext()){
            override fun onSwipeRight() {
                spinRoulette()
            }

            override fun onSwipeLeft() {
                spinRoulette()
            }
        })
    }

    private fun preparePredictions() {
        val luck = randomCardProvider.getLucky()
        luck?.let {
            val currentPrediction = getString(it.text)
            binding.tvLucky.text = currentPrediction
            binding.ivLuckyCard.setImageResource(it.image)
            binding.tvShare.setOnClickListener{shareResult(currentPrediction)}
        }
    }

    private fun shareResult(prediction:String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun spinRoulette() {
        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard(){
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.ivReverse.isVisible = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })

        binding.ivReverse.startAnimation(slideUpAnimation)

    }

    private fun growCard(){
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)
        growAnimation.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivReverse.isVisible = false
                showPredictionView()
            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })

        binding.ivReverse.startAnimation(growAnimation)
    }

    private fun showPredictionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f) // Vamos cambiando la opacidad hasta invisible
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f) // Vamos cambiando la opacidad hata que sea visible completamente
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })

        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }


}