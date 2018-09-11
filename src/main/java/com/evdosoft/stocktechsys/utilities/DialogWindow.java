/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.utilities;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

/**
 *
 * @author atlantis
 */
@Component
public class DialogWindow {
 
    public boolean AskUserInputNewDatabase () {    
	//default icon, custom title
        JFrame frame = new JFrame();
        int n = JOptionPane.showConfirmDialog(
        frame,
        "Do you want to create a Brand new Database ?\n"+
        "WARNING - THIS WILL ERASE ALL YOUR DATA\n",
        "Welcome to StockTechSys",
        JOptionPane.YES_NO_OPTION);
        if (n==0) return true;
        else return false;
        
       
    }
}
