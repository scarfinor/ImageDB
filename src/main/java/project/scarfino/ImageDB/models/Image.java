package project.scarfino.ImageDB.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import org.apache.tomcat.util.codec.binary.Base64;

@Entity
public class Image extends AbstractEntity {

    @NotNull
    private String name;

    @Lob
    private byte[] imageData;

    public Image(){};

    public Image(String name, byte[] imageData) {
        super();
        this.name = name;
        this.imageData = imageData;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageDataBase64() {
        return Base64.encodeBase64String(this.imageData);
    }

}
