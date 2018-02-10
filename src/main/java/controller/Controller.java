package controller;

import model.Pozycja;
import model.Zamowienie;
import view.View;

public class Controller {

    private Pozycja pozycja;
    private Zamowienie zamowienie;
    private View view;


    public Controller(Pozycja pozycja, Zamowienie zamowienie) {
        this.pozycja = pozycja;
        this.zamowienie = zamowienie;
        this.view = view;
    }

    public String getNazwaTowaru(){
        return pozycja.getNazwaTowaru();
    }




}
