package api.user.repository;

import api.user.model.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<ProfilePicture, Integer> {
}
