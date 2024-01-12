package com.example.app.controller;

import com.example.app.domain.vo.FileVO;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/file/*")
public class FileController {
    @PostMapping("/upload")
    public List<FileVO> upload(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload";
        String uploadPath = getUploadPath();
        List<FileVO> files = new ArrayList<>();

        File uploadFullPath = new File(rootPath, uploadPath);
        if(!uploadFullPath.exists()){uploadFullPath.mkdirs();}

        for(MultipartFile multipartFile : upload){
            FileVO fileVO = new FileVO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            String uploadFileName = uuid.toString() + "_" + fileName;

            fileVO.setFileName(fileName);
            fileVO.setFileUuid(uuid.toString());
            fileVO.setFileUploadPath(getUploadPath());
            fileVO.setFileSize(multipartFile.getSize());

            File fullPath = new File(uploadFullPath, uploadFileName);
            multipartFile.transferTo(fullPath);

            if(Files.probeContentType(fullPath.toPath()).startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(uploadFullPath, "s_" + uploadFileName));
                Thumbnailator.createThumbnail(multipartFile.getInputStream(), out, 100, 100);
                out.close();
                fileVO.setFileImageCheck(true);
            }

            files.add(fileVO);
        }
        return files;
    }

    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException{
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }

    @PostMapping("/delete")
    public void delete(FileVO fileVO) {
        File file = new File("C:/upload", fileVO.getFileUploadPath() + "/" + fileVO.getFileName());
        if(file.exists()){
            file.delete();
        }
        if(fileVO.isFileImageCheck()){
            file = new File("C:/upload", fileVO.getFileUploadPath() + "/s_" + fileVO.getFileName());
            if(file.exists()){
                file.delete();
            }
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException {
        Resource resource = new FileSystemResource("C:/upload/" + fileName);
        HttpHeaders header = new HttpHeaders();
        String name = resource.getFilename();
        name = name.substring(name.indexOf("_") + 1);
        header.add("Content-Disposition", "attachment;filename=" + new String(name.getBytes(), "UTF-8"));
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }

    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

}





















