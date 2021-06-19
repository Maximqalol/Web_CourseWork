package ru.example.register.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.register.db.entity.Picture;

/**
 * Репозиторий для картинок.
 *
 * @author Комов Максим
 */
@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    /**
     * Поиск картинки по ее названию.
     *
     * @param name название картинки
     * @return найденная картинка
     */
    Picture findByName(String name);
}
