package com.eurogames.domain.models.user

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class User(
    // Identificador
    @Contextual var id: Uuid = uuid4(),

    // Datos
    var fullName: String,
    var username: String,
    var password: String,
    var email: String,
    var avatar: String? = null
)