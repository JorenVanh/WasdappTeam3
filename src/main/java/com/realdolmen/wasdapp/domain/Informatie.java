/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.wasdapp.domain;

import java.math.BigDecimal;

/**
 *
 * @author JVHBL61
 */
public class Informatie {
    private String titel,locatie, straat,gemeente, land, omschrijving, wiki_link, website, telefoon,email,persoon,nummer, postcode;
    private BigDecimal prijs;

    public Informatie() {
    }
    
    
    
    public Informatie(String titel, String locatie, String straat, String nummer, String postcode, String gemeente, String land, String omschrijving, String wiki_link, String website, String telefoon, String email, BigDecimal prijs, String persoon){
        this.titel = titel;
        this.locatie = locatie;
        this.straat = straat;
        this.nummer = nummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.land = land;
        this.omschrijving = omschrijving;
        this.wiki_link = wiki_link;
        this.website = website;
        this.telefoon = telefoon;
        this.email = email;
        this.prijs = prijs;
        this.persoon = persoon;
        
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getWiki_link() {
        return wiki_link;
    }

    public void setWiki_link(String wiki_link) {
        this.wiki_link = wiki_link;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersoon() {
        return persoon;
    }

    public void setPersoon(String persoon) {
        this.persoon = persoon;
    }

    public BigDecimal  getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal  prijs) {
        this.prijs = prijs;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    
    
}
