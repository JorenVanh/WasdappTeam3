/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.maven.wasdapp.services;

import com.realdolmen.wasdapp.domain.Informatie;
import com.realdolmen.wasdapp.repositories.InformatieRepository;
import com.realdolmen.wasdapp.services.InformatieService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author LNSBG31
 */
@RunWith(MockitoJUnitRunner.class)
public class InformatieServiceTest {
     //Object to test
    private InformatieService informatieService;
    //Object(s) not to test but needed for the object to test => Mocks
    @Mock
    private InformatieRepository informatieRepository;
    
    @Before
    public void init() {
        informatieService = new InformatieService(informatieRepository);
    }
    
     @Test
    public void testFindAll() throws Exception {
        //init data to test
        List<Informatie> infos = new ArrayList<>();
        when(informatieRepository.findAll()).thenReturn(infos);
        //test the method
        List<Informatie> result = informatieService.findAll();
        //verify the results
        assertEquals(result, infos);
        verify(informatieRepository,times(1)).findAll();
    }
    
}
