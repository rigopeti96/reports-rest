package hu.aut.bme.report_rest_springdata.data.response

import hu.aut.bme.report_rest_springdata.collections.Stops

data class StationList(
    val stationList: ArrayList<Stops>
)
