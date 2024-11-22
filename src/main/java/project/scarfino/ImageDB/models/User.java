package project.scarfino.ImageDB.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity {

    @NotNull
    String username;

    @NotNull(message = "Password is required.")
    @Size(min=8, message = "Password must be at least 8 characters long.")
    private String password;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @OneToOne
    private Image userImage;

    public User() {}

    public User(String username, String password, Image image) {
        super();
        this.username = username;
        this.password = password;
        this.userImage = image;
    }

    public User(String username, String password) {
        super();
        this.setName(username);
        this.password = password;
        this.userImage = getUserImage();
    }


    public @NotNull @Size(min = 8) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 8) String password) {
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
