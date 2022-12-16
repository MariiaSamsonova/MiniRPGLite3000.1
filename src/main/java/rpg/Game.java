package rpg;


import rpg.combatants.*;
import rpg.stuff.consumables.*;
import utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private int n;
    public static InputParser ip = new ConsoleParser();//TODO or GUIParser| переименовать
    private int fight;
    public List<Combatant> combatants;//TODO в порядке хода
    public Food food;
    public Potions potions;

    public Game() {
        this.fight = 0;
        this.combatants = new ArrayList<>();
    }

    public int getFightNumber() {
        return fight + 1;
    }

    public void setEnemies(int n) {
        this.combatants = this.combatants.subList(0, countHeroes());
        for (int i = 0; i < n + this.fight; i++) {
            Enemy enemy = createEnemy(String.valueOf(i + 1));
            this.combatants.add(enemy);
            if(enemy instanceof Boss){
                return;
            }
        }
    }

    private Enemy createEnemy(String name) {
        if(this.getFightNumber() == 4){//TODO 5
            return new Boss(name);
        }
        return new Enemy(name);
    }// TODO на последнем уровне босс

    public void setHeroes(List<Hero> heroesClasses) {
        this.n = heroesClasses.size();
        this.combatants.addAll(heroesClasses);
        this.food = new Food(countHeroes());//TODO diff amount
        this.potions = new Potions(countHeroes());
    }

    public static Hero createHero(String heroClass, String name) {//TODO enum (или посмотреть паттерны)

        switch (heroClass) {
            case "Warrior", "warrior", "1":
                return new Warrior(name);
            case "Mage", "mage", "2":
                return new Mage(name);
            case "Healer", "healer", "4":
                return new Healer(name);
            case "Hunter", "hunter", "3":
                return new Hunter(name);
            default:
                return null;
        }

    }

    public Enemy choseEnemy() {
        while (true){
            InputParser ip = new ConsoleParser();
            ip.printl("Enter the number of enemy whom you want to hit");
            int enemyNumber = ip.getInteger();
            if(enemyNumber <= this.n + this.fight && this.combatants.get(countHeroes() + enemyNumber - 1).isAlive())
            {
                return (Enemy) this.combatants.get(countHeroes() + enemyNumber - 1);
            }
        }

    }

    public int countEnemies() {
        int n = 0;
        for (int i = countHeroes(); i < this.combatants.size(); i++) {
            if (this.combatants.get(i).isAlive()) {
                n++;
            }
        }
        return n;
    }

    public int countHeroes() {
        int n = 0;
        for (int i = 0; i < this.combatants.size() && this.combatants.get(i) instanceof Hero; i++) {
            if (this.combatants.get(i).isAlive()) {
                n++;
            }
        }
        return n;
    }

    public List setMoveOrder() {

        List<Integer> order = new ArrayList<>();
        for (int i = this.combatants.size() - 1; i >= 0; i--) {
            order.add(i);
        }
        Collections.shuffle(order);
        printCombatantsByMoveOrder(order);
        return order;

    }

    public void endFight() {

        if (this.countHeroes() > 0) {
            ip.printl("WIN in the fight №" + (this.fight + 1));

            for(int i = 0; i < countHeroes(); i ++)
            {
                Hero hero = (Hero) combatants.get(i);

                    this.ip.printl("Congratulations, now you can improve your efficiency");
                    hero.doAction(this);
                }
                ip.printl("Next fight: ");
            }else {
            ip.printl("GameOver");
        }
        this.fight++;
    }


    public Hero choseHero() {
        while (true){
            InputParser ip = new ConsoleParser();
            ip.printl("Enter the name of hero whom you want to heal");
            String heroName = ip.getString();
            for (Combatant hero : this.combatants) {
                if (hero.getName().equals(heroName)) {
                    return (Hero) hero;
                }
                if (hero instanceof Enemy) break;
            }
        }

    }

    public void printGameInfo() {
        this.ip.printl("Food: " + this.food.toString() + " Potions:" + this.potions.toString());
    }

    public void printCombatantsByMoveOrder(List<Integer> order) {
        for (int i : order) {
            this.ip.print(' ' + this.combatants.get(i).toString());
        }

        this.ip.printl("");
    }
}
