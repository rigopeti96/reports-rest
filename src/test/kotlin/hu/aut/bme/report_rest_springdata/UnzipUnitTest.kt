package hu.aut.bme.report_rest_springdata

import com.mongodb.assertions.Assertions.assertTrue
import hu.aut.bme.report_rest_springdata.gtfshandler.Unzipper
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.io.IOException

@SpringBootTest
class UnzipUnitTest {

    /*@Test
    fun testUnzipperWithCorrectFileName() {
        Unzipper.unzip("test.zip")

        val destDir = File("src/main/resources/unzipStage")
        val fileList = destDir.listFiles()
        assert(fileList.size == 2)
        assert(fileList[0].name == "1.txt")
        assert(fileList[1].name == "2.txt")

        val deleteFirst = File("src/main/resources/unzipStage/1.txt")
        val deleteSecond = File("src/main/resources/unzipStage/2.txt")
        deleteFirst.delete()
        deleteSecond.delete()

        val fileListAfterDelete = destDir.listFiles()
        assert(fileListAfterDelete.isEmpty())
    }

    @Test
    fun testUnzipperWithWrongFileNameExpectException(){
        val exception: Exception = assertThrows(IOException::class.java) { Unzipper.unzip("testWrong.zip") }

        val expectedMessage = "does not exists"
        val actualMessage = exception.message

        assertTrue(actualMessage!!.contains(expectedMessage))
    }*/
}