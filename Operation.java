package HomeWork.FinalProject;

import java.util.*;

public class Operation 
{
    private Set<Notebook> notebooks = new HashSet<>();
    private List<Criterion> criterionList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void printList() 
    {
        for (Notebook notebook : notebooks) 
        {
            if (notebookIsCorrect(notebook)) 
            {
                System.out.println(notebook);
            }
        }
    }

    public boolean notebookIsCorrect(Notebook notebook) 
    {
        for (Criterion criterion : criterionList) {
            Object valueNotebook = null;

            if (criterion.property.equals("name")) 
            {
                valueNotebook = notebook.getId();
            } 
            else if (criterion.property.equals("amountRAM")) 
            {
                valueNotebook = notebook.getRam();
            } 
            else if (criterion.property.equals("operatingSystem")) 
            {
                valueNotebook = notebook.getOperatingSystem();
            }
             else if (criterion.property.equals("hard")) 
            {
                valueNotebook = notebook.getHard();
            } 
            else if (criterion.property.equals("model")) 
            {
                valueNotebook = notebook.getModel();
            } 
            else 
            {
                continue;
            }

            if (criterion.value != null && !criterion.value.equals(valueNotebook)) 
            {
                return false;
            }

            if (criterion.maxValue != null
                    && criterion.maxValue < Double.parseDouble(Objects.toString(valueNotebook))) 
            {
                return false;
            }

            if (criterion.minValue != null
                    && criterion.minValue > Double.parseDouble(Objects.toString(valueNotebook))) 
            {
                return false;
            }
        }
        return true;
    }

    public Operation(Set<Notebook> notebooks) 
    {
        this.scanner = new Scanner(System.in);
        this.notebooks = notebooks;
    }

    public Operation(Set<Notebook> notebooks, List<Criterion> criterionList) 
    {
        this.scanner = new Scanner(System.in);
        this.notebooks = notebooks;
        this.criterionList = criterionList;
    }

    public int getCriteria() {
        String text = "Введите цифру, соответствующую необходимому критерию ! ";
        List<String> properties = propertiesForFilter();

        for (int i = 0; i < properties.size(); i++) 
        {
            text += "\n" + (i + 1) + ". " + getPropertyDescription(properties.get(i));
        }
        System.out.println(text);
        System.out.print("необходимый критерий -> ");
        int value = scanner.nextInt();
        return value;
    }

    public String getPropertyDescription(String property) 
    {
        Map<String, String> descriptionsProperties = descriptionsProperties();
        return descriptionsProperties.get(property);
    }

    public Map<String, String> descriptionsProperties() 
    {
        Map<String, String> map = new HashMap<>();

        map.put("name", "по Id");
        map.put("amountRAM", "Объем оперативной памяти");
        map.put("operatingSystem", "Операционная система");
        map.put("hard", "Объем ЖД");
        map.put("model", "Модель");
        return map;
    }

    public List<String> propertiesForFilter() 
    {
        List<String> list = new ArrayList<>();
        list.add("name");
        list.add("amountRAM");
        list.add("operatingSystem");
        list.add("hard");
        list.add("model");
        return list;
    }

    public String getOperations() 
    {
        String text = "   --------------\n        Меню \n   --------------\n " +
                "1. Выбор параметра \n " +
                "2. Список ноутбуков\n " +
                "3. Выход";
        System.out.println(text);
        System.out.print(" Сделайте выбор -> ");
        String answer = scanner.next();
        return answer;
    }

    public Set<String> quantitativeSelection() 
    {
        Set<String> set = new HashSet<>();
        set.add("amountRAM");
        set.add("hard");
        return set;
    }

    public Set<String> stringSelection() 
    {
        Set<String> set = new HashSet<>();
        set.add("name");
        set.add("operatingSystem");
        set.add("model");
        return set;
    }

    public void start() 
    {
        boolean flag = true;
        while (flag) 
        {
            String operation = getOperations();
            if (operation.equals("3")) 
            {
                flag = false;
                scanner.close();
                continue;
            } 
            else if (operation.equals("1")) 
            {

                int criterion = getCriteria();
                List<String> properties = propertiesForFilter();
                if (criterion - 1 < 0 || criterion - 1 > properties.size() - 1) 
                {
                    System.out.println("Введено некорректное значение");
                    continue;
                }
                String property = properties.get(criterion - 1);
                Criterion criterionObject = null;
                try 
                {
                    if (quantitativeSelection().contains(property)) 
                    {
                        criterionObject = Criterion.startGetting(scanner, property, true);
                    } 
                    else 
                    {
                        criterionObject = Criterion.startGetting(scanner, property, false);
                    }
                } 
                catch (Exception e) 
                {
                    System.out.println("Ошибка при выборе");
                    continue;
                }
                if (criterionObject != null) 
                {
                    System.out.println("Ваш критерий добавлен !");
                    criterionList.add(criterionObject);
                    System.out.println("Промежуточный итог: ");
                    printList();
                }
            } else if (operation.equals("2")) {
                printList();
            }
        }
    }
}

class Criterion 
{

    Object value;
    Double minValue;
    Double maxValue;
    boolean isQuantitative;
    String property;

    public Criterion
    (String property, boolean isQuantitative, Object value, Double minValue, Double maxValue) 
    {
        this.property = property;
        this.isQuantitative = isQuantitative;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static Criterion startGetting
    (Scanner scanner, String property, boolean isQuantitative) 
    {
        if (isQuantitative) 
        {
            String quest = "Выберите тип критерия: " +
                    "\n 1. По значению" +
                    "\n 2. Меньше заданного" +
                    "\n 3. Больше заданного" +
                    "\n 4. Интервал";
            System.out.println(quest);
            System.out.print("Выбор -> ");
            String text = scanner.next();
            Criterion criterion = null;
            if (text.equals("1")) 
            {
                System.out.print("Введите значение -> ");
                int getValue = scanner.nextInt();
                criterion = new Criterion(property, isQuantitative, getValue, null, null);
            } 
            else if (text.equals("2")) 
            {
                System.out.print("Введите максимальное предельное значение -> ");
                double getValue = scanner.nextDouble();
                criterion = new Criterion(property, isQuantitative, null, null, getValue);
            } 
            else if (text.equals("3")) 
            {
                System.out.print("Введите минимальное предельное значение -> ");
                double getValue = scanner.nextDouble();
                criterion = new Criterion(property, isQuantitative, null, getValue, null);
            } 
            else if (text.equals("4")) 
            {
                System.out.print("Введите минимальное предельное значение -> ");
                double getMin = scanner.nextDouble();
                System.out.print("Введите максимальное предельное значение -> ");
                double getMax = scanner.nextDouble();
                criterion = new Criterion(property, isQuantitative, null, getMin, getMax);
            }

            return criterion;
        }

        System.out.print("Введите значение -> ");
        String getValue = scanner.next();
        return new Criterion(property, isQuantitative, getValue, null, null);
    }

}