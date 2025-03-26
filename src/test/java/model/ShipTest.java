package model;
import static org.junit.jupiter.api.Assertions.*;

import com.hundirlaflota.model.Battleship;
import com.hundirlaflota.model.Canoe;
import com.hundirlaflota.model.Frigate;
import org.junit.jupiter.api.Test;

/**
 * Tests unitarios para las clases de barcos.
 */
public class ShipTest {

    @Test
    public void testBattleshipSunk() {
        Battleship battleship = new Battleship.Builder().build();
        for (int i = 0; i < 5; i++) {
            battleship.hit(i);
        }
        assertTrue(battleship.isSunk(),
                "El Battleship debería estar hundido tras recibir 5 impactos.");
    }

    @Test
    public void testFrigateSunk() {
        Frigate frigate = new Frigate.Builder().build();
        for (int i = 0; i < 3; i++) {
            frigate.hit(i);
        }
        assertTrue(frigate.isSunk(),
                "La Frigate debería estar hundida tras recibir 3 impactos.");
    }

    @Test
    public void testCanoeSunk() {
        Canoe canoe = new Canoe.Builder().build();
        canoe.hit(0);
        assertTrue(canoe.isSunk(),
                "La Canoe debería estar hundida tras recibir 1 impacto.");
    }
}
