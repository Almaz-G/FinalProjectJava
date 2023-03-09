package HomeWork.FinalProject;

import java.util.ArrayList;
import java.util.List;

public class Notebook 
{
    private String id;

    private int ram;
    private String operatingSystem;
    private int hard;
    private String model;

    public Notebook(String id, int ram, String operatingSystem, int hard, String model) 
    {
        this.id = id;
        this.ram = ram;
        this.operatingSystem = operatingSystem;
        this.hard = hard;
        this.model = model;
    }

    public static List<String> propertiesForFilter()
    {
        List<String> list = new ArrayList<>();
        list.add("ram");
        list.add("operatingSystem");
        list.add("hard");
        list.add("model");

        return list;
    }

    @Override
    public String toString() 
    {
        return "Ноутбук: (" + id + "): " +
                "Опретивная память:" + ram +
                ", Система: " + operatingSystem + '\'' +
                ", Объем ЖД: " + hard +
                ", Модель: " + model;
    }

    public String getId() 
    {
        return id;
    }

    public void setId(String name) 
    {
        this.id = name;
    }

    public int getRam() 
    {
        return ram;
    }

    public void setRam(int RAM) 
    {
        this.ram = RAM;
    }

    public String getOperatingSystem() 
    {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) 
    {
        this.operatingSystem = operatingSystem;
    }

    public int getHard() 
    {
        return hard;
    }

    public void setHard(int hdd) 
    {
        this.hard = hdd;
    }

    public String getModel() 
    {
        return model;
    }

    public void setModel(String model) 
    {
        this.model = model;
    }
}