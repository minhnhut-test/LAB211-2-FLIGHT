/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBussiness.Entity.Person;

import DataBussiness.Validation.Validation;
import java.util.Date;

/**
 *
 * @author nhutm
 */
public abstract class Person {
    private String email;
    private String name;
    private String address;
    private Date birthday;

    public Person() {
    }

    public Person(String email, String name, String address, Date birthday) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return   email + ", " + name + ", " + address + ", " + Validation.executeNullDate(birthday) ;
    }
    
    
}
