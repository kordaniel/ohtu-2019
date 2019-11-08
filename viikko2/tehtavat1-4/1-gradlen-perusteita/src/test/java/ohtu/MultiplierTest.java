package ohtu;

import static org.junit.Assert.*;
import org.junit.Test;

public class MultiplierTest {

  @Test
  public void kertominenToimii() {
    Multiplier viisi = new Multiplier(5);

    assertEquals(0, viisi.multipliedBy(1));
    assertEquals(335, viisi.multipliedBy(7));
  }
}