/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.wasdapp.services;

import com.realdolmen.wasdapp.domain.Informatie;
import com.realdolmen.wasdapp.exceptions.NoQueryPossibleException;
import com.realdolmen.wasdapp.repositories.AbstractRepository;
import com.realdolmen.wasdapp.repositories.InformatieRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KDLBL62
 */
public class InformatieService {
    private InformatieRepository informatieRepository;

    public InformatieService(InformatieRepository informatieRepository) 
    {
        this.informatieRepository = informatieRepository; 
    }
    
    public List<Informatie>findAll() throws NoQueryPossibleException{
        
        return informatieRepository.findAll();
    }
    
        public void insertItems(ArrayList<Informatie> infos) throws NoQueryPossibleException{
        informatieRepository.insertItems(infos);
    }

    
    
    
}
