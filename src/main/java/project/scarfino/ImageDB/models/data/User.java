package project.scarfino.ImageDB.models.data;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.scarfino.ImageDB.models.AbstractEntity;
import project.scarfino.ImageDB.models.Image;

@Entity
public class User extends AbstractEntity {

    @NotNull(message = "Password is required.")
    @Size(min=8, message = "Password must be at least 8 characters long.")
    private String password;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @OneToOne
    private Image userImage;

    public User() {}

    public User(String username, String password, Image image) {
        super();
        this.setName(username);
        this.password = password;
        this.userImage = image;
    }


    public @NotNull @Size(min = 6) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 6) String password) {
        this.password = password;
    }

    public Image getUserImage() {
        return userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage = userImage;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, password);
    }
}
