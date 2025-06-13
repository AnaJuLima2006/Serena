package com.example.serena.ui.theme.viewModel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serena.ui.theme.model.SoundItem
import com.example.serena.ui.theme.model.getSoundsForEnvironment
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class PlayerViewModel : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null
    private var soundList: List<SoundItem> = emptyList() // Usa a classe correta
    private var progressJob: Job? = null

    // Estados que a UI vai observar
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _currentSound = MutableStateFlow<SoundItem?>(null) // Usa a classe correta
    val currentSound = _currentSound.asStateFlow()

    private val _currentPosition = MutableStateFlow(0)
    val currentPosition = _currentPosition.asStateFlow()

    private val _totalDuration = MutableStateFlow(0)
    val totalDuration = _totalDuration.asStateFlow()

    private val _currentSoundIndex = MutableStateFlow(0)
    val currentSoundIndex = _currentSoundIndex.asStateFlow() // Exposto para a UI

    val isFirstSong: Boolean
        get() = _currentSoundIndex.value == 0

    val isLastSong: Boolean
        get() = _currentSoundIndex.value == soundList.lastIndex


    fun loadSounds(envName: String, context: Context) {
        soundList = getSoundsForEnvironment(envName)
        val soundToPlay = soundList.getOrNull(0)
        _currentSoundIndex.value = 0
        _currentSound.value = soundToPlay
        // Começa a tocar a primeira música automaticamente
        if (soundToPlay != null) {
            playSound(soundToPlay, context)
        }
    }

    private fun playSound(soundData: SoundItem, context: Context) {
        viewModelScope.launch {
            try {
                releasePlayer() // Limpa o player anterior

                mediaPlayer = MediaPlayer().apply {
                    val afd = context.resources.openRawResourceFd(soundData.soundRes)
                    setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                    afd.close()

                    setOnPreparedListener { mp ->
                        _totalDuration.value = mp.duration
                        mp.start()
                        _isPlaying.value = true
                        startProgressUpdates()
                    }

                    setOnCompletionListener {
                        nextSound(context) // Pula para a próxima ao terminar
                    }
                    prepareAsync()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _isPlaying.value = false
            }
        }
    }

    fun togglePlayPause(context: Context) {
        if (_isPlaying.value) {
            pauseSound()
        } else {
            if (mediaPlayer != null && !mediaPlayer!!.isPlaying) {
                mediaPlayer?.start()
                _isPlaying.value = true
                startProgressUpdates()
            } else {
                _currentSound.value?.let { playSound(it, context) }
            }
        }
    }

    private fun pauseSound() {
        mediaPlayer?.pause()
        _isPlaying.value = false
        stopProgressUpdates()
    }

    fun nextSound(context: Context) {
        if (!isLastSong) {
            val nextIndex = _currentSoundIndex.value + 1
            _currentSoundIndex.value = nextIndex
            soundList.getOrNull(nextIndex)?.let {
                _currentSound.value = it
                playSound(it, context)
            }
        }
    }

    fun previousSound(context: Context) {
        if (!isFirstSong) {
            val prevIndex = _currentSoundIndex.value - 1
            _currentSoundIndex.value = prevIndex
            soundList.getOrNull(prevIndex)?.let {
                _currentSound.value = it
                playSound(it, context)
            }
        }
    }

    fun seekTo(position: Int) {
        _currentPosition.value = position
        mediaPlayer?.seekTo(position)
    }

    private fun startProgressUpdates() {
        progressJob?.cancel()
        progressJob = viewModelScope.launch {
            while (isActive && _isPlaying.value) {
                _currentPosition.value = mediaPlayer?.currentPosition ?: 0
                delay(1000)
            }
        }
    }

    private fun stopProgressUpdates() {
        progressJob?.cancel()
    }

    private fun releasePlayer() {
        stopProgressUpdates()
        mediaPlayer?.release()
        mediaPlayer = null
        _isPlaying.value = false
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }

    fun formatTime(millis: Int): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis.toLong())
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis.toLong()) - TimeUnit.MINUTES.toSeconds(minutes)
        return String.format("%02d:%02d", minutes, seconds)
    }
}