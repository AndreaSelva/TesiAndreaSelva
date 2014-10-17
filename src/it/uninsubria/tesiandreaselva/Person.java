package it.uninsubria.tesiandreaselva;

public class Person {
    private String name;
    private String surname;
    private String telephone;
    public Person(String name, String surname, String telephone) {
            super();
            this.name = name;
            this.surname = surname;
            this.telephone = telephone;
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
}