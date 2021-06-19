package ru.example.register.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.example.register.db.entity.Picture;
import ru.example.register.services.IPictureService;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/picture")
public class PictureController {

    private final IPictureService pictureService;

    public PictureController(IPictureService pictureService) {
        this.pictureService = pictureService;
    }


    @PostMapping
    public Picture uploadPicture(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return pictureService.create(multipartFile);
    }
}
