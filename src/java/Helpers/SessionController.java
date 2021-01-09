/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author boody
 */
public class SessionController {

    public static String getSessionAtrributeValue(HttpServletRequest request, String attribute) {
        HttpSession session = request.getSession();
        return session.getAttribute(attribute).toString();

    }

}
