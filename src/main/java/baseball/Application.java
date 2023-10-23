package baseball;

import baseball.dto.StrikeBallCnt;
import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Application {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final String NOT_VALID_NUMBER = "3자리의 숫자만 입력 가능합니다.";

    public static void main(String[] args) {

        try {
            baseballGame();
        }catch (IOException | IllegalArgumentException ioException){
            ioException.printStackTrace();
        }

    }

    private static void baseballGame() throws IOException {
        List<Integer> answerNumber = createAnswerNumber();
        System.out.println("숫자 야구 게임을 시작합니다.");

        String input;
        while (true){
            System.out.print("숫자를 입력해주세요 : ");
            input = br.readLine();
            checkValidNumber(input);
            StrikeBallCnt result = getResult(input, answerNumber);
        }
    }

    private static StrikeBallCnt getResult(String input, List<Integer> answer) {
        int strikeCnt = 0;
        int ballCnt = 0;

        char[] chars = input.toCharArray();
        for (int i = 0; i < 3; i++) {
            if(chars[i] - '0' == answer.get(i)){
                strikeCnt += 1;
            }else if(answer.contains(chars[i] - '0')){
                ballCnt += 1;
            }
        }

        return new StrikeBallCnt(strikeCnt, ballCnt);

    }

    private static void checkValidNumber(String input) {
        if(input.length() != 3){
            throw new IllegalArgumentException(NOT_VALID_NUMBER);
        }
        Integer.parseInt(input);
    }

    private static List<Integer> createAnswerNumber() {
        List<Integer> answer = new ArrayList<>();
        while (answer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!answer.contains(randomNumber)) {
                answer.add(randomNumber);
            }
        }
        return answer;
    }

}
