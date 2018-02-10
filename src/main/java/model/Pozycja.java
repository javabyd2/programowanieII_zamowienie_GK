package model;

import java.text.DecimalFormat;

public class Pozycja {

    private String nazwaTowaru;
    private int ileSztuk;
    private double cena;

    public Pozycja(String nazwaTowaru, int ileSztuk, double cena) {
        this.nazwaTowaru = nazwaTowaru;
        this.ileSztuk = ileSztuk;
        this.cena = cena;
    }

    public double obliczWartosc(){
        return (double) ileSztuk * cena;
    }

    public double obliczWartoscZRabatem(){
        double suma = (double) ileSztuk * cena;
        if (ileSztuk>=5 && ileSztuk<=10) suma*=0.95;
        if (ileSztuk>10 && ileSztuk<=20) suma*=0.90;
        if (ileSztuk>20) suma*=0.85;
        return suma;
    }

    @Override
    public String toString() {
        DecimalFormat formater = new DecimalFormat("#0.00");

        String t=this.nazwaTowaru.trim();
        int dt=t.length();
        if(t.length()>20)
            t=t.substring(0,17)+"...";
        if(t.length()<20){
            for(int i=0; i<20-dt;i++) t=t+" ";
        }

        String cn= String.valueOf(formater.format(this.cena));
        dt=cn.length();
        if(cn.length()<10){
            for(int i=0; i<10-dt;i++) cn=" "+cn;
        }
        cn+=" zł";

        String sn= String.valueOf(this.ileSztuk);
        dt=sn.length();
        if(sn.length()<4){
            for(int i=0; i<4-dt;i++) sn=" "+sn;
        }
        sn+=" szt.";
        return t+cn+sn;
    }

    public String getNazwaTowaru() {
        return nazwaTowaru;
    }

    public void setNazwaTowaru(String nazwaTowaru) {
        this.nazwaTowaru = nazwaTowaru;
    }

    public int getIleSztuk() {
        return ileSztuk;
    }

    public void setIleSztuk(int ileSztuk) {
        this.ileSztuk = ileSztuk;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

}
