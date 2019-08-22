package ujjwal.gl.employees.pojo;

import android.net.Uri;

public class Employee {

    String name;
    Uri image;

    public Employee(String name, Uri image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
