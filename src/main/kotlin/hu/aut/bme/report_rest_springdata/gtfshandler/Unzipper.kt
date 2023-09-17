package hu.aut.bme.report_rest_springdata.gtfshandler

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import kotlin.io.path.exists


object Unzipper {
    @Throws(IOException::class)
    fun unzip(zipFile: String) {
        val fileZip = "src/main/resources/zipContainer/$zipFile"
        val path = Paths.get(fileZip)
        if(!path.exists()){
            throw IOException("$fileZip does not exists")
        }
        val destDir = File("src/main/resources/unzipStage")
        val buffer = ByteArray(1024)
        val zis = ZipInputStream(FileInputStream(fileZip))
        var zipEntry = zis.nextEntry

        while (zipEntry != null) {
            val newFile = newFile(destDir, zipEntry)
            if (zipEntry.isDirectory) {
                if (!newFile.isDirectory && !newFile.mkdirs()) {
                    throw IOException("Failed to create directory $newFile")
                }
            } else {
                // fix for Windows-created archives
                val parent = newFile.parentFile
                if (!parent.isDirectory && !parent.mkdirs()) {
                    throw IOException("Failed to create directory $parent")
                }

                // write file content
                val fos = FileOutputStream(newFile)
                var len: Int
                while (zis.read(buffer).also { len = it } > 0) {
                    fos.write(buffer, 0, len)
                }
                fos.close()
            }
            zipEntry = zis.nextEntry
        }

        zis.closeEntry()
        zis.close()
    }

    @Throws(IOException::class)
    private fun newFile(destinationDir: File, zipEntry: ZipEntry): File {
        val destFile = File(destinationDir, zipEntry.name)
        val destDirPath = destinationDir.canonicalPath
        val destFilePath = destFile.canonicalPath
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw IOException("Entry is outside of the target dir: " + zipEntry.name)
        }
        return destFile
    }
}