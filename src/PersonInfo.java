import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;

public class PersonInfo {
    private String fullName;
    private LocalDate birthDate;

    public PersonInfo(String fullName, String birthDate) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("ФИО не может быть пустым");
        }
        this.fullName = fullName;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[d.M.yyyy][d/M/yyyy]");
            LocalDate parsedDate = LocalDate.parse(birthDate.trim(), formatter);
            if (parsedDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Дата рождения не может быть в будущем");
            }
            this.birthDate = parsedDate;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Используйте формат дд.мм.гггг или дд/мм/гггг");
        }
    }

    public String getInitials() {
        String[] parts = fullName.split("\\s+");
        if (parts.length < 3) {
            throw new IllegalArgumentException("ФИО должно содержать три части: фамилию, имя и отчество");
        }
        return String.format("%s %c.%c.", parts[0], parts[1].charAt(0), parts[2].charAt(0));
    }

    public String getGender() {
        String[] parts = fullName.split("\\s+");
        if (parts.length < 3) {
            throw new IllegalArgumentException("ФИО должно содержать три части: фамилию, имя и отчество");
        }
        String patronymic = parts[2].toLowerCase();

        if (patronymic.endsWith("ич")) {
            return "М";
        } else if (patronymic.endsWith("на")) {
            return "Ж";
        }
        else {
            return "Не удалось определить пол";
        }
    }

    public String getAgeWithSuffix() {
        LocalDate today = LocalDate.now();
        int years = Period.between(birthDate, today).getYears();

        if (years % 10 == 1 && years % 100 != 11) {
            return years + " год";
        } else if (years % 10 >= 2 && years % 10 <= 4 && (years % 100 < 10 || years % 100 >= 20)) {
            return years + " года";
        } else {
            return years + " лет";
        }
    }
}
