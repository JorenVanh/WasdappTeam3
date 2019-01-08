/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.wasdapp;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.realdolmen.wasdapp.domain.Informatie;
import com.realdolmen.wasdapp.exceptions.NoQueryPossibleException;
import com.realdolmen.wasdapp.repositories.InformatieRepository;
import com.realdolmen.wasdapp.services.InformatieService;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JVHBL61
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("./input");

        WatchKey watchKey;
        
            watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
      
        while ((watchKey = watchService.take()) != null) {
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                System.out.println(
                        "Event kind:" + event.kind()
                        + ". File affected: " + event.context() + ".");
                if (event.kind() == ENTRY_CREATE) {
                    System.out.println("File created => proccesing file");
                    proccesFile(event.context());
                }
            }
            watchKey.reset();
        }
    }   catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void proccesFile(Object context) {
        if (context.toString().endsWith(".csv")) {
            InformatieRepository informatieRepository = new InformatieRepository();
            InformatieService informatieService = new InformatieService(informatieRepository);
            ArrayList<Informatie> info = new ArrayList<Informatie>();
            try {
                Reader reader = Files.newBufferedReader(Paths.get("./input/" + context));
                CsvToBean<Informatie> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Informatie.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<Informatie> csvUserIterator = csvToBean.iterator();

                while (csvUserIterator.hasNext()) {
                    Informatie infor = csvUserIterator.next();
                    info.add(new Informatie(infor.getTitel(), infor.getLocatie(), infor.getStraat(), infor.getNummer(), infor.getPostcode(), infor.getGemeente(), infor.getLand(), infor.getOmschrijving(), infor.getWiki_link(), infor.getWebsite(), infor.getTelefoon(), infor.getEmail(), infor.getPrijs(), infor.getPersoon()));
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                informatieService.insertItems(info);
                System.out.println("Data succesvol doorgestuurd");
            } catch (NoQueryPossibleException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (context.toString().endsWith(".json")) {
            System.out.println("json");
        } else {
            System.out.println("geen csv of json bestand");
        }

    }
}
