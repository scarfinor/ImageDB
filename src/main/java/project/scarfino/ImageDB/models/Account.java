package project.scarfino.ImageDB.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Account extends AbstractEntity {

    @NotNull
    private String accountName;

    @NotNull
    @Size(min=3)
    private String password;

    @OneToOne
    private Image accountImage;

    public Account() {}

    public Account(String accountName, String password, Image image) {
        this.accountName = accountName;
        this.password = password;
        this.accountImage = image;
    }

    public Account(String accountName, String password) {
        this.accountName = accountName;
        this.password = password;
    }

    public Image getAccountImage() {
        return accountImage;
    }

    public @NotNull String getAccountName() {
        return accountName;
    }

    public void setAccountName(@NotNull String accountName) {
        this.accountName = accountName;
    }

    public void setAccountImage(Image accountImage) {
        this.accountImage = accountImage;
    }

    public @NotNull @Size(min = 3) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 3) String password) {
        this.password = password;
    }
}
