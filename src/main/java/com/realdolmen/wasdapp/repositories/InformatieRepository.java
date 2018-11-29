/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.wasdapp.repositories;

import com.realdolmen.wasdapp.domain.Informatie;
import com.realdolmen.wasdapp.GUI;
import com.realdolmen.wasdapp.exceptions.NoQueryPossibleException;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InformatieRepository extends AbstractRepository<Informatie, String>{
    
    public static final String KEY = "titel";
    public static final String TABLE_NAME = "informatie";
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
    
    public void insertItems(ArrayList<Informatie> infos) throws NoQueryPossibleException{
        try (Connection connection = createConnection()) {
            System.out.println("Connection succes");
            for(int i = 0; i <= infos.size()-1 ; i++ ){
            PreparedStatement pstatement = connection.prepareStatement("REPLACE INTO " + TABLE_NAME + getColumnString() + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstatement.setString(1, infos.get(i).getTitel());
            pstatement.setString(2, infos.get(i).getLocatie());
            pstatement.setString(3, infos.get(i).getStraat());
            pstatement.setString(4, infos.get(i).getNummer());
            pstatement.setString(5, infos.get(i).getPostcode());
            pstatement.setString(6, infos.get(i).getGemeente());
            pstatement.setString(7, infos.get(i).getLand());
            pstatement.setString(8, infos.get(i).getOmschrijving());
            pstatement.setString(9, infos.get(i).getWiki_link());
            pstatement.setString(10, infos.get(i).getWebsite());
            pstatement.setString(11, infos.get(i).getTelefoon());
            pstatement.setString(12, infos.get(i).getEmail());
            pstatement.setDouble(13, ParseDouble(String.valueOf(infos.get(i).getPrijs())));
            pstatement.setString(14, infos.get(i).getPersoon());
                System.out.println(pstatement);
            pstatement.executeUpdate();
        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoQueryPossibleException("Insert " + TABLE_NAME + " can not be excecuted ");
        }
    }

    public double ParseDouble(String strNumber) {
   if (strNumber != null && strNumber.length() > 0) {
       try {
          return Double.parseDouble(strNumber);
       } catch(Exception e) {
          return 0;   // or some value to mark this field is wrong. or make a function validates field first ...
       }
   }
   else return 0;
}
    
    
    @Override
    public String getColumnString() {
       return "("+KEY+","+LOCATIE+","+STRAAT+","+NUMMER+","+POSTCODE+","+GEMEENTE+","+LAND+","+OMSCHRIJVING+", "+WIKIPEDIA_LINK+", "+WEBSITE+", "+TELEFOON+", "+E_MAIL+", "+PRIJS+", "+PERSOON+")";
    }
}
