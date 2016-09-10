package nyc.c4q.ac21.signboard;

import java.util.Random;

/**
 * Created by hakeemsackes-bramble on 9/9/16.
 */
public class TestPage {
    public static void main(String[] args) {

    }
    public static void circles(SignBoard board,String[] array, int cycles){
        Random random = new Random();
        int width = board.getWidth();
        //int leftPosition = (width / 2) - (40 * Math.cos(radians));
        int y = board.getHeight() / 2;
        for(int i = 0; i < (array.length-8) * cycles ; i++){
            double radians = (i) * 6.28 /array.length-8;
            int leftPosition = (int)((width / 2) - (50* Math.cos(radians)) - 35);
            SignBoard.Frame frame = board.newFrame();
            int j =(int) (((array.length - 8)/2)* Math.sin(radians))+((array.length - 8)/2);

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
            frame.write(leftPosition, y - 3, array[j+1]);
            frame.write(leftPosition, y - 2, array[j+2]);
            frame.write(leftPosition, y - 1, array[j+3]);
            frame.write(leftPosition, y    , array[j+4]);
            frame.write(leftPosition, y + 1, array[j+5]);
            frame.write(leftPosition, y + 2, array[j+6]);
            frame.write(leftPosition, y + 3, array[j+7]);

            frame.finish(0.01);
        }
    }
}
