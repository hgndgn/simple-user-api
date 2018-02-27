package api.user.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "USER_PHOTO")
public class UserPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TYPE_")
    private String type;

    @Lob
    @Column(name = "DATA_")
    private byte[] data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nData: " + Arrays.toString(data) +
                "\nType: " + type;
    }
}
