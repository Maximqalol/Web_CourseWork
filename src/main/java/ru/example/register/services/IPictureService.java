package ru.example.register.services;

import org.springframework.web.multipart.MultipartFile;
import ru.example.register.db.entity.Picture;

import java.io.IOException;

public interface IPictureService {


    Picture create(MultipartFile picture) throws IOException;


    Picture getPicture(int id);


    Picture findByName(String name);

    void delete(Picture picture);
}