package com.ahmety.mkolay.model.enum

enum class StatusTrack(val type: Long) {
    GoNext(1),
    ReGenerateQR(101),
    Crash(1001),
    Success(2)
}