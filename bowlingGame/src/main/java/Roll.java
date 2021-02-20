import java.util.ArrayList;
import java.util.List;

public class Roll {
    private List<Integer> rolls;

    public Roll (int n) {
        rolls = new ArrayList<Integer>();

        for(int i = 0; i < n; i++)
            rolls.add(0);
    }

    public int getRoll(int index) {
        return rolls.get(index);
    }

    public void setRoll(int index, int pins) {
        rolls.set(index, pins);
    }

    public int length() {
        return rolls.size() - 1;
    }

}
