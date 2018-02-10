import controller.Controller;
import model.Pozycja;
import model.Zamowienie;
import view.View;

import java.io.IOException;

public class Main {
    public static void main(String [] args) throws IOException {

        //Controller kontr = new Controller();
        View view = new View();

        //Pozycja model = new Pozycja();
        //Zamowienie zamowienie = new Zamowienie();


        Pozycja p1 = new Pozycja("Chleb", 1, 3.5);
        Pozycja p2 = new Pozycja("Cukier", 3, 4);
        Pozycja p3 = new Pozycja("Chleb", 1, 2.5);
        Pozycja p4 = new Pozycja("Jabłko", 5, 1);
        Pozycja p5 = new Pozycja("Chleb", 10, 3.5);

        view.displayPozycja(p1);
        view.displayPozycja(p2);
        view.displayPozycja(p3);
        view.displayPozycja(p4);
        view.displayPozycja(p5);

        Zamowienie z = new Zamowienie(20);
        z.dodajPozycje(p1);
        z.dodajPozycje(p2);
        z.dodajPozycje(p3);
        z.dodajPozycje(p4);
        z.dodajPozycje(p5);
        System.out.println("\n"+z);

//        z.edytujPozycje( 1);
//        System.out.println("\n"+z);

        Zamowienie.zapiszZamowienie(z,"zamowienie.json");

        for(int j=0; j<4; j++) z.usunPozycje(1);

        System.out.println("\n"+z.toString());

        Zamowienie.wczytajZamowienie("zamowienie.json");

        //System.out.println("\n"+z.toString());

    }
}
