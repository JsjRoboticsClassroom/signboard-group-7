package nyc.c4q.ac21.signboard;

import java.util.Random;

public class Main {
    /**
     * Draws a scene with a scrolling multicolor zig-zag ribbon.
     *
     * @param board     The board on which to draw.
     * @param numCycles The number of cycles to draw for.
     */

    //NOT USED
    public static void ribbonScene(SignBoard board, int numCycles) {
        int width = board.getWidth();
        int height = board.getHeight();
        for (int i = 0; i < numCycles; ++i) {
            SignBoard.Frame frame = board.newFrame();

            for (int x = -2; x < width / 2; ++x) {
                int y = (2 * height - 2 + x + i) % (2 * height - 2);
                if (y >= height)
                    y = 2 * height - y - 2;
                if (0 < x) {
                    frame.setYellow();
                    frame.write(x, y, "*");
                }
                if (0 < x + 1 && x + 1 < width / 2) {
                    frame.setGreen();
                    frame.write(x + 1, y, "*");
                }
                if (x + 2 < width / 2) {
                    frame.setRed();
                    frame.write(x + 2, y, "*");
                }
            }

            frame.finish(0.02);
        }
    }

    /**
     * Draws a scene with text scrolling across the screen..
     *
     * @param board The board on which to draw.
     * @param text  The text to scroll.
     */

    //ORIGINAL ::NOT USED :::
    public static void scrollTextScene(SignBoard board, String text) {
        int width = board.getWidth();
        int y = board.getHeight() / 2;
        for (int x = -text.length(); x <= width; ++x) {
            SignBoard.Frame frame = board.newFrame();

            if (x >= width)
                break;

            if (x < 0)
                // Scrolling on to the left side.
                frame.write(0, y, text.substring(-x));
            else if (x + text.length() <= width)
                // Fully on the board.
                frame.write(x, y, text);
            else
                // Scrolling off the board.
                frame.write(x, y, text.substring(0, width - x));

            frame.finish(0.02);
        }
    }

    public static void scrollingZombieAd(SignBoard board, String[] array, int cycles) {
        int width = board.getWidth();
        Random random = new Random();
        System.out.println(width);
        int y = board.getHeight() / 2;
        System.out.println(y);
        for (int i = -array[1].length(); i <= width * cycles; ++i) {
            SignBoard.Frame frame = board.newFrame();
            int j = 8;
            int x = Math.abs((i % (width*4/5)) - (width*4/10)) ;
            int color = random.nextInt(10);
            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setRed();
            else
                frame.setYellow();

            if (x >= width)
                break;

            if (x < 0) {
                // Scrolling on to the left side.

                frame.write(0, y - 4, array[j].substring(-x));
                frame.write(0, y - 3, array[j + 1].substring(-x));
                frame.write(0, y - 2, array[j + 2].substring(-x));
                frame.write(0, y - 1, array[j + 3].substring(-x));
                frame.write(0, y, array[j + 4].substring(-x));
                frame.write(0, y + 1, array[j + 5].substring(-x));
                frame.write(0, y + 2, array[j + 6].substring(-x));
                frame.write(0, y + 3, array[j + 7].substring(-x));
            } else if (x + array[1].length() <= width) {
                // Fully on the board.
                frame.write(x, y - 4, array[j]);
                frame.write(x, y - 3, array[j + 1]);
                frame.write(x, y - 2, array[j + 2]);
                frame.write(x, y - 1, array[j + 3]);
                frame.write(x, y, array[j + 4]);
                frame.write(x, y + 1, array[j + 5]);
                frame.write(x, y + 2, array[j + 6]);
                frame.write(x, y + 3, array[j + 7]);
            } else if (x + array[1].length() >= width) {
                // Scrolling off the board.
                frame.write(x, y - 4, array[j].substring(0, width - x));
                frame.write(x, y - 3, array[j + 1].substring(0, width - x));
                frame.write(x, y - 2, array[j + 2].substring(0, width - x));
                frame.write(x, y - 1, array[j + 3].substring(0, width - x));
                frame.write(x, y, array[j + 4].substring(0, width - x));
                frame.write(x, y + 1, array[j + 5].substring(0, width - x));
                frame.write(x, y + 2, array[j + 6].substring(0, width - x));
                frame.write(x, y + 3, array[j + 7].substring(0, width - x));
            }
            frame.finish(0.01);
        }
    }

    /**
     * Draws a scene which flashes the words "FRESH" and "HOT".
     *
     * @param board  The board on which to draw.
     * @param cycles The number of cycles to draw for.
     */
    public static void zombieHour(SignBoard board, int cycles) {
        Random random = new Random();
        int width = board.getWidth();
        Random place = new Random();
        int leftPosition = (width) / 4 - 15;
        int rightPosition = 3 * width / 4 - 27;
        int centerPosition = width / 2 - 10;
        int y = board.getHeight() / 2;

        for (int i = 0; i < cycles * 3; ++i) {
            SignBoard.Frame frame = board.newFrame();

            // Choose a color at random.
            int color = random.nextInt(4);
            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setRed();
            else if (color == 2)
                frame.setWhite();
            else
                frame.setYellow();
            // Write a word.
            if (i % 3 == 0) {

                frame.write(leftPosition, y - 4, "ZZZZZZZZ   OOOOOO   MMM    MMM  BBBBBBBB     IIIIIIIIII  EEEEEEEEEE  ");
                frame.write(leftPosition, y - 3, "ZZZZZZZZ  OOOOOOOO  MMMM  MMMM  BBBBBBBBBB   IIIIIIIIII  EEEEEEEEEE  ");
                frame.write(leftPosition, y - 2, "    ZZZ   OO    OO  MM MMMM MM  BB      BB       II      EEE         ");
                frame.write(leftPosition, y - 1, "   ZZZ    OO    OO  MM  MM  MM  BB      BB       II      EEEEEE      ");
                frame.write(leftPosition, y,     "  ZZZ     OO    OO  MM      MM  BBBBBBBBB        II      EEEEEE      ");
                frame.write(leftPosition, y + 1, " ZZZ      OO    OO  MM      MM  BB      BB       II      EEE         ");
                frame.write(leftPosition, y + 2, "ZZZZZZZZ  OOOOOOOO  MM      MM  BB      BB   IIIIIIIIII  EEEEEEEEEE  ");
                frame.write(leftPosition, y + 3, "ZZZZZZZZ   OOOOOO   MM      MM  BBBBBBBBB    IIIIIIIIII  EEEEEEEEEE  ");

            } else {

                frame.write(rightPosition, y - 4, Arrays.feast()[8]);
                frame.write(rightPosition, y - 3, Arrays.feast()[9]);
                frame.write(rightPosition, y - 2, Arrays.feast()[10]);
                frame.write(rightPosition, y - 1, Arrays.feast()[11]);
                frame.write(rightPosition, y,     Arrays.feast()[12]);
                frame.write(rightPosition, y + 1, Arrays.feast()[13]);
                frame.write(rightPosition, y + 2, Arrays.feast()[14]);
                frame.write(rightPosition, y + 3, Arrays.feast()[15]);

            }
            frame.finish(0.05);
        }
    }


    public static void verticalScrollS(SignBoard board, String[] array, int cycles) {
        Random random = new Random();
        int width = board.getWidth();
        //int leftPosition = (width / 2) - (40 * Math.cos(radians));
        int y = board.getHeight() / 2;
        for (int i = 0; i < (array.length - 8) * cycles; i++) {
            double radians = i % 6.28;
            int leftPosition = (int) ((width / 2) - ((width / 4) * Math.cos(radians)) - 35);
            SignBoard.Frame frame = board.newFrame();
            int j = i % (array.length - 8);

            int color = random.nextInt(4);
            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setRed();
            else if (color == 2)
                frame.setWhite();
            else
                frame.setYellow();


            frame.write(leftPosition, y - 4, array[j]);
            frame.write(leftPosition, y - 3, array[j + 1]);
            frame.write(leftPosition, y - 2, array[j + 2]);
            frame.write(leftPosition, y - 1, array[j + 3]);
            frame.write(leftPosition, y, array[j + 4]);
            frame.write(leftPosition, y + 1, array[j + 5]);
            frame.write(leftPosition, y + 2, array[j + 6]);
            frame.write(leftPosition, y + 3, array[j + 7]);

            frame.finish(0.02);
        }
    }

    public static void verticalScroll(SignBoard board, String[] array, int cycles) {
        Random random = new Random();
        SignBoard.Frame frame = board.newFrame();
        int width = board.getWidth();
        int leftPosition = (width / 4) - 10;
        int y = board.getHeight() / 2;

        int color = random.nextInt(4);

        if (color == 0)
            frame.setGreen();
        else if (color == 1)
            frame.setRed();
        else if (color == 2)
            frame.setYellow();
        else
            frame.setYellow();

        for (int i = 0; i < (array.length - 8) * cycles; i++) {
            frame = board.newFrame();
            int j = i % (array.length - 8);
            frame.setYellow();

            if (i > 50 && i % 2 == 0) {

                frame.write(leftPosition - 35, y - 4, Arrays.eat()[0]);
                frame.write(leftPosition - 35, y - 3, Arrays.eat()[1]);
                frame.write(leftPosition - 35, y - 2, Arrays.eat()[2]);
                frame.write(leftPosition - 35, y - 1, Arrays.eat()[3]);
                frame.write(leftPosition - 35, y,     Arrays.eat()[4]);
                frame.write(leftPosition - 35, y + 1, Arrays.eat()[5]);
                frame.write(leftPosition - 35, y + 2, Arrays.eat()[6]);
                frame.write(leftPosition - 35, y + 3, Arrays.eat()[7]);

            } else {

//                int color = random.nextInt(4);
//                if (color == 0)
//                    frame.setGreen();
//                else if (color == 1)
//                    frame.setRed();
//                else if (color == 2)
//                    frame.setWhite();
//                else
//                    frame.setYellow();


                frame.write(leftPosition + 10, y - 4, array[j]);
                frame.write(leftPosition + 10, y - 3, array[j + 1]);
                frame.write(leftPosition + 10, y - 2, array[j + 2]);
                frame.write(leftPosition + 10, y - 1, array[j + 3]);
                frame.write(leftPosition + 10, y    , array[j + 4]);
                frame.write(leftPosition + 10, y + 1, array[j + 5]);
                frame.write(leftPosition + 10, y + 2, array[j + 6]);
                frame.write(leftPosition + 10, y + 3, array[j + 7]);

            }
            frame.finish(0.03);

        }
    }

    public static void circles(SignBoard board, String[] array, int cycles) {
        Random random = new Random();
        int width = board.getWidth();
        //int leftPosition = (width / 2) - (40 * Math.cos(radians));
        int y = board.getHeight() / 2;
        for (int i = 0; i < (array.length - 8) * cycles; i++) {
            double radians = (i) * 6.28 / array.length - 8;
            int leftPosition = (int) ((width / 2) - (50 * Math.sin(radians/2)) - 35);
            SignBoard.Frame frame = board.newFrame();
            int j = (int) (((array.length - 8) / 2) * Math.sin(radians)) + ((array.length - 8) / 2);

            int color = random.nextInt(4);
            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setRed();
            else if (color == 2)
                frame.setWhite();
            else
                frame.setYellow();


            frame.write(leftPosition, y - 4, array[j]);
            frame.write(leftPosition, y - 3, array[j + 1]);
            frame.write(leftPosition, y - 2, array[j + 2]);
            frame.write(leftPosition, y - 1, array[j + 3]);
            frame.write(leftPosition, y,     array[j + 4]);
            frame.write(leftPosition, y + 1, array[j + 5]);
            frame.write(leftPosition, y + 2, array[j + 6]);
            frame.write(leftPosition, y + 3, array[j + 7]);

            frame.finish(0.02);
        }
    }


    public static void main(String[] args) {
        SignBoard signBoard = new SignBoard(8);

        // Run the sign board forever.
        while (true) {
            // NOT USED ribbonScene(signBoard, 48);
            zombieHour(signBoard, 24);
            verticalScroll(signBoard, Arrays.skullArray(),1);
            verticalScrollS(signBoard, Arrays.skullArray(), 1);
            scrollingZombieAd(signBoard, Arrays.freshHumans(), 3);
            circles(signBoard,Arrays.skullArray(),10);
            verticalScroll(signBoard, Arrays.asciigirl(),1);

        }
    }

}
