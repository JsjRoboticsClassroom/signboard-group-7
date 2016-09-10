package nyc.c4q.shawn;

import nyc.c4q.ac21.signboard.SignBoard;

import java.util.Random;

import static java.lang.String.valueOf;

public class Main {
    /**
     * Draws a scene with a scrolling multicolor zig-zag ribbon.
     * @param board
     *   The board on which to draw.
     * @param numCycles
     *   The number of cycles to draw for.
     */
    public static void ribbonScene(SignBoard board, int numCycles) {
        int width = board.getWidth();
        int height = board.getHeight();
        for (int i = 0; i < numCycles; ++i) {
            SignBoard.Frame frame = board.newFrame();

            for (int x = -2; x < width; ++x) {
                int y = (2 * height - 2 + x + i) % (2 * height - 2);
                if (y >= height)
                    y = 2 * height - y - 2;
                if (0 < x) {
                    frame.setYellow();
                    frame.write(x, y, "*");
                }
                if (0 < x + 1 && x + 1 < width) {
                    frame.setGreen();
                    frame.write(x + 1, y, "*");
                }
                if (x + 2 < width) {
                    frame.setRed();
                    frame.write(x + 2, y, "*");
                }
            }

            frame.finish(.02);
        }
    }

    /**
     * Draws a scene with text scrolling across the screen..
     * @param board
     *   The board on which to draw.
     * @param text.
     *   The text to scroll.
     */

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

            frame.finish(0.025);
        }
    }

    public static void myMenu (SignBoard board) {
        Random random = new Random ();
        SignBoard.Frame frame = board.newFrame();
        String theCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ,./<>?1234567890-=`~!@#$%^&*()_+`¡™£¢∞§¶•ªº–≠Œ∑´®†Á¨ˆØ∏“‘«ÅÍÎÏ©ÓÔ˚Ò…ÆΩ≈Ç√ı˜Â≤≥Â[]{}|";
        boolean insideBoarder = false;

        int width = board.getWidth();
        int height = board.getHeight();
        double centerWidth = Math.floor((width/2));

        do {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if ( (x > Math.floor(width*.25)) && (x < Math.floor(width*.75)+1) && (y > height*.25) && (y < height * .75) ){
                        insideBoarder = true;
                    }else
                        insideBoarder = false;


                    if  (((y == Math.floor(height*.25)) || (y ==  Math.floor(height * .75)-1))&& (x > width*.25) && (x < width*.75)) {
                        frame.write(x, y, "=");
                    }else if (((x == Math.floor(width*.25)+1) || (x == Math.floor(width*.75))) && (y > height*.25) && (y < height * .75)){
                        frame.write(x,y,"|");
                    } else if(insideBoarder){

                        String tempString = y == (Math.floor(height/2)-1) ? "BRAINS OVER PLASMA $14.99 ....MMMMMMMM!!!!!!" : "Such Delicious! Very Zombie! Much Tasty! !WoW!";

                        int temp = 0;
                        if (  x + (tempString.length()/2) == centerWidth + 1 ){
                            while (temp < tempString.length()) {
                                frame.write(x, y, tempString.charAt(temp) + "");
                                x++;
                                temp++;
                                try {
                                    Thread.sleep(25);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else
                            frame.write(x,y," ");
                    }
                    else
                        frame.write(x, y, theCharacters.charAt(random.nextInt(theCharacters.length())) + "");
                }
            }
        } while (false);


        frame.finish(.025);
        double frameArea = height * width;

        do {
            for(int y = 0; y < height; y++){
                for (int x = 0; x < width; x++){

                }



            }


            while
        }


    }
    /**
     * Draws a scene which flashes the words "FRESH" and "HOT".
     * @param board
     *   The board on which to draw.
     * @param cycles
     *   The number of cycles to draw for.
     */
    public static void flashFreshHotScene(SignBoard board, int cycles) {
        Random random = new Random();
        int width = board.getWidth();
        int leftPosition = width / 4 - 12;
        int rightPosition = 3 * width / 4 - 7;
        int y = board.getHeight() / 2;
//        int y = board.getHeight()-3;
        for (int i = 0; i < cycles * 2; ++i) {
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
            if (i % 2 == 0) {

                frame.write(leftPosition, y - 2, "0000 RRR  EEEE  SSS H  H");
                frame.write(leftPosition, y - 1, "F    R RR E    SS   H  H");
                frame.write(leftPosition, y    , "FFR  RRR  EEE   SS  HHHH");
                frame.write(leftPosition, y + 1, "F    R R  E      SS H  H");
                frame.write(leftPosition, y + 2, "F    R  R EEEE SSS  H  H");

                //DELETE CODE UNDERNEATH.
            }
            else {
                frame.write(rightPosition, y - 2, "H  H  XX  TTTT");
                frame.write(rightPosition, y - 1, "H  H O  O  TT ");
                frame.write(rightPosition, y    , "HHHH O  O  TT ");
                frame.write(rightPosition, y + 1, "H  H O  O  TT ");
                frame.write(rightPosition, y + 2, "H  H  OO   TT ");
            }

            frame.finish(0.25);
        }
    }

    public static void main(String[] args) {
        SignBoard signBoard = new SignBoard(8);
        myMenu(signBoard);
        // Run the sign board forever.
//        while (true) {
//            ribbonScene(signBoard, 48);
//            scrollTextScene(signBoard, "### FALAFEL ###");
//            ribbonScene(signBoard, 48);
//            flashFreshHotScene(signBoard, 8);
//            myMenu(signBoard);
//        }
    }
}
