package com.realdolmen.maven.wasdapp.repositories;

import com.realdolmen.wasdapp.domain.Informatie;
import com.realdolmen.wasdapp.exceptions.NoQueryPossibleException;
import com.realdolmen.wasdapp.repositories.InformatieRepository;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hamcrest.CoreMatchers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

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
        when(resultSet.getBigDecimal(InformatieRepository.PRIJS)).thenReturn(BigDecimal.ONE);
        
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
        assertEquals(BigDecimal.ONE, result.getPrijs());
        
       
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
        verify(resultSet, times(1)).getBigDecimal(InformatieRepository.PRIJS);
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
          
        
  
    
    




