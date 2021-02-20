import java.util.ArrayList;
import java.util.List;

public class Frame_score {
    private List<Integer> scores;

    public Frame_score(int n){
        scores = new ArrayList<Integer>();

        for(int i=0;i<n;i++) {
            scores.add(0);
        }
    }

    public int getScore(int index) {
        return scores.get(index);
    }

    public void setScore(int index, int score) {
        scores.set(index, score);
    }

}
