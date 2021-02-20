import java.util.ArrayList;
import java.util.List;

public class Frame {
    private List<Integer> frames;

    public Frame(int frameCount) {
        frames = new ArrayList<Integer>();

        for (int i = 0; i < frameCount; i++) {
            frames.add(0);
        }
    }

    public int getFrameScore(int index) {
        return frames.get(index);
    }

    public void setFrameScore(int index, int score) {
        frames.set(index, score);
    }

    public int length() {
        return frames.size();
    }

    public void addFrameScore(int frameIndex, int pins) {
        frames.set(frameIndex, frames.get(frameIndex) + pins);
    }
}
