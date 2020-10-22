package cn.dark.serializable.thinking;

import java.io.*;
import java.util.Random;

/**
 * @author lwj
 * @date 2020-10-15
 */
public class Data implements Serializable {

    private int n;

    Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "Data{" +
                "n=" + n +
                '}';
    }
}

class Worm implements Serializable {
    private static Random rand = new Random(47);

    private Data[] d = {
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10))
    };

    private Worm next;

    private char c;

    public Worm() {
        System.out.println("default");
    }
    public Worm(int i, char x) {
        c = x;
        if (--i > 0) {
            next = new Worm(i, (char) (x + 1));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data dat : d) {
            result.append(dat);
        }
        result.append(")");
        if (next != null) {
            result.append(next);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Worm w = new Worm(6, 'a');
        System.out.println("w = " + w);
        test1(w);
        test2(w);
    }

    private static void test2(Worm w) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject("Worm storage\n");
            oos.writeObject(w);
            oos.flush();

            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()))) {
                String s = (String) ois.readObject();
                Worm worm = (Worm) ois.readObject();
                System.out.println(s + "w3 = " + worm);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test1(Worm w) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("worm.dat"))) {
            oos.writeObject("Worm storage\n");
            oos.writeObject(w);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("worm.dat"))) {
            String s = (String) ois.readObject();
            Worm worm = (Worm) ois.readObject();
            System.out.println(s + "w2 = " + worm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}