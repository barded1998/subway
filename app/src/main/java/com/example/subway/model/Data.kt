package com.example.subway.model

import com.example.subway.data.StationData.timeArray1
import com.example.subway.data.StationData2.timeArray2
import com.example.subway.data.StationData3.expenseArray1
import com.example.subway.data.StationData4.expenseArray2
import com.example.subway.data.StationData5.distanceArray1
import com.example.subway.data.StationData6.distanceArray2

var timeArray = timeArray1.plus(timeArray2)
var expenseArray = expenseArray1.plus(expenseArray2)
var distanceArray = distanceArray1.plus(distanceArray2)

var array = arrayOf(
    intArrayOf(1, 2),
    intArrayOf(1),
    intArrayOf(1),
    intArrayOf(1, 4),
    intArrayOf(1),
    intArrayOf(1),
    intArrayOf(1, 3),
    intArrayOf(1),
    intArrayOf(1, 5),
    intArrayOf(1),
    intArrayOf(1),
    intArrayOf(1, 9),
    intArrayOf(1, 8),
    intArrayOf(1),
    intArrayOf(1, 4),
    intArrayOf(1, 6),
    intArrayOf(1),
    intArrayOf(1),
    intArrayOf(1, 9),
    intArrayOf(1),
    intArrayOf(1, 6),
    intArrayOf(1, 5),
    intArrayOf(1, 3),
    intArrayOf(2),
    intArrayOf(2, 7),
    intArrayOf(2),
    intArrayOf(2),
    intArrayOf(2),
    intArrayOf(2),
    intArrayOf(2, 3),
    intArrayOf(2),
    intArrayOf(2, 5),
    intArrayOf(2),
    intArrayOf(2, 9),
    intArrayOf(2),
    intArrayOf(2),
    intArrayOf(2, 8),
    intArrayOf(2),
    intArrayOf(2, 4),
    intArrayOf(2),
    intArrayOf(3),
    intArrayOf(3),
    intArrayOf(3, 7),
    intArrayOf(3),
    intArrayOf(3),
    intArrayOf(3),
    intArrayOf(3, 4),
    intArrayOf(3),
    intArrayOf(4),
    intArrayOf(4),
    intArrayOf(4, 5),
    intArrayOf(4),
    intArrayOf(4),
    intArrayOf(4, 9),
    intArrayOf(4),
    intArrayOf(4),
    intArrayOf(4, 8),
    intArrayOf(4),
    intArrayOf(4),
    intArrayOf(4, 6),
    intArrayOf(4),
    intArrayOf(4),
    intArrayOf(4),
    intArrayOf(4, 7),
    intArrayOf(4, 6),
    intArrayOf(5),
    intArrayOf(5),
    intArrayOf(5, 7),
    intArrayOf(5),
    intArrayOf(5),
    intArrayOf(5),
    intArrayOf(5),
    intArrayOf(6, 7),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6, 9),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6, 8),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6, 7),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6, 8),
    intArrayOf(6),
    intArrayOf(6),
    intArrayOf(6, 9),
    intArrayOf(6),
    intArrayOf(7),
    intArrayOf(7, 9),
    intArrayOf(7),
    intArrayOf(7),
    intArrayOf(7, 8),
    intArrayOf(7),
    intArrayOf(7),
    intArrayOf(8),
    intArrayOf(8),
    intArrayOf(8),
    intArrayOf(8),
    intArrayOf(8),
    intArrayOf(8),
    intArrayOf(9),
    intArrayOf(9),
    intArrayOf(9),
    intArrayOf(9)
)

fun getStationId(station: String?): Int {
    return when (station) {
        "101" -> 0
        "102" -> 1
        "103" -> 2
        "104" -> 3
        "105" -> 4
        "106" -> 5
        "107" -> 6
        "108" -> 7
        "109" -> 8
        "110" -> 9
        "111" -> 10
        "112" -> 11
        "113" -> 12
        "114" -> 13
        "115" -> 14
        "116" -> 15
        "117" -> 16
        "118" -> 17
        "119" -> 18
        "120" -> 19
        "121" -> 20
        "122" -> 21
        "123" -> 22
        "201" -> 23
        "202" -> 24
        "203" -> 25
        "204" -> 26
        "205" -> 27
        "206" -> 28
        "207" -> 29
        "208" -> 30
        "209" -> 31
        "210" -> 32
        "211" -> 33
        "212" -> 34
        "213" -> 35
        "214" -> 36
        "215" -> 37
        "216" -> 38
        "217" -> 39
        "301" -> 40
        "302" -> 41
        "303" -> 42
        "304" -> 43
        "305" -> 44
        "306" -> 45
        "307" -> 46
        "308" -> 47
        "401" -> 48
        "402" -> 49
        "403" -> 50
        "404" -> 51
        "405" -> 52
        "406" -> 53
        "407" -> 54
        "408" -> 55
        "409" -> 56
        "410" -> 57
        "411" -> 58
        "412" -> 59
        "413" -> 60
        "414" -> 61
        "415" -> 62
        "416" -> 63
        "417" -> 64
        "501" -> 65
        "502" -> 66
        "503" -> 67
        "504" -> 68
        "505" -> 69
        "506" -> 70
        "507" -> 71
        "601" -> 72
        "602" -> 73
        "603" -> 74
        "604" -> 75
        "605" -> 76
        "606" -> 77
        "607" -> 78
        "608" -> 79
        "609" -> 80
        "610" -> 81
        "611" -> 82
        "612" -> 83
        "613" -> 84
        "614" -> 85
        "615" -> 86
        "616" -> 87
        "617" -> 88
        "618" -> 89
        "619" -> 90
        "620" -> 91
        "621" -> 92
        "622" -> 93
        "701" -> 94
        "702" -> 95
        "703" -> 96
        "704" -> 97
        "705" -> 98
        "706" -> 99
        "707" -> 100
        "801" -> 101
        "802" -> 102
        "803" -> 103
        "804" -> 104
        "805" -> 105
        "806" -> 106
        "901" -> 107
        "902" -> 108
        "903" -> 109
        "904" -> 110
        else -> 0
    }
}

fun getStation(station: Int): String? {
    return when (station) {
        0 -> "101"
        1 -> "102"
        2 -> "103"
        3 -> "104"
        4 -> "105"
        5 -> "106"
        6 -> "107"
        7 -> "108"
        8 -> "109"
        9 -> "110"
        10 -> "111"
        11 -> "112"
        12 -> "113"
        13 -> "114"
        14 -> "115"
        15 -> "116"
        16 -> "117"
        17 -> "118"
        18 -> "119"
        19 -> "120"
        20 -> "121"
        21 -> "122"
        22 -> "123"
        23 -> "201"
        24 -> "202"
        25 -> "203"
        26 -> "204"
        27 -> "205"
        28 -> "206"
        29 -> "207"
        30 -> "208"
        31 -> "209"
        32 -> "210"
        33 -> "211"
        34 -> "212"
        35 -> "213"
        36 -> "214"
        37 -> "215"
        38 -> "216"
        39 -> "217"
        40 -> "301"
        41 -> "302"
        42 -> "303"
        43 -> "304"
        44 -> "305"
        45 -> "306"
        46 -> "307"
        47 -> "308"
        48 -> "401"
        49 -> "402"
        50 -> "403"
        51 -> "404"
        52 -> "405"
        53 -> "406"
        54 -> "407"
        55 -> "408"
        56 -> "409"
        57 -> "410"
        58 -> "411"
        59 -> "412"
        60 -> "413"
        61 -> "414"
        62 -> "415"
        63 -> "416"
        64 -> "417"
        65 -> "501"
        66 -> "502"
        67 -> "503"
        68 -> "504"
        69 -> "505"
        70 -> "506"
        71 -> "507"
        72 -> "601"
        73 -> "602"
        74 -> "603"
        75 -> "604"
        76 -> "605"
        77 -> "606"
        78 -> "607"
        79 -> "608"
        80 -> "609"
        81 -> "610"
        82 -> "611"
        83 -> "612"
        84 -> "613"
        85 -> "614"
        86 -> "615"
        87 -> "616"
        88 -> "617"
        89 -> "618"
        90 -> "619"
        91 -> "620"
        92 -> "621"
        93 -> "622"
        94 -> "701"
        95 -> "702"
        96 -> "703"
        97 -> "704"
        98 -> "705"
        99 -> "706"
        100 -> "707"
        101 -> "801"
        102 -> "802"
        103 -> "803"
        104 -> "804"
        105 -> "805"
        106 -> "806"
        107 -> "901"
        108 -> "902"
        109 -> "903"
        110 -> "904"
        else -> "0"
    }
}

fun getPathAndLine(path: MutableList<String?>): ArrayList<Any> {
    var line = arrayListOf<String?>()
    var count = arrayListOf<Int?>()
    var curLine: Int? = null
    var line1: Int? = null
    var line2: Int? = null
    if (array[getStationId(path[0])].size == 1) {
        line1 = array[getStationId(path[0])][0]
    } else {
        line1 = array[getStationId(path[0])][0]
        line2 = array[getStationId(path[0])][1]
    }
    if (array[getStationId(path[1])].size == 1) {
        if (line1 == array[getStationId(path[1])][0]) {
            curLine = line1
        } else if (line2 == array[getStationId(path[1])][0]) {
            curLine = line2
        }
    } else {
        if (line1 == array[getStationId(path[1])][0]) {
            curLine = line1
        } else if (line2 == array[getStationId(path[1])][0]) {
            curLine = line2
        } else if (line1 == array[getStationId(path[1])][1]) {
            curLine = line1
        } else if (line2 == array[getStationId(path[1])][1]) {
            curLine = line2
        }
    }
    var prevLine: Int?
    var c = 0;
    if(path.size == 2) {
        return arrayListOf<Any>(arrayListOf(1), arrayListOf(curLine.toString()))
    }
    for (i in 1 until path.size - 1) {
        c++
        for (l in 0 until array[getStationId(path[i])].size) {
            prevLine = curLine
//            println("curLine : " + curLine.toString() + " prevLine : " + prevLine.toString())
            if (array[getStationId(path[i])].size == 1) {
                line1 = array[getStationId(path[i])][0]
            } else {
                line1 = array[getStationId(path[i])][0]
                line2 = array[getStationId(path[i])][1]
            }
            if (array[getStationId(path[i + 1])].size == 1) {
                if (line1 == array[getStationId(path[i + 1])][0]) {
                    curLine = line1
                } else if (line2 == array[getStationId(path[i + 1])][0]) {
                    curLine = line2
                }
            } else {
                if (line1 == array[getStationId(path[i + 1])][0]) {
                    curLine = line1
                } else if (line2 == array[getStationId(path[i + 1])][0]) {
                    curLine = line2
                } else if (line1 == array[getStationId(path[i + 1])][1]) {
                    curLine = line1
                } else if (line2 == array[getStationId(path[i + 1])][1]) {
                    curLine = line2
                }
            }
            if (prevLine != curLine) {
                /* println("not equal!!\n" + "curLine : " + curLine.toString() + " prevLine : " + prevLine.toString())*/
                if (i == path.size - 2) {
                    line.add(prevLine.toString())
                    count.add(c)
                    c = 0
                    break;
                }
                line.add(prevLine.toString())
                count.add(c)
                c = 0
            }
        }
        if (i == path.size - 2) {
            count.add(c + 1)
            c = 0
            line.add(curLine.toString())
        }
    }
    println(count.toString() +  line.toString())
    return arrayListOf<Any>(count, line)
}

fun combinePathAndLine(path: MutableList<String?>, count: MutableList<Int?>, line: MutableList<String?>): MutableList<MutableList<String?>> {
    var p = mutableListOf<MutableList<String?>>()
    var num = 0;
    var t = 0
    for (i in count) {
        var temp = arrayListOf<String?>()
        temp.add(line[t]!!)
        t++
        for (n in num..(num + i!!)) {
            temp.add(path[n]!!)
        }
        num += i
        p.add(temp)
    }
    return p
}

public fun makePath(path: MutableList<String?>): MutableList<MutableList<String?>> {
    var t = getPathAndLine(path)
    var count = t[0]
    var line = t[1]
    return combinePathAndLine(path, count as MutableList<Int?>, line as MutableList<String?>)
}

fun getShortestPathByTime(start: String?, end: String?): MutableList<String?> {
    val visited = Array<Boolean>(111){false}
    val distance = Array<Int>(111){10000}
    val startId = getStationId(start)
    val endId = getStationId(end)
    val routeArray = MutableList<MutableList<String?>>(111){ mutableListOf<String?>()}
    for (i in 0..110) {
        routeArray[i].add(start)
        if (timeArray[startId][i] != 10000) {
            distance[i] = timeArray[startId][i]
            routeArray[i].add(getStation(i))
        }
    }
    visited[startId] = true
    var curr = startId

    for (i in 0..110) {
        var min = 10000
        var minIdx = curr
        for (j in 0..110) {
            if (!visited[j]) {
                if (min > distance[j]) {
                    min = distance[j]
                    minIdx = j
                }
            }
        }
        curr = minIdx
        visited[curr] = true
        for (j in 0..110) {
            if (!visited[j]) {
                if (distance[curr] + timeArray[curr][j] < distance[j]) {
                    routeArray[j] = MutableList(routeArray[curr].size, {index: Int -> routeArray[curr][index].toString() })
                    routeArray[j].add(getStation(j));
                    distance[j] = distance[curr] + timeArray[curr][j]
                }
            }
        }
    }
    return routeArray[endId]
}


fun getExpense(path: MutableList<String?>): Int {
    var cost = 0
    for(i in 0 until path.size-1) {
        cost += expenseArray[getStationId(path[i])][getStationId(path[i+1])]
    }
    return cost
}

fun getShortestPathByExpense(start: String?, end: String?): MutableList<String?> {
    val visited = Array<Boolean>(111){false}
    val distance = Array<Int>(111){10000}
    val startId = getStationId(start)
    val endId = getStationId(end)
    val routeArray = MutableList<MutableList<String?>>(111){ mutableListOf<String?>()}
    for (i in 0..110) {
        routeArray[i].add(start)
        if (expenseArray[startId][i] != 10000) {
            distance[i] = timeArray[startId][i]
            routeArray[i].add(getStation(i))
        }
    }
    visited[startId] = true
    var curr = startId

    for (i in 0..110) {
        var min = 10000
        var minIdx = curr
        for (j in 0..110) {
            if (!visited[j]) {
                if (min > distance[j]) {
                    min = distance[j]
                    minIdx = j
                }
            }
        }
        curr = minIdx
        visited[curr] = true
        for (j in 0..110) {
            if (!visited[j]) {
                if (distance[curr] + expenseArray[curr][j] < distance[j]) {
                    routeArray[j] = MutableList(routeArray[curr].size, {index: Int -> routeArray[curr][index].toString() })
                    routeArray[j].add(getStation(j));
                    distance[j] = distance[curr] + expenseArray[curr][j]
                }
            }
        }
    }
    return routeArray[endId]
}

fun getTime(path: MutableList<String?>): Int {
    var cost = 0
    for(i in 0 until path.size-1) {
        if(path[i]?.toInt()!! < 10)
            continue
        cost += timeArray[getStationId(path[i])][getStationId(path[i+1])]
    }
    return cost
}

fun getShortestPathByDistance(start: String?, end: String?): MutableList<String?> {
    val visited = Array<Boolean>(111){false}
    val distance = Array<Int>(111){10000}
    val startId = getStationId(start)
    val endId = getStationId(end)
    val routeArray = MutableList<MutableList<String?>>(111){ mutableListOf<String?>()}
    for (i in 0..110) {
        routeArray[i].add(start)
        if (distanceArray[startId][i] != 10000) {
            distance[i] = distanceArray[startId][i]
            routeArray[i].add(getStation(i))
        }
    }
    visited[startId] = true
    var curr = startId

    for (i in 0..110) {
        var min = 10000
        var minIdx = curr
        for (j in 0..110) {
            if (!visited[j]) {
                if (min > distance[j]) {
                    min = distance[j]
                    minIdx = j
                }
            }
        }
        curr = minIdx
        visited[curr] = true
        for (j in 0..110) {
            if (!visited[j]) {
                if (distance[curr] + distanceArray[curr][j] < distance[j]) {
                    routeArray[j] = MutableList(routeArray[curr].size, {index: Int -> routeArray[curr][index].toString() })
                    routeArray[j].add(getStation(j));
                    distance[j] = distance[curr] + distanceArray[curr][j]
                }
            }
        }
    }
    return routeArray[endId]
}



//fun main() {
//    var path = getShortestPathByTime("101", "102")
//    println(path.toString())
//    println(makePath(path).toString())
//}

