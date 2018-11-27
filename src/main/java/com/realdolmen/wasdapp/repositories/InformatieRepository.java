/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.wasdapp.repositories;

import com.realdolmen.wasdapp.domain.Informatie;
import com.realdolmen.wasdapp.exceptions.NoQueryPossibleException;
//import static com.realdolmen.wasdapp.repositories.InformatieRepository.TITEL;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.LOCATIE;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.STRAAT;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.NUMMER;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.POSTCODE;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.GEMEENTE;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.LAND;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.OMSCHRIJVING;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.WIKIPEDIA_LINK;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.WEBSITE;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.TELEFOON;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.E_MAIL;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.PRIJS;
import static com.realdolmen.wasdapp.repositories.InformatieRepository.PERSOON;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformatieRepository extends AbstractRepository<Informatie, String>{
    
    public static final String KEY = "titel";
    public static final String TABLE_NAME = "informatie";
    //public static final String TITEL = "titel";
    public static final String LOCATIE = "locatie";
    public static final String STRAAT = "straat";
    public static final String NUMMER = "nummer";
    public static final String POSTCODE = "postcode";
    public static final String GEMEENTE = "gemeente";
    public static final String LAND = "land";
    public static final String OMSCHRIJVING = "omschrijving";
    public static final String WIKIPEDIA_LINK = "wikipedia_link";
    public static final String WEBSITE = "website";
    public static final String TELEFOON = "telefoon";
    public static final String E_MAIL = "e_mail";
    public static final String PRIJS = "prijs";
    public static final String PERSOON = "persoon";
    
    public InformatieRepository(){
        super(TABLE_NAME, KEY);
    }
   @Override
    public Informatie createObject(ResultSet resultSet) {
        try {
            Informatie informatie = new Informatie();
            informatie.setTitel(resultSet.getString(KEY));
            informatie.setLocatie(resultSet.getString(LOCATIE));
            informatie.setStraat(resultSet.getString(STRAAT));
            informatie.setNummer(resultSet.getString(NUMMER));
            informatie.setPostcode(resultSet.getString(POSTCODE));
            informatie.setGemeente(resultSet.getString(GEMEENTE));
            informatie.setLand(resultSet.getString(LAND));
            informatie.setOmschrijving(resultSet.getString(OMSCHRIJVING));
            informatie.setWiki_link(resultSet.getString(WIKIPEDIA_LINK));
            informatie.setWebsite(resultSet.getString(WEBSITE));
            informatie.setTelefoon(resultSet.getString(TELEFOON));
            informatie.setEmail(resultSet.getString(E_MAIL));
            informatie.setPersoon(resultSet.getString(PERSOON));
            informatie.setPrijs(resultSet.getDouble(PRIJS));

             return informatie;
            
        } catch (SQLException e) {
            return null;
        }
        
    }

    @Override
    public String getColumnString() {
       return "("+KEY+","+LOCATIE+","+STRAAT+","+NUMMER+","+POSTCODE+","+GEMEENTE+","+LAND+","+OMSCHRIJVING+", "+WIKIPEDIA_LINK+", "+WEBSITE+", "+TELEFOON+", "+E_MAIL+", "+PRIJS+", "+PERSOON+")";
    }

    @Override
    public String getValuesString(Informatie informatie) {
        if(informatie == null){
            throw new NullPointerException("The information you have given is null");
        }
        return "('"+informatie.getTitel()+"','"+ informatie.getLocatie()+"','"+informatie.getStraat()+"','"+informatie.getNummer()+"','"+informatie.getPostcode()+"','"+informatie.getGemeente()+"','"+informatie.getLand()+"','"+informatie.getOmschrijving()+"','"+informatie.getWiki_link()+"','"+informatie.getWebsite()+"','"+informatie.getTelefoon()+"','"+informatie.getEmail()+"',"+informatie.getPrijs()+",'"+informatie.getPersoon()+"')";    
    }
    
}
