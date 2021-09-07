package com.example.theaterkotlinspring.utils

import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.CopyOption
import java.nio.file.Files
import java.nio.file.Files.exists
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class FileUtilApp {
    companion object {

        fun saveFile(
            uploadDirectory: String,
            fileName: String,
            multipartFile: MultipartFile,
            releaseExisting: Boolean =false
        ) {
            val uploadPath = Paths.get(uploadDirectory)
            if (!exists(uploadPath)) {
                Files.createDirectories(uploadPath)
            }
            val filePath = uploadPath.resolve(fileName)
            try {
                if (releaseExisting) Files.copy(multipartFile.inputStream, filePath,StandardCopyOption.REPLACE_EXISTING) else Files.copy(multipartFile.inputStream, filePath)
            } catch (e: java.nio.file.FileAlreadyExistsException) {

            }

        }
        fun saveFileAndReturnPathToString( rootDirectory:String,
                                           uploadDirectory: String,
                                           fileName: String,
                                           multipartFile: MultipartFile): String {
            try{
                saveFile("${rootDirectory}${uploadDirectory}",fileName,multipartFile)
            }catch(e:java.io.IOException){
              return  "can not upload file to server"
            }
            catch(e:SecurityException ){
              return  "dont have permission upload file to server"
            }
            return Paths.get(uploadDirectory,fileName).toString()

        }

    }

}