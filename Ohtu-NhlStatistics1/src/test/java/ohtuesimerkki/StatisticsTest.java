
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    
    public StatisticsTest() {
        stats = new Statistics(readerStub);
    }
       
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void pelaajaLoytyy() {
        Player p = stats.search("Kurri");
        assertEquals("Kurri", p.getName());
    }
    
    @Test
    public void josPelaajaaEiLoydyPalauttaaNull() {
        Player p = stats.search("Hurri");
        assertNull(p);
    }
    
    @Test
    public void palauttaaListanaTeaminPelaajat() {
        List<Player> p = stats.team("DET");
        assertEquals(1, p.size());
        assertEquals("Yzerman", p.get(0).getName());
    }
    
    @Test
    public void topScoresPalauttaaKysytynMaaranPelaajia() {
        assertEquals(3, stats.topScorers(3).size());
        assertEquals(5, stats.topScorers(5).size());
    }
}


