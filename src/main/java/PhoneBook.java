/*
Реализуйте структуру телефонной книги с помощью HashMap.

Программа также должна учитывать,
что в во входной структуре будут повторяющиеся имена с разными телефонами,
их необходимо считать, как одного человека с разными телефонами.
Вывод должен быть отсортирован по убыванию числа телефонов.
*/

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();
    private static LinkedHashMap<String, ArrayList<Integer>> sortBook = new LinkedHashMap<>();

    // +Добавление номера в элемент по имени, или нового элемента, если имя новое.
    public void add(String name, Integer phoneNum) {
            ArrayList<Integer> phoneNums = phoneBook.getOrDefault(name, new ArrayList<>());
            phoneNums.add(phoneNum);
            phoneBook.put(name, phoneNums);
    }

    // Метод сортировки и печати в порядке убывания количества номеров
    public static void sortPhoneBook(HashMap<String, ArrayList<Integer>> map) {
        HashMap<String, Integer> counter = new HashMap<>();

        for (String key : map.keySet()) {
            counter.put(key, map.getOrDefault(key, new ArrayList<>()).size());
        }

        Map<String, Integer> sortedMap = counter.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        sortedMap.forEach((k,v)-> {
            System.out.println(k + ": " + map.get(k));
        });
    }


    public static void main(String[] args) {
        PhoneBook myPhoneBook = new PhoneBook();
        // Данные вводятся из файла text.txt
        System.out.println("Данные вводятся из файла text.txt и из самой программы");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (true){
            String data = scanner.nextLine();
            if (data.equals("exit")) break;
            list.add(data);
        }
        for (int i = 0; i < list.size(); i++) {
            String[] info = list.get(i).split(" ");
            myPhoneBook.add(info[0], Integer.valueOf(info[1]));
        }

        // Данные вводятся из программы
        myPhoneBook.add("Ivanov", 123456);
        myPhoneBook.add("Ivanov", 654322);
        myPhoneBook.add("Petrov", 654321);
        myPhoneBook.add("Иванов", 123);
        myPhoneBook.add("Иванов", 1234);
        myPhoneBook.add("Сидоров", 8956477);
        myPhoneBook.add("Иванов", 12356233);
        myPhoneBook.add("Петров", 787897);

        sortPhoneBook(phoneBook); //Вызов метода сортировки и печати книги
        //System.out.println(phoneBook);
    }
}
