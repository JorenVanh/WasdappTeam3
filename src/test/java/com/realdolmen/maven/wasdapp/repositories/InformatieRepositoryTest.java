/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.maven.wasdapp.repositories;

import com.realdolmen.wasdapp.domain.Informatie;
import com.realdolmen.wasdapp.exceptions.NoQueryPossibleException;
import com.realdolmen.wasdapp.repositories.InformatieRepository;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.E_MAIL;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.GEMEENTE;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.LAND;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.LOCATIE;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.NUMMER;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.OMSCHRIJVING;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.PERSOON;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.POSTCODE;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.PRIJS;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.STRAAT;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.TELEFOON;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.WEBSITE;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.WIKIPEDIA_LINK;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hamcrest.CoreMatchers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author LNSBG31
 */

@RunWith(MockitoJUnitRunner.class)
public class InformatieRepositoryTest {
    
    private static String url = "jdbc:mysql://localhost:3306/wasdapp?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

  private InformatieRepository informatieRepository;

    @Mock
    private ResultSet resultSet;

    @Before
    public void init() {
        informatieRepository = new InformatieRepository();
    }
    
    @Test
    public void createObjectTestSucces() throws SQLException, NoQueryPossibleException {
        //init data
        when(resultSet.getString(InformatieRepository.KEY)).thenReturn("titel");
        when(resultSet.getString(InformatieRepository.LOCATIE)).thenReturn("locatie");
        when(resultSet.getString(InformatieRepository.STRAAT)).thenReturn("straat");
        when(resultSet.getString(InformatieRepository.NUMMER)).thenReturn("nummer");
        when(resultSet.getString(InformatieRepository.POSTCODE)).thenReturn("postcode");
        when(resultSet.getString(InformatieRepository.GEMEENTE)).thenReturn("gemeente");
        when(resultSet.getString(InformatieRepository.LAND)).thenReturn("land");
        when(resultSet.getString(InformatieRepository.OMSCHRIJVING)).thenReturn("omschrijving");
        when(resultSet.getString(InformatieRepository.WIKIPEDIA_LINK)).thenReturn("wikipedia_link");
        when(resultSet.getString(InformatieRepository.WEBSITE)).thenReturn("website");
        when(resultSet.getString(InformatieRepository.TELEFOON)).thenReturn("telefoon");
        when(resultSet.getString(InformatieRepository.E_MAIL)).thenReturn("email");
        when(resultSet.getString(InformatieRepository.PERSOON)).thenReturn("persoon");
        when(resultSet.getDouble(InformatieRepository.PRIJS)).thenReturn(1.0);
        
        //test the test objects
        Informatie result = informatieRepository.createObject(resultSet);
        //verify the result
        assertEquals("titel", result.getTitel());
        assertEquals("locatie", result.getLocatie());
        assertEquals("straat", result.getStraat());
        assertEquals("nummer", result.getNummer());
        assertEquals("postcode", result.getPostcode());
        assertEquals("gemeente", result.getGemeente());
        assertEquals("land", result.getLand());
        assertEquals("omschrijving", result.getOmschrijving());
        assertEquals("wikipedia_link", result.getWiki_link());
        assertEquals("website", result.getWebsite());
        assertEquals("telefoon", result.getTelefoon());
        assertEquals("email", result.getEmail());
        assertThat(1.0, CoreMatchers.equalTo(result.getPrijs()));
       
        verify(resultSet, times(1)).getString(InformatieRepository.KEY);
        verify(resultSet, times(1)).getString(InformatieRepository.LOCATIE);
        verify(resultSet, times(1)).getString(InformatieRepository.STRAAT);
        verify(resultSet, times(1)).getString(InformatieRepository.NUMMER);
        verify(resultSet, times(1)).getString(InformatieRepository.POSTCODE);
        verify(resultSet, times(1)).getString(InformatieRepository.GEMEENTE);
        verify(resultSet, times(1)).getString(InformatieRepository.LAND);
        verify(resultSet, times(1)).getString(InformatieRepository.KEY);
        verify(resultSet, times(1)).getString(InformatieRepository.OMSCHRIJVING);
        verify(resultSet, times(1)).getString(InformatieRepository.WIKIPEDIA_LINK);
        verify(resultSet, times(1)).getString(InformatieRepository.WEBSITE);
        verify(resultSet, times(1)).getString(InformatieRepository.TELEFOON);
        verify(resultSet, times(1)).getString(InformatieRepository.E_MAIL);
        verify(resultSet, times(1)).getDouble(InformatieRepository.PRIJS);
    }
   
     @Test
    public void createObjectTestThrowsSQLException() throws SQLException {
        //init data
        when(resultSet.getString(informatieRepository.KEY)).thenThrow(SQLException.class);
        //test the object
        Informatie result = informatieRepository.createObject(resultSet);
        //verify the result
        assertNull(result);
        verify(resultSet, times(1)).getString(InformatieRepository.KEY);
        verifyNoMoreInteractions(resultSet);
    }
}
          
        
  
    
    




