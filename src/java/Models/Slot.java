/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Time;

/**
 *
 * @author boody
 */
public class Slot {

    public int id;
    public String slot_name;
    public Time from_hour;
    public Time to_hour;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the slot_name
     */
    public String getSlot_name() {
        return slot_name;
    }

    /**
     * @return the from_hour
     */
    public Time getFrom_hour() {
        return from_hour;
    }

    /**
     * @return the to_hour
     */
    public Time getTo_hour() {
        return to_hour;
    }

}
