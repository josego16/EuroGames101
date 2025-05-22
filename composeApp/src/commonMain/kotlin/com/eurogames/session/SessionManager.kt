package com.eurogames.session

import com.benasher44.uuid.Uuid
import com.eurogames.domain.model.User

object SessionManager {
    var userId: Uuid? = null
    var user: User? = null
}