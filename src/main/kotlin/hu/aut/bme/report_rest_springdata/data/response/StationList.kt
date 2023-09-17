package hu.aut.bme.report_rest_springdata.data.response

import hu.aut.bme.report_rest_springdata.collections.Station

data class StationList(
    val stationList: ArrayList<Station>
)
