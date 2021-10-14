package com.example.subway.model

data class ContentDTO(
    var facilities: Array<String>,
    var imageUrl: String,
    var landmarks: Array<String>,
    var line: Array<String>,
    var nexStation: String,
    var prevStation: String,
    var station: String
) {}

