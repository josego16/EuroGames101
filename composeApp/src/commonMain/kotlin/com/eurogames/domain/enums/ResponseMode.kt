package com.eurogames.domain.enums

import kotlinx.serialization.Serializable

@Serializable
enum class ResponseMode { MULTIPLE_CHOICE, SINGLE_CHOICE, FREE_TEXT }