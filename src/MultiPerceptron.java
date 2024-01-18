import java.util.Arrays;

public class MultiPerceptron {
    int m;  //no of labels
    int n;  //no of inputs,
    Perceptron [] perceptrons;
    MultiPerceptron(int m, int n)
    {
        this.m=m;
        this.n=n;
        perceptrons = new Perceptron[m];
        for(int i=0;i<m;i++)
        {
            perceptrons[i]=new Perceptron(n);
        }
    }
    int numberOfClasses()
    {
        return m;
    }
    int numberOfInputs()
    {
        return n;
    }
    public int predictMulti(double []x)
    {
        double largest = perceptrons[0].weightedSum(x);
        int index=0;
        for(int i=0;i<m;i++)
        {
            if(perceptrons[i].weightedSum(x)>largest)
            {
                largest=perceptrons[i].weightedSum(x);
                index=i;
            }
        }
        return index;
    }
    void trainMulti(double [] x,int label)
    {
        perceptrons[label].train(x,1);
        for(int i=0;i<m;i++)
        {
            if(i==label)
            {
                continue;
            }
            perceptrons[i].train(x,-1);
        }
    }

    @Override
    public String toString() {
        String s=" ";
        for(int i=0;i<m;i++)
        {
            s=s+" {"+perceptrons[i].toString()+"} ";
        }
        return "MultiPerceptron{" +
                "m=" + m +
                ", n=" + n +
                ", perceptrons=" +
                s+
                '}';
    }

    public static void main (String args[])
    {
        int n=3;
        int m=2;
        MultiPerceptron multiPerceptron =new MultiPerceptron(2,2);
        StdOut.println(multiPerceptron.toString());
    }
}
