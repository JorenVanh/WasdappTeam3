/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.wasdapp;

import com.realdolmen.wasdapp.domain.Informatie;
import com.realdolmen.wasdapp.exceptions.NoQueryPossibleException;
import com.realdolmen.wasdapp.repositories.InformatieRepository;
import com.realdolmen.wasdapp.services.InformatieService;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author demun
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws com.realdolmen.wasdapp.exceptions.NoQueryPossibleException
     */
    public static void main(String[] args) throws NoQueryPossibleException {
        // TODO code application logic here
        InformatieService infoservice = new InformatieService(new InformatieRepository());
        
        System.out.println(infoservice.findAll().get(0).getTitel());
        
        //insert item test
        ArrayList<Informatie> infos = new ArrayList<>();
        infos.add(new Informatie("haha1", "x", "x", "123", "234", "x", "x", "x", "x", "x", "x", "x", BigDecimal.ONE, "Joren"));
        infos.add(new Informatie("haha2", "x", "x", "123", "234", "x", "x", "x", "x", "x", "x", "x", BigDecimal.ONE, "Joren"));
        infos.add(new Informatie("haha3", "x", "x", "123", "234", "x", "x", "x", "x", "x", "x", "x", BigDecimal.ONE, "Joren"));              
       infoservice.insertItems(infos);
       
       
       
       
       
  

        
        
    }
    
}


