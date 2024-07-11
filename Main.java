import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    public static void main(String[] args) {
        AtomicInteger valueOne = new AtomicInteger(0);
        AtomicInteger valueTwo = new AtomicInteger(0);
        AtomicInteger valueThree = new AtomicInteger(0);
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        Runnable logic = () -> {
            for (int i = 0; i < texts.length; i++) {
                String word = texts[i];
                int value = word.length();
                if (value == 3) {
                    valueOne.incrementAndGet();
                }
                if (value == 4) {
                    valueTwo.incrementAndGet();
                }
                if (value == 5) {
                    valueThree.incrementAndGet();
                }

            }
        };

        Thread threadOne = new Thread(logic);
        threadOne.start();
        Thread threadTwo = new Thread(logic);
        threadTwo.start();
        Thread threadThree = new Thread(logic);
        threadThree.start();
        System.out.println("Красивых слов с длиной 3: "+ valueOne);
        System.out.println("Красивых слов с длиной 4: "+ valueTwo);
        System.out.println("Красивых слов с длиной 5: "+ valueThree);

    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
