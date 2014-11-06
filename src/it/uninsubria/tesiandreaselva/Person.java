package it.uninsubria.tesiandreaselva;

public class Person {
    private String name;
    private String surname;
    private String telephone;
    private int photoRes;
    public Person(String name, String surname, String telephone, int photoRes) {
            super();
            this.name = name;
            this.surname = surname;
            this.telephone = telephone;
            this.photoRes = photoRes;
    }
    public String getName() {
            return name;
    }
    public String getSurname() {
            return surname;
    }
    public String getTelephone() {
            return telephone;
    }
    public int getPhotoRes() {
        return photoRes;
}
}