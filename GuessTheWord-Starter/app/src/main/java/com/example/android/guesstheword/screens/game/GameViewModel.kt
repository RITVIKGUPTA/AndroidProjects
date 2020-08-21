package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.MainActivity

class GameViewModel : ViewModel() {
    //private var bun : Bundle? = null
    //private var acti : MainActivity = MainActivity()
    //var word = ""

    private val _word = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private var _gamefinished = MutableLiveData<Boolean>()
    private var _currenttime = MutableLiveData<Long>()
    val score : LiveData<Int>
        get() = _score
    val word: LiveData<String>
        get() = _word
    val gamefinished : LiveData<Boolean>
        get() = _gamefinished
    val currenttime : LiveData<Long>
        get() = _currenttime
    var Timer : CountDownTimer
    val currenttimestring = Transformations.map(currenttime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    // The current score
    //var score = 0

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

  init{
      Log.i("GameViewModel", "GameViewModel created!")
      Timer = object : CountDownTimer(MAX_TIME, COUNTDOWN_TIME){

          override fun onTick(millisUntilFinished: Long) {
              _currenttime.value = millisUntilFinished/COUNTDOWN_TIME
          }

          override fun onFinish() {
              _currenttime.value = DONE
              onGameFinish()
          }
      }

      Timer.start()

          _word.value  =""
          _score.value =0
          //acti.onCreate(bun)
          resetList()
          nextWord()
  }

    override fun onCleared() {
        super.onCleared()
      Log.i("GameViewModel", "game view model is cleared")
    }

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = _score.value?.plus(1)
        nextWord()
    }

    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            //Select and remove a word from the list
            onGameFinish()
        }
        else{
            _word.value = wordList.removeAt(0)
        }
    }

    fun onGameFinish(){
        _gamefinished.value = true
    }

    fun onGameFinishComplete(){
        _gamefinished.value = false
    }




    /** Methods for updating the UI **/

    companion object{
     private val COUNTDOWN_TIME: Long  = 1000L
        private val DONE : Long = 0L
        private val MAX_TIME :Long = 60000L
    }





}

