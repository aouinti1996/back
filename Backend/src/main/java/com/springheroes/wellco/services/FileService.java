package com.springheroes.wellco.services;

import com.springheroes.wellco.entities.File;
import com.springheroes.wellco.repositories.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileService {

    @Autowired
    FileRepo fileRepo;

    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File File = new File(fileName, file.getContentType(), file.getBytes());
        return fileRepo.save(File);
    }
    public File getFile(Integer id) {
        return fileRepo.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return fileRepo.findAll().stream();
    }
}
