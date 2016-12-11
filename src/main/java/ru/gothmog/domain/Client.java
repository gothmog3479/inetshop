package ru.gothmog.domain;

import java.sql.Date;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public class Client {

    private long id;
    private String login;
    private String password;
    private String surname;
    private String name;
    private Date registrationDate;
    private String phone;
    private String address;
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Client client = (Client) obj;

        if (id != client.id) return false;
        if (!login.equals(client.login)) return false;
        if (!password.equals(client.password)) return false;
        if (!surname.equals(client.surname)) return false;
        if (!name.equals(client.name)) return false;
        if (!registrationDate.equals(client.registrationDate)) return false;
        if (!phone.equals(client.phone)) return false;
        if (!address.equals(client.address)) return false;
        return email.equals(client.email);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + registrationDate.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
