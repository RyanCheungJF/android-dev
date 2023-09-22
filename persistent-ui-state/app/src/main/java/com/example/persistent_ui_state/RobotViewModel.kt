package com.example.persistent_ui_state

import androidx.lifecycle.ViewModel

class RobotViewModel : ViewModel() {
    // backing property
    private var _turnCount: Int = -1

    // keeps it private within the class, and returns an immutable read only for public
    val turnCount: Int
        get() = _turnCount

    fun advanceCounter() {
        _turnCount++
        if (_turnCount >= 3) {
            _turnCount = 0
        }
    }
}
