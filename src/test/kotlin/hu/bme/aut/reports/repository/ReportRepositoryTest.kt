package hu.bme.aut.reports.repository

import hu.bme.aut.reports.data.ReportRepository
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class ReportRepositoryTest {
    @Autowired
    private var reportRepository: ReportRepository = Any() as ReportRepository
}