import java.util.Arrays;

public class Perceptron {
    double [] weights;
    int n; // no of pixels of an image, also no of weights
    Perceptron(int n)
    {
        this.n=n;
        weights =new double[n];
        Arrays.fill(weights, 0.0);
    }
    public int numberOfInputs()
    {
        return n;
    }
    public double weightedSum(double [] x)
    {
        double s=0;
        for(int i=0;i<x.length;i++)
        {
            s+=x[i]*this.weights[i];
        }
        return s;
    }
    //returns +1 if weighted sum>0 else -1
    public int predict(double [] x)
    {
        int s=(int) Math.signum(weightedSum(x));
        if(s==0)
        {
            s=-1;
        }
        return s;
    }
    public void train (double [] x,int label)
    {
        int s=predict(x);
        if(s==1 ^ label==1)
        {
            for(int i=0;i<x.length;i++)
            {
                weights[i]+=x[i]*s*-1;
            }
        }
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "weights=" + Arrays.toString(weights) +
                ", n=" + n +
                '}';
    }
    public static void main (String[] args)
    {
        double [] w = {1,2,3,1};
        Perceptron perceptron = new Perceptron(10);
        StdOut.println(perceptron.toString());
        StdOut.println(perceptron.weightedSum(w));
    }
}
