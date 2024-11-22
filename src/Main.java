import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите ФИО:");
            String fullName = scanner.nextLine();

            System.out.println("Введите дату рождения (дд.мм.гггг или дд/мм/гггг):");
            String birthDate = scanner.nextLine();

            PersonInfo person = new PersonInfo(fullName, birthDate);

            System.out.println("Инициалы: " + person.getInitials());
            System.out.println("Пол: " + person.getGender());
            System.out.println("Возраст: " + person.getAgeWithSuffix());

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
