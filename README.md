## Bowling Game (볼링게임)

### 볼링 점수판 구현
볼링을 칠 때 점수를 계산한다.
<br>

#### Scoring Rules
Strike <br>
If you knock down all 10 pins in the first shot of a frame, you get a strike.
How to score: A strike earns 10 points plus the sum of your next two shots.

Spare <br>
If you knock down all 10 pins using both shots of a frame, you get a spare.
How to score: A spare earns 10 points plus the sum of your next one shot.

Open Frame <br>
If you do not knock down all 10 pins using both shots of your frame (9 or fewer pins knocked down), you have an open frame.
How to score: An open frame only earns the number of pins knocked down.

The 10th Frame <br>
The 10th frame is a bit different:
If you roll a strike in the first shot of the 10th frame, you get 2 more shots.
If you roll a spare in the first two shots of the 10th frame, you get 1 more shot.
If you leave the 10th frame open after two shots, the game is over and you do not get an additional shot.
How to Score: The score for the 10th frame is the total number of pins knocked down in the 10th frame.

## 볼링 class
```java
public class Bowling {
    private Frame frame = new Frame(11);
    private Roll roll = new Roll(21);
    private Frame_score frames_score = new Frame_score(11);
    private Index index = Index.create(0);
}
```

Roll - 한번 공을 굴릴 때 핀을 넘어 뜨리는 개수 <br>
Frame - 각 프레임에 넘어뜨린 핀의 개수 (스트라이크 && 스페어 점수 합산) <br>
Frame_score - 현재 프레임까지의 각 프레임마다 얻은 점수들의 합 <br>
Index - index를 표시 <br>

## score 계산 함수들
```java
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
```

private int scoreForNormal(int i) - 스트라이크, 스페어가 아닌 일반적인 경우 점수를 계산한다. <br>
private int scoreForSpare(int i) - 스페어인 경우 점수를 계산한다. <br>
private int scoreForStrike(int i) - 스트라이크일 경우 점수를 계산한다. <br>
private int sum() - 각각 프레임마다의 점수, 각 프레임까지의 점수를 계산한다. <br>

## 테스트 코드
```java
    @Test
    public void roll_test() {
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
    public void perfect_score() {
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

    @Test
    public void all_zero_score() {
        for(int i = 0; i < 20; i++) {
            bowling.roll(0);
        }
        assertThat(bowling.getScore(), is(0));
    }
```


public void roll_test() - 0점 일 때 테스트가 잘 작동하는지 테스트 <br>
public void strike_test() - 스트라이크일 경우를 테스트한다. <br>
public void spare_test() - 스페어일 경우를 테스트한다. <br>
public void perfect_score() - 모두 스트라이크일 경우를 테스트 한다. <br>
public void four_score_for_each_roll() - 4점만 쳤을 일반적인 경우를 테스트 한다. <br>
public void all_zero_score() - 모두 0점일 경우를 테스트 한다. <br>



## 개발 과정

테스트 코드를 작성하면서 코드를 작성했다.
먼저 어떻게 볼링 게임이 진행되는지 생각했는데
Roll에 처음부터 끝까지 핀을 어떻게 치는지를 넣으면
알아서 점수가 계산되도록 코드를 작성했다.






