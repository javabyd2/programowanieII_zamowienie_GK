package view;

import model.Pozycja;

import java.text.DecimalFormat;

public class View {

    public View() {
    }

    DecimalFormat formater = new DecimalFormat("#0.00");

    public void displayPozycja(Pozycja pozycja){
        double wart = pozycja.obliczWartosc();
        String wn= String.valueOf(formater.format(wart));
        int dt=wn.length();
        if(wn.length()<10){
            for(int j=0; j<10-dt;j++) wn=" "+wn;
        }
        wn+=" zł";
        System.out.println(pozycja.toString()+wn);
    }


}
