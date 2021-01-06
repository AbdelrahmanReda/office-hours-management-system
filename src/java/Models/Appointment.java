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
public class Appointment {

    public int id;
    public Staff staff =new Staff();
    public OfficeHours  officeHours = new OfficeHours();
    public Student student = new Student();

}
