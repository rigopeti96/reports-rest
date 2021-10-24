package hu.bme.aut.reports.repository

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue


@RunWith(SpringRunner::class)
@SpringBootTest
class ReportRepositoryTest {
    @Autowired
    private var reportRepository: ReportRepository = Any() as ReportRepository
}