package model;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;

public class PozycjaTest {

    @Test
    public void obliczWartosc() throws Exception{
        Pozycja poz = new Pozycja("ekierka",15, 22.11);
        double wartosc = poz.obliczWartosc();
        double wynik=331.65;
        assertThat(wartosc,is(equalTo(wynik)));

    }
}