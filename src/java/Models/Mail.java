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
public class Mail {

    public int id;
    public Message message = new Message();
    public Staff staffSneder = new Staff();
    public Staff staffRecieever = new Staff();
    public Student stafSender = new Student();
    public Student stafReciever = new Student();

}
