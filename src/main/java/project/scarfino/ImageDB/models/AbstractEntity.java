package project.scarfino.ImageDB.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Please Enter a name.")
    @NotNull(message = "Please Enter a name.")
    @Size(min=3 , max=50, message = "Name must be within 3 and 50 charaters long.")
    String name;

    public int getId() {
        return id;
    }

    public @NotBlank(message = "Please Enter a name.") @NotNull(message = "Please Enter a name.") @Size(min = 3, max = 50, message = "Name must be within 3 and 50 charaters long.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Please Enter a name.") @NotNull(message = "Please Enter a name.") @Size(min = 3, max = 50, message = "Name must be within 3 and 50 charaters long.") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
