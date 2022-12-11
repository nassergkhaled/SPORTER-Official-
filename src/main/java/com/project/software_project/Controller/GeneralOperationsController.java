package com.project.software_project.Controller;

import java.io.IOException;

import com.project.software_project.assistance.FileDownloadUtil;
import com.project.software_project.assistance.FileUploadResponse;
import com.project.software_project.assistance.FileUploadUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class GeneralOperationsController{
        @PostMapping("/uploadFile")
        public ResponseEntity<FileUploadResponse> uploadFile(
                @RequestParam("file") MultipartFile multipartFile,
                @RequestParam ("filetype") String Type)
                throws IOException {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            long size = multipartFile.getSize();
            String filecode = FileUploadUtil.saveFile(fileName, multipartFile,Type);
            FileUploadResponse response = new FileUploadResponse();
            response.setFileName(fileName);
            response.setSize(size);
            response.setDownloadUri("/Files-Upload/Pictures/" + filecode);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }


//    @PostMapping("/uploadVideo")
//        public ResponseEntity<FileUploadResponse> uploadVideo(
//            @RequestParam("file") MultipartFile multipartFile,
//            @RequestParam ("filetype") String Type)
//            throws IOException {
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        long size = multipartFile.getSize();
//        String filecode = FileUploadUtil.saveFile(fileName, multipartFile,Type);
//        FileUploadResponse response = new FileUploadResponse();
//        response.setFileName(fileName);
//        response.setSize(size);
//        response.setDownloadUri("/Files-Upload/Videos/" + filecode);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
