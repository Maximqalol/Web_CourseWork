package ru.example.register.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.example.register.db.entity.Picture;
import ru.example.register.db.repository.PictureRepository;

import java.io.IOException;

/**
 * Реализация интерфейса IPictureService.
 *
 * @author Комов Максим
 */
@Service
public class PictureServiceImpl implements IPictureService {

    /**
     * Репозиторий для картинки.
     */
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Picture create(MultipartFile picture) throws IOException {
        String name = StringUtils.cleanPath(picture.getOriginalFilename());
        Picture pic = new Picture();
        pic.setName(name);
        pic.setType(picture.getContentType());
        pic.setData(picture.getBytes());
        return pictureRepository.save(pic);
    }

    @Override
    public Picture getPicture(int id) {
        return pictureRepository.findById(id).get();
    }

    @Override
    public Picture findByName(String name) {
        return pictureRepository.findByName(name);
    }

    @Override
    public void delete(Picture picture) {
        pictureRepository.delete(picture);
    }
}