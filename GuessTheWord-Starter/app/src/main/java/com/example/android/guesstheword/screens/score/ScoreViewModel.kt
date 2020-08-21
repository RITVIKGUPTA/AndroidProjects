package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(Score : Int) : ViewModel(){
    var score = Score
    init{

        Log.i("ScoreViewModel", "score view model created")
    }


}