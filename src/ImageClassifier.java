import java.awt.*;

public class ImageClassifier {
    //Takes a Picture as an input and return an 1 dimensional aray of greyscale value of picture in row major order
    public static double [] extractFeatures(Picture picture)
    {
        int width = picture.width();
        int height = picture.height();
        double [] greyscale = new double[width*height];
        Color color;
        int k=0;
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                color=picture.get(j,i);
                greyscale[k]=color.getRed();
                k++;
            }
        }
        return greyscale;
    }
    public static void main (String args[])
    {

    }
}
