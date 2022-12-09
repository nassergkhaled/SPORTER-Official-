package com.project.software_project.assistance;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static String saveFile(String fileName, MultipartFile multipartFile,String Type)
            throws IOException {
        Path uploadPath = Paths.get("Files-Upload");// generally
        Integer Flag=0;
        if(Type.startsWith("pp-")) {Flag = 1; /* Profile Picture*/}
            else if(Type.startsWith("gp-")){Flag = 2; /* Gym Picture*/}
//            else if(){Flag = 3; /* Diet Picture*/}
        switch (Flag) {
            case (1):
                //Profile Picture Image
                uploadPath = Paths.get("Files-Upload/Profile-Pictures");

            case (2):
                //Gym Picture Image
                uploadPath = Paths.get("Files-Upload/Gym-Pictures");
        }

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        //String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(Type.substring(3,Type.length())+".png");
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }

        return Type.substring(3,Type.length())+".png";
    }
}