package hu.aut.bme.report_rest_springdata.data.request

import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.NotBlank

data class ReportUpdateRequest(
    val id: @NotBlank UUID,
    val reportDateUntil: @NotBlank String
)