public class Bowling {
    private Frame frame = new Frame(11);
    private Roll roll = new Roll(21);
    private Frame_score frames_score = new Frame_score(11);
    private Index index = Index.create(0);

    public int getScore() {
        int frameIndex = 0;
        for (int i = 0; i < roll.length(); i++) {
            if(frameIndex == 9) {
                frame.addFrameScore(frameIndex, roll.getRoll(i));
                continue;
            }

            if(isStrike(i)) {
                frame.setFrameScore(frameIndex++, scoreForStrike(i));
                continue;
            }
            else if(isSpare(i)) {
                frame.setFrameScore(frameIndex++, scoreForSpare(i));
                i += 1;
                continue;
            }

            frame.setFrameScore(frameIndex++, scoreForNormal(i));
            i += 1;
        }
        return sum();
    }

    private int scoreForNormal(int i) {
        return roll.getRoll(i) + roll.getRoll(i+1);
    }

    private int scoreForSpare(int i) {
        return 10 + roll.getRoll(i + 2);
    }

    private int scoreForStrike(int i) {
        return 10 + roll.getRoll(i + 1) + roll.getRoll(i + 2);
    }

    private int sum() {
        int sum = 0;
        for (int i = 0; i < frame.length(); i++) {
            sum += frame.getFrameScore(i);
            if(i >= 1){
                frames_score.setScore(i, frame.getFrameScore(i) + frames_score.getScore(i - 1));
                continue;
            }
            frames_score.setScore(i, frame.getFrameScore(i));
        }
        return sum;
    }

    private boolean isStrike(int i) {
        return roll.getRoll(i) == 10;
    }

    private boolean isSpare(int i) {
        return roll.getRoll(i) + roll.getRoll(i + 1) == 10;
    }

    public void roll(int pins) {
        roll.setRoll(index.get(), pins);
    }

    public void print() {
        for(int i = 0; i < frame.length(); i++) {
            System.out.printf("%5d", i + 1);
        }

        System.out.println();

        for(int i = 0; i < frame.length(); i++) {
            System.out.printf("%5d", frames_score.getScore(i));
        }
    }
}
