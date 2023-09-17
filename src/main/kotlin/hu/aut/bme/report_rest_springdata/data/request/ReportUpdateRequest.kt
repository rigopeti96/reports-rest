package hu.aut.bme.report_rest_springdata.data.request

data class ReportUpdateRequest(
    val id: Int?,
    val timeInterval: Int
)