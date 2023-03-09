package HomeWork.FinalProject;

import java.util.HashSet;
import java.util.Set;

public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("\n Привет! Это программа\n для подбора ноутбука!");
        Set<Notebook> set = new HashSet<>();
        set.add(new Notebook
        ("001", 16, "Windows", 1024, "Samsung"));
        set.add(new Notebook
        ("002", 32, "Windows", 1024, "Lenovo"));
        set.add(new Notebook
        ("003", 8, "linux", 256, "Asus"));
        set.add(new Notebook
        ("004", 8, "Mac", 512, "Apple"));
        set.add(new Notebook
        ("005", 16, "Solaris", 512, "IBM"));
        //System.out.println(set);

        Operation operation = new Operation(set);
        operation.start();
        System.out.println("\nВсего доброго! \nБыло приятно иметь с вами дело!");

    }
}