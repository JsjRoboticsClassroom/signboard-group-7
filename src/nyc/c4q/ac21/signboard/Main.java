package nyc.c4q.ac21.signboard;

import java.util.Random;

public class Main {
    /**
     * Draws a scene with a scrolling multicolor zig-zag ribbon.
     *
     * @param board     The board on which to draw.
     * @param numCycles The number of cycles to draw for.
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

            frame.finish(0.01);
        }
    }

    /**
     * Draws a scene with text scrolling across the screen..
     *
     * @param board The board on which to draw.
     * @param text  The text to scroll.
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

            frame.finish(0.02);
        }
    }

    /**
     * Draws a scene which flashes the words "FRESH" and "HOT".
     *
     * @param board  The board on which to draw.
     * @param cycles The number of cycles to draw for.
     */
    public static void flashFreshHotScene(SignBoard board, int cycles) {
        Random random = new Random();
        int width = board.getWidth();
        int leftPosition = width / 4 - 12;
        int rightPosition = 3 * width / 4 - 7;
        int y = board.getHeight() / 2;

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
                frame.write(leftPosition, y - 2, "FFFF RRR  EEEE  SSS H  H");
                frame.write(leftPosition, y - 1, "F    R RR E    SS   H  H");
                frame.write(leftPosition, y, "FFR  RRR  EEE   SS  HHHH");
                frame.write(leftPosition, y + 1, "F    R R  E      SS H  H");
                frame.write(leftPosition, y + 2, "F    R  R EEEE SSS  H  H");
            } else {
                frame.write(rightPosition, y - 2, "H  H  OO  TTTT");
                frame.write(rightPosition, y - 1, "H  H O  O  TT ");
                frame.write(rightPosition, y, "HHHH O  O  TT ");
                frame.write(rightPosition, y + 1, "H  H O  O  TT ");
                frame.write(rightPosition, y + 2, "H  H  OO   TT ");
            }

            frame.finish(0.25);
        }
    }

    public static void zombieWalk(SignBoard board, int cycles) {
        int width = board.getWidth();
        int leftPosition = width / 15 - 5;
        int middlePosition = width / 4 - 7;
        int rightPosition = width / 2 + 10;
        int y = board.getHeight() / 2;

        for (int i = 0; i < cycles * 2; i++) {
            SignBoard.Frame frame = board.newFrame();
            frame.setWhite();
            frame.write(leftPosition, y - 1, "    +            +     ");
            frame.write(leftPosition, y, "  .-:-.        .-:-.    ");
            frame.write(leftPosition, y + 1, " / RIP \\      / RIP \\  ");
            frame.write(leftPosition, y + 2, " |     |      |     |   ");
            frame.write(leftPosition, y + 3, "\\\\     |//   \\\\     |//");

            frame.setRed();
            frame.write(middlePosition + i, y - 4, "dMM+                 dMM+");
            frame.write(middlePosition + i, y - 3, " :mMh`                :mMh`");
            frame.write(middlePosition + i, y - 2, " +zxzxzxmho/          +zxzxzmdmho/");
            frame.write(middlePosition + i, y - 1, " :zxzdhyyos:          :zxzdhyyos:");
            frame.write(middlePosition + i, y, "  hMMMo                hMMMo");
            if (i % 2 == 0) {
                frame.write(middlePosition + i, y + 1, "  sMo MMo              sMo MMo");
                frame.write(middlePosition + i, y + 2, "   ym` yMM+             ym` yMM+");
                frame.write(middlePosition + i, y + 3, "   dm:  os-             dm:  os-");
            } else {
                frame.write(middlePosition + i, y + 1, "  sMo  MMo             sMo  MMo");
                frame.write(middlePosition + i, y + 2, "   ym`  yMM+            ym`  yMM+");
                frame.write(middlePosition + i, y + 3, "    dm:  os-             dm:  os-");
            }

            if (i % 2 == 0) {
                frame.setGreen();
                frame.write(rightPosition, y - 4, "        ______________________");
                frame.write(rightPosition, y - 3, "       |                      |");
                frame.write(rightPosition, y - 2, "       |  _______________   __|");
                frame.write(rightPosition, y - 1, "       | |               |  |----.");
                frame.write(rightPosition, y, "       | |_______________|  | |O,|____");
                frame.write(rightPosition, y + 1, "  @    |  .-.          .-.  | -  .-.  |");
                frame.write(rightPosition, y + 2, "  `@@ =(_| @ |________| @ |_|___| @ |_) ");
                frame.write(rightPosition, y + 3, "          `-'          '-'       `-'");
            } else {
                frame.setYellow();
                frame.write(rightPosition, y - 3, "        ______________________");
                frame.write(rightPosition, y - 2, "       |                      |");
                frame.write(rightPosition, y - 1, "       |  _______________   __|");
                frame.write(rightPosition, y, "       | |               |  |----.");
                frame.write(rightPosition, y + 1, "       | |_______________|  | |O,|____");
                frame.write(rightPosition, y + 2, "  @    |  .-.          .-.  | -  .-.  |");
                frame.write(rightPosition, y + 3, "  `@@ =(_| @ |________| @ |_|___| @ |_) ");
            }
            frame.finish(0.25);
        }
    }

    public static void scrollTruck(SignBoard board) {


        String truck1 = "        ______________________";
        String truck2 = "       |                      |";
        String truck3 = "       |  _______________   __|";
        String truck4 = "       | |               |  |----.";
        String truck5 = "       | |_______________|  | |O,|____";
        String truck6 = "  @    |  .-.          .-.  | -  .-.  |";
        String truck7 = "  `@@ =(_| @ |________| @ |_|___| @ |_) ";
        String truck8 = "          `-'          '-'       `-'";

        int width = board.getWidth();
        int y = board.getHeight() / 2;
        for (int x = -truck7.length(); x <= width; ++x) {
            SignBoard.Frame frame = board.newFrame();

            if (x >= width)
                break;

            if (x < 0) {
                // Scrolling from the left side.
                frame.write(0, y, truck1.substring(-x));
                frame.write(0, y, truck2.substring(-x));
                frame.write(0, y, truck3.substring(-x));
                frame.write(0, y, truck4.substring(-x));
                frame.write(0, y, truck5.substring(-x));
                frame.write(0, y, truck6.substring(-x));
                frame.write(0, y, truck7.substring(-x));
                frame.write(0, y, truck8.substring(-x));
            } else if (x + truck7.length() <= width) {
                // Fully on the board.
                frame.write(x, y, truck1);
                frame.write(x, y, truck2);
                frame.write(x, y, truck3);
                frame.write(x, y, truck4);
                frame.write(x, y, truck5);
                frame.write(x, y, truck6);
                frame.write(x, y, truck7);
                frame.write(x, y, truck8);
            } else
                // Scrolling off the board.
                frame.write(x, y, truck1.substring(0, width - x));
            frame.write(x, y, truck2.substring(0, width - x));
            frame.write(x, y, truck3.substring(0, width - x));
            frame.write(x, y, truck4.substring(0, width - x));
            frame.write(x, y, truck5.substring(0, width - x));
            frame.write(x, y, truck6.substring(0, width - x));
            frame.write(x, y, truck7.substring(0, width - x));
            frame.write(x, y, truck8.substring(0, width - x));

            frame.finish(0.02);
        }
    }

    public static void truckDrive(SignBoard board, int cycles) {
        int width = board.getWidth();
        int leftPosition = width / 15 - 5;
        int y = board.getHeight() / 2;

        for (int i = 0; i < cycles * 2; i++) {
            SignBoard.Frame frame = board.newFrame();

            frame.setGreen();
            frame.write(leftPosition + i, y - 4, "        ______________________");
            frame.write(leftPosition + i, y - 3, "       |                      |");
            frame.write(leftPosition + i, y - 2, "       |  _______________   __|");
            frame.write(leftPosition + i, y - 1, "       | |               |  |----.");
            frame.write(leftPosition + i, y, "       | |_______________|  | |O,|____");
            frame.write(leftPosition + i, y + 1, "  @    |  .-.          .-.  | -  .-.  |");
            frame.write(leftPosition + i, y + 2, "  `@@ =(_| @ |________| @ |_|___| @ |_) ");
            frame.write(leftPosition + i, y + 3, "          `-'          '-'       `-'");

            frame.finish(0.02);

        }
    }

    public static void myMenu(SignBoard board) {
        Random random = new Random();
        SignBoard.Frame frame = board.newFrame();
        String theCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ,./<>?1234567890-=`~!@#$%^&*()_+`¡™£¢∞§¶•ªº–≠Œ∑´®†Á¨ˆØ∏'«ÅÍÎÏ©ÓÔ˚Ò…ÆΩ≈Ç√ı˜Â≤≥Â[]{}|";
        boolean insideBoarder = false;

        int width = board.getWidth();
        int height = board.getHeight();
        double centerWidth = Math.floor((width / 2));

        do {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if ((x > Math.floor(width * .25)) && (x < Math.floor(width * .75) + 1) && (y > height * .25) && (y < height * .75)) {
                        insideBoarder = true;
                    } else
                        insideBoarder = false;


                    if (((y == Math.floor(height * .25)) || (y == Math.floor(height * .75) - 1)) && (x > width * .25) && (x < width * .75)) {
                        frame.write(x, y, "=");
                    } else if (((x == Math.floor(width * .25) + 1) || (x == Math.floor(width * .75))) && (y > height * .25) && (y < height * .75)) {
                        frame.write(x, y, "|");
                    } else if (insideBoarder) {

                        String tempString = y == (Math.floor(height / 2) - 1) ? "BRAINS OVER PLASMA $14.99 ....MMMMMMMM!!!!!!" : "Such Delicious! Very Zombie! Much Tasty! !WoW!";

                        int temp = 0;
                        if (x + (tempString.length() / 2) == centerWidth + 1) {
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
                        } else
                            frame.write(x, y, " ");
                    } else
                        frame.write(x, y, theCharacters.charAt(random.nextInt(theCharacters.length())) + "");
                }
            }
        } while (false);


        frame.finish(.025);

    }

    public static void dancingBrains(SignBoard board, int cycles) {
        Random random = new Random();
        int width = board.getWidth();
        int height = board.getHeight();
        int firstPosition = width / 4;
        //   int secondPosition = firstPosition + 20;
//        int thirdPosition = secondPosition + 16;
//        int fourthPosition = thirdPosition + 20;
//        int fifthPosition = fourthPosition + 16;


        for (int i = 0; i < 7; i++) {
            SignBoard.Frame frame = board.newFrame();

            int color = random.nextInt(4);
            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setRed();
            else if (color == 2)
                frame.setWhite();
            else
                frame.setYellow();

            switch (i) {
                case 2:
                case 4:
                case 6:
                    frame.write(firstPosition, 0, "                    d88b.d88b                       d88b.d88b                   ");
                    frame.write(firstPosition, 1, "                   88888888888                     88888888888                  ");
                    frame.write(firstPosition, 2, "   _.—~~(~~-_.     `Y8888888Y'     _.—~~(~~-_.     `Y8888888Y'     _.—~~(~~-_.  ");
                    frame.write(firstPosition, 3, " ,) -~~-(,-' )_      `Y888Y'      ,) -~~-(,-' )_     `Y888Y'      ,) -~~-(,-' )_");
                    frame.write(firstPosition, 4, "( `-,_..`.’_,.)        `Y'       ( `-,_..`.’_,.)       `Y'       ( `-,_..`.’_,.)");
                    frame.write(firstPosition, 5, " `~-^(_;-,((())                   `~-^(_;-,((())                  `~-^(_;-,((())");
                    frame.write(firstPosition, 6, "         `Jj }                            `Jj }                           `Jj } ");
                    frame.write(firstPosition, 7, "            {}                               {}                              {} ");
                    break;

                case 1:
                case 3:
                case 5:
                    frame.write(firstPosition, 0, "   _.—~~(~~-_.                     _.—~~(~~-_.                      _.—~~(~~-_. ");
                    frame.write(firstPosition, 1, " ,) -~~-(,-' )_                   ,) -~~-(,-' )_                  ,) -~~-(,-' )_");
                    frame.write(firstPosition, 2, "( `-,_..`.’_,.)                  ( `-,_..`.’_,.)                 ( `-,_..`.’_,.)");
                    frame.write(firstPosition, 3, " `~-^(_;-,((())     d88b.d88b     `~-^(_;-,((())    d88b.d88b     `~-^(_;-,((())");
                    frame.write(firstPosition, 4, "         `Jj }     88888888888           ` Jj }    88888888888             Jj } ");
                    frame.write(firstPosition, 5, "            {}     `Y8888888Y'               {}    `Y8888888Y'               {} ");
                    frame.write(firstPosition, 6, "                     `Y888Y'                         `Y888Y'                    ");
                    frame.write(firstPosition, 7, "                       `Y'                             `Y'                      ");
                    break;


            }

            frame.finish(0.25);

        }

        for (int i = 7; i < 14; i++) {
            SignBoard.Frame frame = board.newFrame();
            frame.setRed();
            switch (i) {
                case 7:
                    frame.write(firstPosition, 0, "                    d88b.d88b                       d88b.d88b                  @");
                    frame.write(firstPosition, 1, "@                  88888888888                     88888888888                  ");
                    frame.write(firstPosition, 2, "   _.—~~(~~-_.     `Y8888888Y'     _.—~~(~~-_.     `Y8888888Y'     _.—~~(~~-_. @");
                    frame.write(firstPosition, 3, "@,) -~~-(,-' )_      `Y888Y'      ,) -~~-(,-' )_     `Y888Y'      ,) -~~-(,-' )_");
                    frame.write(firstPosition, 4, "( `-,_..`.’_,.)        `Y'       ( `-,_..`.’_,.)       `Y'       ( `-,_..`.’_,.@");
                    frame.write(firstPosition, 5, "@`~-^(_;-,((())                   `~-^(_;-,((())                  `~-^(_;-,((())");
                    frame.write(firstPosition, 6, "         `Jj }                            `Jj }                           `Jj }@");
                    frame.write(firstPosition, 7, "@           {}                               {}                              {} ");
                    break;
                case 8:
                    frame.write(firstPosition, 0, "@@@@                d88b.d88b                       d88b.d88b             @@@@@@");
                    frame.write(firstPosition, 1, "@@@@@@             88888888888                     88888888888              @@@@");
                    frame.write(firstPosition, 2, "@@@@.—~~(~~-_.     `Y8888888Y'     _.—~~(~~-_.     `Y8888888Y'     _.—~~(~@@@@@@");
                    frame.write(firstPosition, 3, "@@@@@@~-(,-' )_      `Y888Y'      ,) -~~-(,-' )_     `Y888Y'      ,) -~~-(,-@@@@");
                    frame.write(firstPosition, 4, "@@@@,_..`.’_,.)        `Y'       ( `-,_..`.’_,.)       `Y'       ( `-,_..`@@@@@@");
                    frame.write(firstPosition, 5, "@@@@@@_;-,((())                   `~-^(_;-,((())                  `~-^(_;-,(@@@@");
                    frame.write(firstPosition, 6, "@@@@     `Jj }                            `Jj }                           @@@@@@");
                    frame.write(firstPosition, 7, "@@@@@@      {}                               {}                             @@@@");
                    break;
                case 9:
                    frame.write(firstPosition, 0, "@@@@@@@@@@          d88b.d88b                       d88b.d88b       @@@@@@@@@@@@");
                    frame.write(firstPosition, 1, "@@@@@@@@@@@@       88888888888                     88888888888        @@@@@@@@@@");
                    frame.write(firstPosition, 2, "@@@@@@@@@@~-_.     `Y8888888Y'     _.—~~(~~-_.     `Y8888888Y'     _@@@@@@@@@@@@");
                    frame.write(firstPosition, 3, "@@@@@@@@@@@@ )_      `Y888Y'      ,) -~~-(,-' )_     `Y888Y'      ,) -@@@@@@@@@@");
                    frame.write(firstPosition, 4, "@@@@@@@@@@’_,.)        `Y'       ( `-,_..`.’_,.)       `Y'       ( `@@@@@@@@@@@@");
                    frame.write(firstPosition, 5, "@@@@@@@@@@@@())                   `~-^(_;-,((())                  `~-^@@@@@@@@@@");
                    frame.write(firstPosition, 6, "@@@@@@@@@@Jj }                            `Jj }                     @@@@@@@@@@@@");
                    frame.write(firstPosition, 7, "@@@@@@@@@@@@{}                               {}                       @@@@@@@@@@");
                    break;
                case 10:
                    frame.write(firstPosition, 0, "@@@@@@@@@@@@@@@@@@  d88b.d88b                       d88b.d88@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 1, "@@@@@@@@@@@@@@@@@@@@8888888888                     88888888888@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 2, "@@@@@@@@@@@@@@@@@@ `Y8888888Y'     _.—~~(~~-_.     `Y8888888@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 3, "@@@@@@@@@@@@@@@@@@@@ `Y888Y'      ,) -~~-(,-' )_     `Y888Y'  @@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 4, "@@@@@@@@@@@@@@@@@@     `Y'       ( `-,_..`.’_,.)       `Y'  @@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 5, "@@@@@@@@@@@@@@@@@@@@              `~-^(_;-,((())              @@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 6, "@@@@@@@@@@@@@@@@@@                        `Jj }             @@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 7, "@@@@@@@@@@@@@@@@@@@@                         {}               @@@@@@@@@@@@@@@@@@");
                    break;

                case 11:
                    frame.write(firstPosition, 0, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@b                     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 1, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                     8@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 2, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@Y'     _.—~~(~~-_.    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 3, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    ,) -~~-(,-' )_    @@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 4, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@     ( `-,_..`.’_,.)  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 5, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    `~-^(_;-,((())    @@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 6, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@              `Jj }   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 7, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@               {}     @@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    break;

                case 12:
                    frame.write(firstPosition, 0, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 1, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 2, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 3, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 4, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 5, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 6, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 7, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    break;
                case 13:
                    frame.write(firstPosition, 0, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    frame.write(firstPosition, 1, "@@@@@@       @@@@@@       @@@@@@@@@@   @@@@@@@  @@@@@  @@@@@@  @@@@@     @@@@@@@");
                    frame.write(firstPosition, 2, "@@@@@@  @@@  @@@@@@  @@@@  @@@@@@@@  @  @@@@@@  @@@@@    @@@@  @@@@  @@@  @@@@@@");
                    frame.write(firstPosition, 3, "@@@@@@      @@@@@@@      @@@@@@@@@  @@@  @@@@@  @@@@@  @@  @@  @@@@   @@@@@@@@@@");
                    frame.write(firstPosition, 4, "@@@@@@       @@@@@@  @@@  @@@@@@@@       @@@@@  @@@@@  @@@  @  @@@@@@   @@@@@@@@");
                    frame.write(firstPosition, 5, "@@@@@@  @@@   @@@@@  @@@@  @@@@@@  @@@@@  @@@@  @@@@@  @@@@    @@@  @@@   @@@@@@");
                    frame.write(firstPosition, 6, "@@@@@@       @@@@@@  @@@@@  @@@@  @@@@@@@  @@@  @@@@@  @@@@@@  @@@@@     @@@@@@@");
                    frame.write(firstPosition, 7, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    break;

            }

            frame.finish(0.15);

        }

        for (int i = 15; i < cycles; i++) {

            SignBoard.Frame frame = board.newFrame();
            int color = random.nextInt(4);
            int position = random.nextInt(width - 80);
            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setRed();
            else if (color == 2)
                frame.setWhite();
            else
                frame.setYellow();

            frame.write(position, 0, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            frame.write(position, 1, "@@@@@@       @@@@@@       @@@@@@@@@@   @@@@@@@  @@@@@  @@@@@@  @@@@@     @@@@@@@");
            frame.write(position, 2, "@@@@@@  @@@  @@@@@@  @@@@  @@@@@@@@  @  @@@@@@  @@@@@    @@@@  @@@@  @@@  @@@@@@");
            frame.write(position, 3, "@@@@@@      @@@@@@@      @@@@@@@@@  @@@  @@@@@  @@@@@  @@  @@  @@@@   @@@@@@@@@@");
            frame.write(position, 4, "@@@@@@       @@@@@@  @@@  @@@@@@@@       @@@@@  @@@@@  @@@  @  @@@@@@   @@@@@@@@");
            frame.write(position, 5, "@@@@@@  @@@   @@@@@  @@@@  @@@@@@  @@@@@  @@@@  @@@@@  @@@@    @@@  @@@   @@@@@@");
            frame.write(position, 6, "@@@@@@       @@@@@@  @@@@@  @@@@  @@@@@@@  @@@  @@@@@  @@@@@@  @@@@@     @@@@@@@");
            frame.write(position, 7, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            frame.finish(0.2);
        }

    }

    public static void scrollingColdPressed(SignBoard board) {

        String row1 = "▄████ █████ ██    ████▄     ████▄ █████ ████ ▄██▄ ▄██▄ ████ ████▄";
        String row2 = "██    █   █ ██    █   █     ██  █ ██  █ ██   █▄▄  █▄▄  ██   █   █";
        String row3 = "██    █   █ ██    █   █     ████  ████  ██▀  ▄ ▀█ ▄ ▀█ ██▀  █   █";
        String row4 = "▀████ █████ █████ ████▀     ██    ██  █ ████ ▀██▀ ▀██▀ ████ ████▀";

        String row5 = "████▄ ██    █████ █████ ████▄                                    ";
        String row6 = "█▄▄▄▀ ██    █   █ █   █ █   █                                    ";
        String row7 = "█   █ ██    █   █ █   █ █   █                                    ";
        String row8 = "████▀ █████ █████ █████ ████▀                                    ";

        int j = 0;
        int p = 0;

        Random random = new Random();
        int width = board.getWidth();
        int y = board.getHeight() / 2;

        for (int x = -row1.length(); x <= width; ++x) {
            SignBoard.Frame frame = board.newFrame();
            int color = random.nextInt(4);
            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setRed();
            else if (color == 2)
                frame.setWhite();
            else
                frame.setYellow();

            if (x >= width)
                break;

            if (x < 0) {
                frame.write(0, y - 4, row1.substring(-x));
                frame.write(0, y - 3, row2.substring(-x));
                frame.write(0, y - 2, row3.substring(-x));
                frame.write(0, y - 1, row4.substring(-x));
                frame.write(width, y, row5.substring(0, j));
                frame.write(width, y + 1, row6.substring(0, j));
                frame.write(width, y + 2, row7.substring(0, j));
                frame.write(width, y + 3, row8.substring(0, j));
                j++;

            } else if (x + row1.length() <= width) {
                // Fully on the board.
                frame.write(x, y - 4, row1);
                frame.write(x, y - 3, row2);
                frame.write(x, y - 2, row3);
                frame.write(x, y - 1, row4);

                frame.write(width - j, y, row5);
                frame.write(width - j, y + 1, row6);
                frame.write(width - j, y + 2, row7);
                frame.write(width - j, y + 3, row8);
                j++;

            } else {
                // Scrolling off the board.
                frame.write(x, y - 4, row1.substring(0, width - x));
                frame.write(x, y - 3, row2.substring(0, width - x));
                frame.write(x, y - 2, row3.substring(0, width - x));
                frame.write(x, y - 1, row4.substring(0, width - x));
                frame.write(0, y, row5.substring(p));
                frame.write(0, y + 1, row6.substring(p));
                frame.write(0, y + 2, row7.substring(p));
                frame.write(0, y + 3, row8.substring(p));
                p++;
            }
            frame.finish(0.02);
        }
    }

    public static void eatingZombie(SignBoard board) {

        String headRow1 = " ▒▒██████  ";
        String headRow2 = "▒█▒███████▒";
        String headRow3 = "▒██████▒▄▒ ";
        String headRow4 = "▒██▒██████░";
        String openMouthRow5 = "░███▒██ ▄  ";
        String openMouthRow6 = "░ ████████ ";
        String openMouthRow7 = "  ░▒▒   ▒░ ";
        String openMouthRow8 = "░   ░   ▒  ";

        String closedMouthRow5 = "░███▒█████ ";
        String closedMouthRow6 = "░ ░▒▒   ▒░ ";
        String closedMouthRow7 = "    ░   ▒  ";
        String closedMouthRow8 = "░          ";


        String food1 = "  ____          ▄▄▄▄    ██▓     ▒█████   ▒█████  ▓█████▄                              ▄▄▄▄    ██▀███   ▄▄▄       ██▓ ███▄    █   ██████  ";
        String food2 = " |    |        ▓█████▄ ▓██▒    ▒██▒  ██▒▒██▒  ██▒▒██▀ ██▌         _.—~~(~~-_.        ▓█████▄ ▓██ ▒ ██▒▒████▄    ▓██▒ ██ ▀█   █ ▒██    ▒  ";
        String food3 = " |~~~~|        ▒██▒ ▄██▒██░    ▒██░  ██▒▒██░  ██▒░██   █▌       ,) -~~-(,-' )_       ▒██▒ ▄██▓██ ░▄█ ▒▒██  ▀█▄  ▒██▒▓██  ▀█ ██▒░ ▓██▄    ";
        String food4 = " '-..-'        ▒██░█▀  ▒██░    ▒██   ██░▒██   ██░░▓█▄   ▌      ( `-,_..`.’_,.)       ▒██░█▀  ▒██▀▀█▄  ░██▄▄▄▄██ ░██░▓██▒  ▐▌██▒  ▒   ██▒ ";
        String food5 = "   ||          ░▓█  ▀█▓░██████▒░ ████▓▒░░ ████▓▒░░▒████▓        `~-^(_;-,((())       ░▓█  ▀█▓░██▓ ▒██▒ ▓█   ▓██▒░██░▒██░   ▓██░▒██████▒▒ ";
        String food6 = "   ||          ░▒▓███▀▒░ ▒░▓  ░░ ▒░▒░▒░ ░ ▒░▒░▒░  ▒▒▓  ▒                `Jj }        ░▒▓███▀▒░ ▒▓ ░▒▓░ ▒▒   ▓▒█░░▓  ░ ▒░   ▒ ▒ ▒ ▒▓▒ ▒ ░ ";
        String food7 = " .----.        ▒░▒   ░ ░ ░ ▒  ░  ░ ▒ ▒░   ░ ▒ ▒░  ░ ▒  ▒                   {}        ▒░▒   ░   ░▒ ░ ▒░  ▒   ▒▒ ░ ▒ ░░ ░░   ░ ▒░░ ░▒  ░ ░ ";
        String food8 = "                ░    ░   ░ ░   ░ ░   ▒  ░ ░ ░ ▒   ░ ░  ░                              ░    ░   ░░   ░   ░   ▒    ▒ ░   ░   ░ ░ ░  ░  ░   ";


        Random random = new Random();
        int width = board.getWidth();
        int j = 0;
        int p = 0;
        int q = 0;
        int headStop = 30;
        int y = board.getHeight() / 2;
        for (int x = -headRow1.length(); x <= width + food1.length(); ++x) {
            // x <= (food1.length()*2)+headStop+headRow1.length()
            SignBoard.Frame frame = board.newFrame();
            int color = random.nextInt(3);

            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setRed();
//            else if (color == 2)
//                frame.setWhite();
            else
                frame.setYellow();

            if (x >= width + food1.length())
                break;

            if (x < 0) {
                // head scrolls from left, food scrolls in from right
                if (x % 4 == 0 && x % 5 == 0) {

                    frame.write(0, y - 4, headRow1.substring(-x));
                    frame.write(0, y - 3, headRow2.substring(-x));
                    frame.write(0, y - 2, headRow3.substring(-x));
                    frame.write(0, y - 1, headRow4.substring(-x));


                    frame.write(0, y, closedMouthRow5.substring(-x));
                    frame.write(0, y + 1, closedMouthRow6.substring(-x));
                    frame.write(0, y + 2, closedMouthRow7.substring(-x));
                    frame.write(0, y + 3, closedMouthRow8.substring(-x));


                    frame.write(width - j, y - 4, food1.substring(0, j));
                    frame.write(width - j, y - 3, food2.substring(0, j));
                    frame.write(width - j, y - 2, food3.substring(0, j));
                    frame.write(width - j, y - 1, food4.substring(0, j));
                    frame.write(width - j, y, food5.substring(0, j));
                    frame.write(width - j, y + 1, food6.substring(0, j));
                    frame.write(width - j, y + 2, food7.substring(0, j));
                    frame.write(width - j, y + 3, food8.substring(0, j));
                    j++;
                    q = j;

                } else {
                    frame.write(0, y - 4, headRow1.substring(-x));
                    frame.write(0, y - 3, headRow2.substring(-x));
                    frame.write(0, y - 2, headRow3.substring(-x));
                    frame.write(0, y - 1, headRow4.substring(-x));

                    frame.write(0, y, openMouthRow5.substring(-x));
                    frame.write(0, y + 1, openMouthRow6.substring(-x));
                    frame.write(0, y + 2, openMouthRow7.substring(-x));
                    frame.write(0, y + 3, openMouthRow8.substring(-x));

                    frame.write(width - j, y - 4, food1.substring(0, j));
                    frame.write(width - j, y - 3, food2.substring(0, j));
                    frame.write(width - j, y - 2, food3.substring(0, j));
                    frame.write(width - j, y - 1, food4.substring(0, j));
                    frame.write(width - j, y, food5.substring(0, j));
                    frame.write(width - j, y + 1, food6.substring(0, j));
                    frame.write(width - j, y + 2, food7.substring(0, j));
                    frame.write(width - j, y + 3, food8.substring(0, j));
                    j++;
                    q = j;
                }
            } else if (x <= 30) {
                //head scrolls up to about a quarter of the way through the board while the food scrolls onto board
                if (x % 4 == 0 && x % 5 == 0) {
                    frame.write(x, y - 4, headRow1);
                    frame.write(x, y - 3, headRow2);
                    frame.write(x, y - 2, headRow3);
                    frame.write(x, y - 1, headRow4);

                    frame.write(x, y, closedMouthRow5);
                    frame.write(x, y + 1, closedMouthRow6);
                    frame.write(x, y + 2, closedMouthRow7);
                    frame.write(x, y + 3, closedMouthRow8);

                    frame.write(width - j, y - 4, food1.substring(0, j));
                    frame.write(width - j, y - 3, food2.substring(0, j));
                    frame.write(width - j, y - 2, food3.substring(0, j));
                    frame.write(width - j, y - 1, food4.substring(0, j));
                    frame.write(width - j, y, food5.substring(0, j));
                    frame.write(width - j, y + 1, food6.substring(0, j));
                    frame.write(width - j, y + 2, food7.substring(0, j));
                    frame.write(width - j, y + 3, food8.substring(0, j));
                    j++;
                    q = j;

                } else {


                    frame.write(x, y - 4, headRow1);
                    frame.write(x, y - 3, headRow2);
                    frame.write(x, y - 2, headRow3);
                    frame.write(x, y - 1, headRow4);

                    frame.write(x, y, openMouthRow5);
                    frame.write(x, y + 1, openMouthRow6);
                    frame.write(x, y + 2, openMouthRow7);
                    frame.write(x, y + 3, openMouthRow8);


                    frame.write(width - j, y - 4, food1.substring(0, j));
                    frame.write(width - j, y - 3, food2.substring(0, j));
                    frame.write(width - j, y - 2, food3.substring(0, j));
                    frame.write(width - j, y - 1, food4.substring(0, j));
                    frame.write(width - j, y, food5.substring(0, j));
                    frame.write(width - j, y + 1, food6.substring(0, j));
                    frame.write(width - j, y + 2, food7.substring(0, j));
                    frame.write(width - j, y + 3, food8.substring(0, j));
                    j++;
                    q = j;
                }

            } else if (x > headStop && x <= width - (headStop + (headRow1.length() * 2))) {
                // head stays in place while food scrolls towards zombie face
                if (x % 4 == 0 && x % 5 == 0) {
                    frame.write(headStop, y - 4, headRow1);
                    frame.write(headStop, y - 3, headRow2);
                    frame.write(headStop, y - 2, headRow3);
                    frame.write(headStop, y - 1, headRow4);

                    frame.write(headStop, y, closedMouthRow5);
                    frame.write(headStop, y + 1, closedMouthRow6);
                    frame.write(headStop, y + 2, closedMouthRow7);
                    frame.write(headStop, y + 3, closedMouthRow8);


                    frame.write(width - j, y - 4, food1.substring(0, q));
                    frame.write(width - j, y - 3, food2.substring(0, q));
                    frame.write(width - j, y - 2, food3.substring(0, q));
                    frame.write(width - j, y - 1, food4.substring(0, q));
                    frame.write(width - j, y, food5.substring(0, q));
                    frame.write(width - j, y + 1, food6.substring(0, q));
                    frame.write(width - j, y + 2, food7.substring(0, q));
                    frame.write(width - j, y + 3, food8.substring(0, q));
                    j++;
                    if (j < food1.length()) {
                        q = j;
                    }

                } else {
                    frame.write(headStop, y - 4, headRow1);
                    frame.write(headStop, y - 3, headRow2);
                    frame.write(headStop, y - 2, headRow3);
                    frame.write(headStop, y - 1, headRow4);

                    frame.write(headStop, y, openMouthRow5);
                    frame.write(headStop, y + 1, openMouthRow6);
                    frame.write(headStop, y + 2, openMouthRow7);
                    frame.write(headStop, y + 3, openMouthRow8);


                    frame.write(width - j, y - 4, food1.substring(0, q));
                    frame.write(width - j, y - 3, food2.substring(0, q));
                    frame.write(width - j, y - 2, food3.substring(0, q));
                    frame.write(width - j, y - 1, food4.substring(0, q));
                    frame.write(width - j, y, food5.substring(0, q));
                    frame.write(width - j, y + 1, food6.substring(0, q));
                    frame.write(width - j, y + 2, food7.substring(0, q));
                    frame.write(width - j, y + 3, food8.substring(0, q));
                    j++;
                    if (j < food1.length()) {
                        q = j;
                    }
                }

            } else {
                if (x % 4 == 0 && x % 5 == 0) {
                    frame.write(headStop, y - 4, headRow1);
                    frame.write(headStop, y - 3, headRow2);
                    frame.write(headStop, y - 2, headRow3);
                    frame.write(headStop, y - 1, headRow4);

                    frame.write(headStop, y, closedMouthRow5);
                    frame.write(headStop, y + 1, closedMouthRow6);
                    frame.write(headStop, y + 2, closedMouthRow7);
                    frame.write(headStop, y + 3, closedMouthRow8);

                    frame.write(headRow1.length() - 1 + 31, y - 4, food1.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y - 3, food2.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y - 2, food3.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y - 1, food4.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y, food5.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y + 1, food6.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y + 2, food7.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y + 3, food8.substring(p));
                    if (p < food1.length()) {
                        p++;
                    }

                } else {
                    frame.write(headStop, y - 4, headRow1);
                    frame.write(headStop, y - 3, headRow2);
                    frame.write(headStop, y - 2, headRow3);
                    frame.write(headStop, y - 1, headRow4);

                    frame.write(headStop, y, openMouthRow5);
                    frame.write(headStop, y + 1, openMouthRow6);
                    frame.write(headStop, y + 2, openMouthRow7);
                    frame.write(headStop, y + 3, openMouthRow8);


                    frame.write(headRow1.length() - 1 + 31, y - 4, food1.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y - 3, food2.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y - 2, food3.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y - 1, food4.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y, food5.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y + 1, food6.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y + 2, food7.substring(p));
                    frame.write(headRow1.length() - 1 + 31, y + 3, food8.substring(p));
                    if (p < food1.length()) {
                        p++;
                    }
                }
            }

            frame.finish(0.03);
        }

    }


    public static void scrollingZombieAd(SignBoard board, String[] array, int cycles) {
        int width = board.getWidth();
        Random random = new Random();
        System.out.println(width);
        int y = board.getHeight() / 2;
        System.out.println(y);
        for (int i = -array[1].length(); i <= width * cycles; ++i) {
            double radians = (i) * 6.28 / array.length - 8;
            int leftPosition = (int) ((width / 2) - (50 * Math.sin(radians / 2)) - 35);
            SignBoard.Frame frame = board.newFrame();
            int j = 8;
            int x = Math.abs((i % (width * 4 / 5)) - (width * 4 / 10));
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
                frame.write(leftPosition, y, "  ZZZ     OO    OO  MM      MM  BBBBBBBBB        II      EEEEEE      ");
                frame.write(leftPosition, y + 1, " ZZZ      OO    OO  MM      MM  BB      BB       II      EEE         ");
                frame.write(leftPosition, y + 2, "ZZZZZZZZ  OOOOOOOO  MM      MM  BB      BB   IIIIIIIIII  EEEEEEEEEE  ");
                frame.write(leftPosition, y + 3, "ZZZZZZZZ   OOOOOO   MM      MM  BBBBBBBBB    IIIIIIIIII  EEEEEEEEEE  ");

            } else {

                frame.write(rightPosition, y - 4, Arrays.feast()[8]);
                frame.write(rightPosition, y - 3, Arrays.feast()[9]);
                frame.write(rightPosition, y - 2, Arrays.feast()[10]);
                frame.write(rightPosition, y - 1, Arrays.feast()[11]);
                frame.write(rightPosition, y, Arrays.feast()[12]);
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
                frame.write(leftPosition - 35, y, Arrays.eat()[4]);
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
                frame.write(leftPosition + 10, y, array[j + 4]);
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
            int leftPosition = (int) ((width / 2) - (50 * Math.sin(radians * 3 / 8)) - 35);
            SignBoard.Frame frame = board.newFrame();
            int j = (int) (((array.length - 8) / 2) * Math.sin(radians * 3 / 4)) + ((array.length - 8) / 2);

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


    public static void main(String[] args) {
        SignBoard signBoard = new SignBoard(8);

        // Run the sign board forever.
        while (true) {
//            ribbonScene(signBoard, 48);
//            scrollTextScene(signBoard, "###  F A L A F E L  ###");
//            ribbonScene(signBoard, 48);
//            flashFreshHotScene(signBoard, 8);


//
            zombieWalk(signBoard, 8);
//            truckDrive(signBoard, 30);
            scrollTruck(signBoard);
            myMenu(signBoard);
            dancingBrains(signBoard, 25);
            scrollingColdPressed(signBoard);
            eatingZombie(signBoard);
            zombieHour(signBoard, 24);
            verticalScroll(signBoard, Arrays.skullArray(), 1);
            verticalScrollS(signBoard, Arrays.skullArray(), 1);
            scrollingZombieAd(signBoard, Arrays.freshHumans(), 3);
            circles(signBoard, Arrays.skullArray(), 10);
            verticalScroll(signBoard, Arrays.asciigirl(), 1);

//
        }
    }
}
