import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PremierLeagueManager implements LeagueManager {
    private static List<FootballClub> allFootballClubs = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static final String savePath = "C:\\Users\\Ammuuu\\Downloads\\learning\\UNI\\OOP-Module\\Coursework\\OOP-COURSEWORK\\savefile";

    @Override
    public void addClub() throws ClassNotFoundException, IllegalAccessException {
        System.out.println("Please enter the type of club (University /School /League level)");
        String clubType = sc.nextLine().toLowerCase();

        while (true) {
            if (clubType.equals("university") || clubType.equals("school") || clubType.equals("league")) {
                break;
            } else {
                System.out.println("Please specify appropriately! (university/school/league)");
                clubType = sc.nextLine().toLowerCase();
            }
        }

        System.out.println("Enter Club's name");
        String clubName = sc.nextLine().toLowerCase();
        boolean clubExists = false;

        while (true) {
            for (FootballClub footballClub : allFootballClubs) {
                if (footballClub.getClubName().equals(clubName)) {
                    System.out.println("[ERROR] ==> " + clubName + " already exists! Please try again");
                    clubExists = true;
                    System.out.println("Enter Club's name");
                    clubName = sc.nextLine().toLowerCase();
                    break;
                }
            }
            if (!clubExists) {
                break;
            } else {
                clubExists = false;
            }
        }

        System.out.println("Enter club location");
        String clubLocation = sc.nextLine().toLowerCase();
        System.out.println("Enter club owner");
        String clubOwner = sc.nextLine().toLowerCase();
        System.out.println("Enter club sponsor");
        String clubSponsor = sc.nextLine().toLowerCase();

        Color colorTop;
        Color colorShort;
        Field fieldTop;
        Field fieldBottom;

        while (true) {
            try {
                System.out.println("Enter club kit top color");
                String topColorInput = sc.nextLine();
                fieldTop = Class.forName("java.awt.Color").getField(topColorInput.toLowerCase());
                colorTop = (Color) fieldTop.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, magenta, orange, " +
                        "pink, red, white, yellow]");
            }
        }

        while (true) {
            try {
                System.out.println("Enter club kit short color");
                String shortColorInput = sc.nextLine();
                fieldBottom = Class.forName("java.awt.Color").getField(shortColorInput.toLowerCase());
                colorShort = (Color) fieldBottom.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, magenta, orange, " +
                        "pink, red, white, yellow]");
            }
        }

        FootballClub footballClub = null;
        switch (clubType) {
            case "university":
                System.out.println("Please enter the lecturer in charge");
                String lecturerInCharge = sc.nextLine();
                footballClub = new UniversityFootballClub(clubName, clubLocation, clubOwner, new SportsClubKit(clubSponsor, colorTop, colorShort), lecturerInCharge);
                break;
            case "school":
                System.out.println("Please enter the teacher in charge");
                String teacherInCharge = sc.nextLine();
                footballClub = new SchoolFootballClub(clubName, clubLocation, clubOwner, new SportsClubKit(clubSponsor, colorTop, colorShort), teacherInCharge);
                break;
            case "league":
                footballClub = new FootballClub(clubName, clubLocation, clubOwner, new SportsClubKit(clubSponsor, colorTop, colorShort));
                break;
        }
        allFootballClubs.add(footballClub);
        System.out.println(footballClub);
        System.out.println(allFootballClubs.size());
    }

    @Override
    public SportsClub deleteClub() {
        System.out.println("Enter Club Name you wish to delete");
        String clubInput = sc.nextLine().toLowerCase();

        boolean foundFlag = false;
        SportsClub removedClub = null;
        for (FootballClub footballClub : allFootballClubs) {
            if(footballClub.getClubName().equals(clubInput)) {
                removedClub = allFootballClubs.remove(allFootballClubs.indexOf(footballClub));
                foundFlag = true;
                break;
            }
        }
        if(!foundFlag) {
            System.out.println("[ERROR] ==> No Such Club Exists");
            return null;
        } else {
            System.out.println(allFootballClubs.size());
            return removedClub;
        }
    }

    @Override
    public void displaySelectedClub() {
        System.out.println("Enter club name to display");
        String clubNameInput = sc.nextLine().toLowerCase();

        boolean foundFlag = false;
        FootballClub foundClub = null;
        for(FootballClub footballClub : allFootballClubs) {
            if (footballClub.getClubName().equals(clubNameInput)) {
                foundClub = footballClub;
                foundFlag = true;
                break;
            }
        }
        if (!foundFlag) {
            System.out.println("[ERROR] ==> No Such Club Exists");
        } else {
            System.out.println(foundClub);
        }
    }

    @Override
    public void addPlayedMatch() {

    }

    @Override
    public void displayPointsTable() {

    }

    @Override
    public void saveData() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath + "\\saveFile.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            System.out.print("Now saving data");
            Thread.sleep(500);
            System.out.print(".");
            Thread.sleep(500);
            System.out.print(".");
            Thread.sleep(500);
            System.out.println(".");
            objectOutputStream.writeObject(allFootballClubs);
            objectOutputStream.close();
            fileOutputStream.close();
            Thread.sleep(500);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(savePath + "\\saveFile.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            System.out.print("Now loading data");
            Thread.sleep(500);
            System.out.print(".");
            Thread.sleep(500);
            System.out.print(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            allFootballClubs = (List<FootballClub>) objectInputStream.readObject();
            System.out.println("Data loaded successfully!");
            fileInputStream.close();
            objectInputStream.close();
            System.out.println(allFootballClubs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
