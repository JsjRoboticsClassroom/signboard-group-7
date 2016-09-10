package nyc.c4q.ac21.signboard;

import java.util.Random;

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

            frame.finish(0.02);
        }
    }

    /**
     * Draws a scene with text scrolling across the screen..
     * @param board
     *   The board on which to draw.
    // * @param text
     *   The text to scroll.
     */

    public static void scrollTextScene(SignBoard board, String text) {

        int width = board.getWidth();
        int y = board.getHeight() / 2;
        for (int x = -text.length(); x <= width; ++x) {
            SignBoard.Frame frame = board.newFrame();

            if (x >= width)
                break;

            if (x < 0) {

                frame.write(0, y, text.substring(-x));
                frame.write(0, y - 1, text.substring(-x));

            } else if (x + text.length() <= width) {
                // Fully on the board.
                frame.write(x, y, text);
                frame.write(x, y - 1, text);

            } else {
                // Scrolling off the board.
                frame.write(x, y, text.substring(0, width - x));
                frame.write(x, y - 1, text.substring(0, width - x));
            }
            frame.finish(0.02);
        }
    }

    /**
     * Draws a scene which flashes the words "FRESH" and "HOT".
     * @param board
     *   The board on which to draw.
     * @param cycles
     *   The number of cycles to draw for.
     */
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
            int position = random.nextInt(width-80);
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

    public static void scrollingColdPressed( SignBoard board) {

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
                frame.write(width-j, y, row5.substring(0, j));
                frame.write(width-j, y + 1, row6.substring(0, j));
                frame.write(width-j, y + 2, row7.substring(0, j));
                frame.write(width-j, y + 3, row8.substring(0, j));
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

        String headRow1 =      " ▒▒██████  ";
        String headRow2 =      "▒█▒███████▒";
        String headRow3 =      "▒██████▒▄▒ ";
        String headRow4 =      "▒██▒██████░";
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
        for (int x = -headRow1.length(); x<= width+food1.length()  ; ++x ) {
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

            } else if (x > headStop && x <= width-(headStop+(headRow1.length()*2))) {
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
                    if (j < food1.length()){
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
                    if (j < food1.length()){
                        q = j;
                    }
                    }

                }

                else {
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

    public static void main(String[] args) {
        SignBoard signBoard = new SignBoard(8);

        // Run the sign board forever.
        while (true) {

            //ribbonScene(signBoard, 48);
            dancingBrains(signBoard, 20);
            scrollingColdPressed(signBoard);
            eatingZombie(signBoard);


            //scrollTextScene(signBoard, "hello");

//            ribbonScene(signBoard, 48);
//            flashingBrain(signBoard, 15);

        }
    }
}
