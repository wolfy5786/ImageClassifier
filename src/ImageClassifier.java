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
    private static MultiPerceptron start(In f)
    {
        int noofclass = Integer.parseInt(f.readLine());
        String s=f.readLine();
        String[] wh = s.split(" ");
        int width = Integer.parseInt(wh[0]);
        int height = Integer.parseInt(wh[1]);
        MultiPerceptron multiPerceptron= new MultiPerceptron(noofclass,width*height);
        return multiPerceptron;
    }
    private static void train(MultiPerceptron multiPerceptron, In training)
    {
        int label;
        String s;
        while(training.hasNextLine())
        {
            s=training.readString();
            //s="Dig/"+s;
            label=training.readInt();
            training.readLine();
            Picture picture= new Picture(s);
            multiPerceptron.trainMulti(extractFeatures(picture),label);
        }

    }
    public static void testing(MultiPerceptron multiPerceptron, In testing)
    {
        int noofclass = Integer.parseInt(testing.readLine());
        String s=testing.readLine();
        String[] wh = s.split(" ");
        int width = Integer.parseInt(wh[0]);
        int height = Integer.parseInt(wh[1]);
        int label;
        int correct=0;
        int incoorect=0;
        while(testing.hasNextLine())
        {
            s=testing.readString();
            s="Dig/"+s;
            label=testing.readInt();
            testing.readLine();
            Picture picture= new Picture(s);
            int prediction=multiPerceptron.predictMulti(extractFeatures(picture));
            StdOut.println(label+" "+prediction);
            if(label==prediction)
            {
                correct++;
            }
            else {
                picture.show();
                incoorect++;
            }
        }
        System.out.println("correct: "+correct+"; incorrect: "+incoorect);
    }
    public static void main (String [] args)
    {
        In training = new In(args[0]);
        In testing = new In(args[1]);
        MultiPerceptron multiPerceptron=start(training);
        train(multiPerceptron,training);
        testing(multiPerceptron,testing);

    }
}
