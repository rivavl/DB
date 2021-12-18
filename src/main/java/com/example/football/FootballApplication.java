package com.example.football;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.football.model.*;
import com.example.football.repo.*;
import com.example.football.repo.sup.PlayingSup;
import com.example.football.repo.sup.TeamMatchSup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class FootballApplication implements CommandLineRunner {
    FootballApplication(StadiumRepository stadiumRepository,
                        TeamRepository teamRepository,
                        MatchRepository matchRepository,
                        PlayerRepository playerRepository,
                        PlayingRepository playingRepository,
                        TeamMatchRepository teamMatchRepository,
                        GoalRepository goalRepository) {
        this.stadiumRepository = stadiumRepository;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.playingRepository = playingRepository;
        this.teamMatchRepository = teamMatchRepository;
        this.goalRepository = goalRepository;
    }

    StadiumRepository stadiumRepository;
    TeamRepository teamRepository;
    MatchRepository matchRepository;
    PlayerRepository playerRepository;
    PlayingRepository playingRepository;
    TeamMatchRepository teamMatchRepository;
    GoalRepository goalRepository;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        SpringApplication.run(FootballApplication.class, args);
    }
    @Override
    public void run(String... args) throws IOException, ParseException {
        print("BRUH MOMENT");


        String text_menu_for_all=
                "\t\t\t\tМеню для работы с базой данных:"+
                        "\t\t\t1)даты встреч команды, ее противник и счет\n"+
                        "\t\t\t2)ФИО и номера игроков, участвовавших во встрече (по названию команды, городу и дате встречи)\n"+
                        "\t\t\t3)результативность данного игрока в данной встрече (по названию команды, городу, дате встречи и ФИО игрока)\n"+
                        "\t\t\t4)цена билета на матч указанных команд\n";
        String text_menu_for_admin=
                "\t\t\t5)добавить стадион\n"+
                        "\t\t\t6)добавить команду\n"+
                        "\t\t\t7)добавить игрока\n"+
                        "\t\t\t8)добавить матч\n"+
                        "\t\t\t9)выбрать игроков для матча\n"+
                        "\t\t\t10)добавить гол\n"+

                        "\t\t\t11)переход игрока из одной команды в другую\n"+
                        "\t\t\t12)отмена встречи\n"+
                        "\t\t\t13)назначение нового тренера\n"+
                        "\t\t\t14)Выдача справки об играх";

        /*на указанном стадионе и отчет о их проведении (количество проведенных встреч, число побед хозяев и гостей, ФИО игроков, забивавших мячи\n" +
                "в каждой команде, названия стадионов, где проводились встречи)"*/


        //String name = reader.readLine();

        //int nAge = Integer.parseInt(sAge);
        String ch="";
        while (!(ch.equals("`")) && !(ch.equals("ё")))
        {
            ch = reader.readLine();

            switch (ch)
            {
                case ("1"):
                    //Даты встреч команды, ее противник и счет
//                    date_match_team_score();
                    break;
                case ("2"):
                    name_number();
                    break;
                case ("3"):
                    resultant();
                    break;
                case ("4"):
                    add_stadium();
                    break;
                case ("5"):
                    add_team();
                    break;
                case ("6"):
                    add_player();
                    break;
                case ("7"):
                    add_match();
                    break;
                case ("8"):
                    add_team_match();
                    break;
                case ("9"):
                    add_goal();
                    break;
            }

        }
    }
    public Date string_in_date(String date) throws ParseException {
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return date1;
    }
    public Time string_in_time(String time) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Time time1=new java.sql.Time(formatter.parse(time).getTime());
        return time1;
    }
    public void print(Object text){
        System.out.print(text);
    }

    public void date_match_team_score() throws IOException {
        //Даты встреч команды, ее противник и счет
        print("\nВведите название команды: ");
        String name_team=reader.readLine();

        List<PlayingSup> psList=playingRepository.date_match_team_score(name_team);
        print("\n");
        print(psList);
    }
    public void name_number() throws IOException, ParseException {
        //ФИО и номера игроков, участвовавших во встрече (по названию команды, городу и дате встречи)
        print("\nВведите название команды: ");
        String name_team=reader.readLine();
        print("\nВведите город: ");
        String city=reader.readLine();
        print("\nВведите дату матча в формате ГГГГ/ММ/ДД: ");
        Date date=string_in_date(reader.readLine());
        List<TeamMatchSup> tmsList=teamMatchRepository.name_number_by_team_city_date(name_team,city,date);
        print("\n");
        System.out.print(tmsList);
    }
    public void resultant() throws IOException, ParseException {
        //результативность данного игрока в данной встрече (по названию команды, городу, дате встречи и ФИО игрока)
        print("\nВведите название команды: ");
        String name_team=reader.readLine();
        print("\nВведите город: ");
        String city=reader.readLine();
        print("\nВведите дату матча в формате ГГГГ/ММ/ДД: ");
        Date date=string_in_date(reader.readLine());
        print("\nВведите ФИО игрока: ");
        String name_player=reader.readLine();
        Integer score=goalRepository.resultant(name_team,city, date,name_player);
        print("\nрезультативность(количество голов): ");print(score);
    }

    public boolean add_stadium() throws IOException {
        Stadium stadium=new Stadium();
        print("\nВведите название стадиона: ");
        stadium.name=reader.readLine();
        print("\nВведите город: ");
        stadium.city=reader.readLine();
        print("\nВведите вместимость стадиона: ");
        stadium.capacity=Integer.parseInt (reader.readLine());

        stadiumRepository.save(stadium);

        return true;
    }
    public boolean add_team() throws IOException{
        Team team=new Team();
        print("\nВведите название команды: ");
        team.name=reader.readLine();
        print("\nВведите город: ");
        team.city=reader.readLine();
        print("\nВведите ФИО тренера: ");
        team.trainerName=reader.readLine();
        print("\nВведите название стадиона: ");
        String name_stadium=reader.readLine();
        team.position=stadiumRepository.last_position();
        team.stadium=stadiumRepository.get_stadium_by_param(name_stadium,team.city);

        teamRepository.save(team);

        return true;
    }
    public boolean add_player() throws IOException{
        Player player=new Player();
        print("\nВведите ФИО игрока: ");
        player.name=reader.readLine();
        print("\nВведите номер игрока: ");
        player.number=reader.readLine();
        print("\nВведите название команды: ");
        String name_team=reader.readLine();
        print("\nВведите город команды: ");
        String city_team=reader.readLine();
        player.team=teamRepository.get_team_by_param(name_team,city_team);

        playerRepository.save(player);

        return true;
    }
    public boolean add_match() throws IOException, ParseException {
        Match match=new Match();
        Playing playing1=new Playing();
        Playing playing2=new Playing();

        print("\nВведите дату матча в формате ДД/ММ/ГГГГ: ");
        String data=reader.readLine();
        match.date=string_in_date(data);

        print("\nВведите название стадиона на котором будет проводиться матч: ");
        String name_stadium=reader.readLine();
        print("\nВведите город проведения матча: ");
        String city_stadium=reader.readLine();

        match.stadium=stadiumRepository.get_stadium_by_param(name_stadium,city_stadium);

        matchRepository.save(match);

        match=matchRepository.get_match_by_param(match.date,match.stadium.id);
        playing1.match=match;
        playing2.match=match;

        print("\nВведите название первой команды: ");
        String team_name_1=reader.readLine();
        print("\nВведите город первой команды: ");
        String team_city_1=reader.readLine();


        print("\nВведите название второй команды: ");
        String team_name_2=reader.readLine();
        print("\nВведите город второй команды: ");
        String team_city_2=reader.readLine();

        playing1.team=teamRepository.get_team_by_param(team_name_1,team_city_1);
        playingRepository.save(playing1);

        playing2.team=teamRepository.get_team_by_param(team_name_2,team_city_2);
        playingRepository.save(playing2);

        return true;
    }
    public boolean add_team_match() throws IOException, ParseException {

        TeamMatch team_match=new TeamMatch();

        print("\nВведите дату матча в формате ДД/ММ/ГГГГ: ");
        String date=reader.readLine();
        Date date_match=string_in_date(date);

        print("\nВведите название команды: ");
        String team_name=reader.readLine();
        print("\nВведите город команды: ");
        String team_city=reader.readLine();

        team_match.team=teamRepository.get_team_by_param(team_name,team_city);

        team_match.match=matchRepository.get_match_by_date_team(date_match, team_match.team.id);

        //вывод игроков
        System.out.print(playerRepository.get_players_by_team(team_match.team.id));


        print("\nПоочерёдно вводите номера игроков для добавления, когда закончите введите \"end\": ");

        String number="";
        number = reader.readLine();
        while (!(number.equals("end"))) {

            team_match.player=playerRepository.get_player_by_team_and_number(team_match.team.id,number);
            team_match.number=number;
            teamMatchRepository.save(team_match);
            print("\nИгрок успешно добавлен");
            print("\nВведите номер игрока для добавления, когда закончите введите \"end\": ");
            number = reader.readLine();
        }

        return true;
    }
    public boolean add_goal() throws IOException, ParseException {

        Goal goal=new Goal();

        print("\nВведите дату матча в формате ДД/ММ/ГГГГ: ");
        String date=reader.readLine();
        Date date_match=string_in_date(date);

        print("\nВведите название стадиона на котором проходит матч: ");
        String name_stadium=reader.readLine();
        print("\nВведите город в котором проходит матч: ");
        String city=reader.readLine();

        goal.match=matchRepository.get_match_by_param(date_match,stadiumRepository.get_id(name_stadium,city));

        print("\nВведите ФИО футболиста забившего гол: ");
        String name_player=reader.readLine();

        goal.player=teamMatchRepository.get_player_by_match_name(goal.match.id,name_player);

        print("\nСколько очков заработал игрок забивший в ворота для своей команды?(0/1): ");
        goal.score=Integer.parseInt (reader.readLine());

        print("\nВведите время в которое был забит гол в формате: ЧЧ:ММ:CC");

        goal.time=string_in_time(reader.readLine());

        goalRepository.save(goal);

        return true;
    }

}