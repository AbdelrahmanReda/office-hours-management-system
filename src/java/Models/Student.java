/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author boody
 */
public class Student {

    public int id;
    public String user_name;
    public String mail;
    public String password;
    public String gender;
    public String country;
    public int student_level;
    public float student_gpa;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the student_level
     */
    public int getStudent_level() {
        return student_level;
    }

    /**
     * @return the student_gpa
     */
    public float getStudent_gpa() {
        return student_gpa;
    }
    
    

}
