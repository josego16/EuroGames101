package com.eurogames.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PaginatedResponseDto(
    val info: PaginationInfoDto,
    val items: List<CountryResponseDto>
)

@Serializable
data class PaginationInfoDto(
    val pages:Int,
    val next:String?,
    val prev:String?
)