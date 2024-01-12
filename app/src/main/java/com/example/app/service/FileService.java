package com.example.app.service;

import com.example.app.domain.vo.FileVO;
import com.example.app.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileDAO fileDAO;

    public List<FileVO> showOldFiles(){
        return fileDAO.findOldFiles();
    }
}
