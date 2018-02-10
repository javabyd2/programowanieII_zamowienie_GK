package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zamowienie implements Serializable{

    private List<Pozycja> pozycje = new ArrayList<Pozycja>();
    private int ileDodanych;
    private int maksRozmiar;

    private Scanner input = new Scanner(System.in);

    public Zamowienie() {
        maksRozmiar=10;
    }

    public Zamowienie(int maksRozmiar) {
        this.maksRozmiar = maksRozmiar;
    }

    public void dodajPozycje(Pozycja p){
        int ile = pozycje.size();
        if(ile<maksRozmiar) {
            if(this.sprawdzCzyPozycjaJestWZamowieniu(p)){
                for(Pozycja poz: pozycje){
                    if(poz.getCena()==p.getCena() && poz.getNazwaTowaru()==p.getNazwaTowaru()){
                        poz.setIleSztuk(p.getIleSztuk()+poz.getIleSztuk());
                    }
                }
            }
            else pozycje.add(p);
        }
        else System.out.println("lista zamówień jest przepełniona");
    }

    private boolean sprawdzCzyPozycjaJestWZamowieniu(Pozycja p){
        boolean exist = false;
        for(Pozycja poz: pozycje){
            if(poz.getCena()==p.getCena() && poz.getNazwaTowaru()==p.getNazwaTowaru()) exist=true;
        }
        return exist;
    }

    public void usunPozycje(int indeks){
        pozycje.remove(--indeks);
    }

    public void edytujPozycje(int indeks){
        Pozycja p = pozycje.get(indeks);
        System.out.println("\n"+p.getNazwaTowaru());
        String nazwaNew = input.next();
        p.setNazwaTowaru(nazwaNew);
        System.out.println(p.getCena());
        double cenaNew = input.nextDouble();
        p.setCena(cenaNew);
        System.out.println(p.getIleSztuk());
        int ileSztukNew = input.nextInt();
        p.setIleSztuk(ileSztukNew);
    }

    public double obliczWartosc(){
        double suma = 0;
        for (Pozycja p: pozycje){
            double wartosc = (double) p.getIleSztuk() * p.getCena();
            if ( p.getIleSztuk()>=5 &&  p.getIleSztuk()<=10) wartosc*=0.95;
            if ( p.getIleSztuk()>10 &&  p.getIleSztuk()<=20) wartosc*=0.90;
            if ( p.getIleSztuk()>20) wartosc*=0.85;
            suma+=wartosc;
        }
        return suma;
    }

    @Override
    public String toString() {
        String zamowienie = "Zamowienie:";
        for (int i=0;i<pozycje.size();i++){

            String rabat = "  rabat ";
            int ileSztuk=pozycje.get(i).getIleSztuk();
            double wart = pozycje.get(i).obliczWartosc();
            double wartZRab = pozycje.get(i).obliczWartoscZRabatem();

            if(wart!=wartZRab) wart=wartZRab;
            if (ileSztuk<5) rabat = "";
            if (ileSztuk>=5 && ileSztuk<=10) rabat += "5%";
            if (ileSztuk>10 && ileSztuk<=20) rabat += "10%";
            if (ileSztuk>20) rabat += "15%";

            DecimalFormat formater = new DecimalFormat("#0.00");
            String wn= String.valueOf(formater.format(wart));
            int dt=wn.length();
            if(wn.length()<10){
                for(int j=0; j<10-dt;j++) wn=" "+wn;
            }
            wn+=" zł";
            zamowienie+="\n"+(i+1)+"\t"+pozycje.get(i).toString()+wn+rabat;
        }
        return zamowienie;
    }

    //private static String pathname = "C:\\Users\\A666224\\Desktop\\JAVA\\";
    private static String pathname = "";

    public static void zapiszZamowienie(Zamowienie z, String nazwaPliku) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in file
        mapper.writeValue(new File(pathname+nazwaPliku), z.pozycje);

        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(z.pozycje);
    }

    //private List<Pozycja> pozycje = new ArrayList<Pozycja>();

    public static void wczytajZamowienie(String nazwaPliku) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Pozycja> pozycje2 =
                mapper.readValue(pathname+nazwaPliku,
                        new TypeReference<List<Pozycja>>(){});

    }
}
