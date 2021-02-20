import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestBowling {

    private Bowling bowling;


    @Before
    public void setUp() throws Exception {
        bowling = new Bowling();

    }

    @Test
    public void test() {
        bowling.roll(0);
        assertThat(bowling.getScore(), is(0));
    }

    @Test
    public void strike_test() {
        bowling.roll(10);   // first frame
        bowling.roll(3);    // second frame - first roll
        bowling.roll(5);    // second frame - second roll
        assertThat(bowling.getScore(), is(26)); // 18 + (3 + 5)
    }

    @Test
    public void spare_test() {
        bowling.roll(5);
        bowling.roll(5);
        bowling.roll(3);
        assertThat(bowling.getScore(), is(16));
    }

    @Test
    public void perfectScore() {
        for (int i = 0; i < 12; i++) {
            bowling.roll(10);
        }
        assertThat(bowling.getScore(), is(300));
    }

    @Test
    public void four_score_for_each_roll() {
        for (int i = 0; i < 20; i++) {

            bowling.roll(4); // No strike, No spare
        }
        assertThat(bowling.getScore(), is(80));
        bowling.print();
    }
}
