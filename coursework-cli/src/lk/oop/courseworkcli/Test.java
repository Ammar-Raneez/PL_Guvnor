package lk.oop.courseworkcli;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        LeagueManager premierLeagueManager = new PremierLeagueManager();
        premierLeagueManager.addClub();
        premierLeagueManager.addClub();
        premierLeagueManager.addPlayedMatch();
//        premierLeagueManager.displaySelectedClub();
//
//        System.out.println(premierLeagueManager.deleteClub());
//        premierLeagueManager.saveData();
//        premierLeagueManager.loadData();
    }
}