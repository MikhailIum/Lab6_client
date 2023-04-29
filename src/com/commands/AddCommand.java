package com.commands;

import com.auxiliary.Check;
import com.auxiliary.Message;
import com.auxiliary.TextColor;
import com.main.Client;
import com.main.Data;
import com.main.Listener;
import com.study_group.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/** Adds new group to the collection */
public class AddCommand extends Command {
    static Scanner in = new Scanner(System.in);

    public AddCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        String name = "";
        boolean isNameSet = false;
        if (args.length == 3) {
            if (Objects.equals(args[1], "if min")) {
                Message message = Client.requestWithAnswer(Data.createData("add_if_min",  args));
                assert message != null;
                if(message.isException){
                    System.out.println(TextColor.ANSI_YELLOW + message.text + TextColor.ANSI_RESET);
                    return null;
                } else {
                    name = args[2];
                    isNameSet = true;
                }
            }
        }
        StudyGroup group = getStudyGroup(name, isNameSet);
        return Data.createData("add", group);
    }

    /**
     * Allows user to set the name of the new group or admin's name or location's where admin is from
     * name
     */
    public static String getGroupName() {
        System.out.print(
                TextColor.ANSI_PURPLE
                        + "Name"
                        + TextColor.ANSI_BLUE
                        + "(string)"
                        + TextColor.ANSI_PURPLE
                        + ": "
                        + TextColor.ANSI_RESET);
        String name;
        while (!Check.checkName(name = in.nextLine())) {
            System.out.print(
                    TextColor.ANSI_PURPLE
                            + "Name"
                            + TextColor.ANSI_BLUE
                            + "(string)"
                            + TextColor.ANSI_PURPLE
                            + ": "
                            + TextColor.ANSI_RESET);
        }
        return name;
    }

    /**
     * Finds out which delimiter is used by the user(" " / ", " / ",")
     *
     * @param coords - String user printed as a coordinates
     * @return delimiter(String)
     */
    private static String getDelimiter(String coords) {
        String del = " ";
        if (coords.contains(", ")) del = ", ";
        else if (coords.contains(",")) del = ",";
        return del;
    }

    /** Allows user to set the coordinates of the new group */
    public static Coordinates getCoords() throws IOException {
        System.out.print(
                TextColor.ANSI_PURPLE
                        + "Coordinates"
                        + TextColor.ANSI_BLUE
                        + "(x - double(<=860), y - integer)"
                        + TextColor.ANSI_PURPLE
                        + ": "
                        + TextColor.ANSI_RESET);
        String coords = in.nextLine();
        String del = getDelimiter(coords);

        while (!Check.checkCoords(coords, del)) {
            System.out.print(
                    TextColor.ANSI_PURPLE
                            + "Coordinates"
                            + TextColor.ANSI_BLUE
                            + "(x - double(<=860), y - integer)"
                            + TextColor.ANSI_PURPLE
                            + ": "
                            + TextColor.ANSI_RESET);
            coords = in.nextLine();
            del = getDelimiter(coords);
        }

        ArrayList<String> cord_ar = new ArrayList<>(Arrays.asList(coords.split(del)));
        cord_ar.removeIf(s -> Objects.equals(s, ""));

        double x = Double.parseDouble(cord_ar.get(0));
        long y = Long.parseLong(cord_ar.get(1));

        return new Coordinates(x, y);
    }

    /**
     * Allows user to set the number of students of the new group(or expelled students or those who
     * should be expelled)
     *
     * @param field - Number of students/Number of expelled students/Number of students who should be
     *     expelled
     */
    public static Integer getStudentsCount(String field) throws IOException {
        System.out.print(
                TextColor.ANSI_PURPLE
                        + field
                        + TextColor.ANSI_BLUE
                        + "(positive integer)"
                        + TextColor.ANSI_PURPLE
                        + ": "
                        + TextColor.ANSI_RESET);
        String num;
        while (!Check.checkStudentsCount(num = in.nextLine().split(" ")[0], field)) {
            System.out.print(
                    TextColor.ANSI_PURPLE
                            + field
                            + TextColor.ANSI_BLUE
                            + "(positive integer)"
                            + TextColor.ANSI_PURPLE
                            + ": "
                            + TextColor.ANSI_RESET);
        }
        int res;
        try {
            res = Integer.parseInt(num);
            return res;
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /** Allows user to set the semester on which is the new group */
    public static Semester getSemester() throws IOException {
        System.out.print(
                TextColor.ANSI_PURPLE
                        + "Semester: "
                        + "\n\t1 - First\n\t2 - Second\n\t3 - Fourth"
                        + "\n\t4 - Sixth\n\t5 - Seventh\n"
                        + TextColor.ANSI_RESET);
        String sem;
        while (!Check.checkEnum(sem = in.nextLine().replace(" ", ""), 1, 5)) {
            System.out.print(
                    TextColor.ANSI_PURPLE
                            + "Semester: "
                            + "\n\t1 - First\n\t2 - Second\n\t3 - Fourth"
                            + "\n\t4 - Sixth\n\t5 - Seventh\n"
                            + TextColor.ANSI_RESET);
        }

        return Semester.find(sem);
    }

    /** Allows user set the birthday of the admin of the new group */
    public static LocalDate getBirthday() throws IOException {
        System.out.print(
                TextColor.ANSI_PURPLE
                        + "Birthday"
                        + TextColor.ANSI_BLUE
                        + "(DD.MM.YYYY)"
                        + TextColor.ANSI_PURPLE
                        + ": "
                        + TextColor.ANSI_RESET);
        String date;
        while (!Check.checkBirthday(date = in.nextLine().replace(" ", ""))) {
            System.out.print(
                    TextColor.ANSI_PURPLE
                            + "Birthday"
                            + TextColor.ANSI_BLUE
                            + "(DD.MM.YYYY)"
                            + TextColor.ANSI_PURPLE
                            + ": "
                            + TextColor.ANSI_RESET);
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, format);
    }

    /** Allows user set admin's hair color */
    public static Color getHairColor() throws IOException {
        System.out.print(
                TextColor.ANSI_PURPLE
                        + "Hair color: "
                        + "\n\t1 - Black\n\t2 - Brown\n\t3 - Blue\n"
                        + TextColor.ANSI_RESET);
        String color;
        while (!Check.checkEnum(color = in.nextLine().replace(" ", ""), 1, 3)) {
            System.out.print(
                    TextColor.ANSI_PURPLE
                            + "Hair color: "
                            + "\n\t1 - Black\n\t2 - Brown\n\t3 - Blue\n"
                            + TextColor.ANSI_RESET);
        }

        return Color.findByNum(color);
    }

    /** Allows user set the nationality of the admin */
    public static Country getNationality() throws IOException {
        System.out.print(
                TextColor.ANSI_PURPLE
                        + "Nationality: "
                        + "\n\t1 - UK\n\t2 - USA\n\t3 - France\n\t4 - Thailand\n"
                        + TextColor.ANSI_RESET);
        String country;
        while (!Check.checkEnum(country = in.nextLine().replace(" ", ""), 1, 4)) {
            System.out.print(
                    TextColor.ANSI_PURPLE
                            + "Nationality: "
                            + "\n\t1 - UK\n\t2 - USA\n\t3 - France\n\t4 - Thailand\n"
                            + TextColor.ANSI_RESET);
        }

        return Country.findByNum(country);
    }

    /**
     * Allows user set the coordinates of the location where admin is from
     *
     * @param coord - type of coordinate(x\y\z)
     */
    public static String getCoordinate(String coord) throws IOException {
        String type = "";
        if (Objects.equals(coord, "x")) type = "(double)";
        else if (Objects.equals(coord, "y") || Objects.equals(coord, "z")) type = "(integer)";
        System.out.print(
                TextColor.ANSI_PURPLE
                        + coord
                        + TextColor.ANSI_BLUE
                        + type
                        + TextColor.ANSI_PURPLE
                        + ": "
                        + TextColor.ANSI_RESET);
        String x;
        while (!Check.checkCoordinate(x = in.nextLine().split(" ")[0], coord)) {
            System.out.print(
                    TextColor.ANSI_PURPLE
                            + coord
                            + TextColor.ANSI_BLUE
                            + type
                            + TextColor.ANSI_PURPLE
                            + ": "
                            + TextColor.ANSI_RESET);
        }

        return x;
    }

    /** Gets all the information about new study group from the user */
    private StudyGroup getStudyGroup(String name, boolean isNameSet) throws IOException {
        if (!isNameSet) name = getGroupName();
        Coordinates coords = getCoords();
        Integer studentsCount = getStudentsCount("Number of students");
        Integer expelledStudents = getStudentsCount("Number of expelled students");
        Integer shouldBeExpelled = getStudentsCount("Number of students who should be expelled");
        Semester semester = getSemester();

        return new StudyGroup(
                name,
                coords,
                studentsCount,
                expelledStudents,
                shouldBeExpelled,
                semester,
                getAdmin(),
                LocalDateTime.now(),
                UUID.nameUUIDFromBytes(name.getBytes()));
    }

    /** Gets all the information about admin of new study group from the user */
    private Person getAdmin() throws IOException {
        System.out.println("Admin:");
        String adminName = getGroupName();
        java.time.LocalDate birthday = getBirthday();
        Color hairColor = getHairColor();
        Country nationality = getNationality();
        return new Person(adminName, birthday, hairColor, nationality, getLocation());
    }

    /** Gets all the information about location of the admin from the user */
    private Location getLocation() throws IOException {
        System.out.println("Location:");
        System.out.println("Coordinates:");
        float x = Float.parseFloat(getCoordinate("x"));
        Integer y = Integer.parseInt(getCoordinate("y"));
        long z = Long.parseLong(getCoordinate("z"));
        String locationName = getGroupName();
        return new Location(x, y, z, locationName);
    }

}
