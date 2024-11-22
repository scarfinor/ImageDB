package project.scarfino.ImageDB.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import org.apache.tomcat.util.codec.binary.Base64;

@Entity
public class Image extends AbstractEntity {

    @Lob
    private byte[] imageData;

    public Image(){};

    public Image(String name, byte[] imageData) {

        super();
        this.setName(name);
        this.imageData = imageData;
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
