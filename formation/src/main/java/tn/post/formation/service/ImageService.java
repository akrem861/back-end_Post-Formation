package tn.post.formation.service;

import com.fasterxml.jackson.databind.util.NativeImageUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.post.formation.exception.ImageException;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class ImageService {

    private final String dirPath = "D:\\Project Post Fromation\\formation\\uploads\\";

    public String addPhoto(MultipartFile image) throws ImageException {
        if(image==null)
            return null;
        if(image.isEmpty())
            throw new ImageException("file is not suported");
        String fileName =new Date().getTime() +"."+ FilenameUtils.getExtension(image.getOriginalFilename()) ;
        String filePath = dirPath + fileName;
        File destinationFile = new File(filePath);
        try {
            image.transferTo(destinationFile);
        } catch (IOException e) {
            throw new ImageException("problem in the server !!!!");
        }
        return "/images/"+fileName;
    }


}
