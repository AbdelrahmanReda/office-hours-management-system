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
public class UserMessage {

    public int id;
    public Message message = new Message();
    public Conversation conversation = new Conversation();
    public String recipent;
    public String reciever;

}
