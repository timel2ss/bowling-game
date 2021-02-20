public class Bowling {

    boolean first = true;
    private int[] frames = new int[11];
    private int[] rolls = new int[21];
    private int[] frames_score = new int[11];
    private int index = 0;


    public int getScore() {
        int frame = 0;
        for (int i = 0; i < 20; i++) {
            if(frame==9) {
                frames[frame] += rolls[i];
                continue;
            }

            if(isStrike(rolls[i])) {
                first = false;
                frames[frame++] += 10 + rolls[i + 1] + rolls[i + 2];
                continue;
            }
            else if(isSpare(i)) {
                frames[frame++] += 10 + rolls[i + 2];
                i += 1;
                continue;
            }

            frames[frame++] += rolls[i] + rolls[i + 1];
            i += 1;
        }
        return sum();
    }

    private int sum() {
        int sum = 0;
        for (int i = 0; i < frames.length; i++) {
            sum += frames[i];
            if(i>=1)
                frames_score[i] = frames[i] + frames_score[i-1];
            else
                frames_score[i] = frames[i];
//            System.out.println(sum);
        }
        return sum;
    }

    private boolean isStrike(int pins) {
        return pins == 10;
    }

    private boolean isSpare(int i) {
        return rolls[i] + rolls[i + 1] == 10;
    }

    public void roll(int pins) {
        rolls[index++] = pins;
    }

    public void print() {
        for(int i=0;i<10;i++) {
            System.out.printf("%5d", i + 1);
        }

        System.out.println();

        for(int i=0;i<10;i++) {
            System.out.printf("%5d", frames_score[i]);
        }
    }
}
