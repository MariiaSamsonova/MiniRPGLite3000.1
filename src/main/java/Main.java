import rpg.Game;
import rpg.combatants.Hero;
import utils.InputParser;

import java.util.ArrayList;
import java.util.List;

public class Main {

    InputParser ip;
    public static void main(String args[]) throws Exception {//TODO это должно быть в Game но это не точно // TODO delete Exception
        Game game = new Game();//TODO move ip to main

        game.ip.printl("Enter number of heroes");
        int n = game.ip.getInteger();

        List<Hero> heroes = new ArrayList<Hero>();
        for (int i = 1; i <= n; i++) {
            Hero hero = null;

            game.ip.printl("Enter name of hero №" + i);
            String name = game.ip.getString();

            while (hero == null) {
                game.ip.printl("Enter class of hero №" + i + " (1 - warrior, 2 - mage, 3 - hunter, 4 - healer)");
                hero = Game.createHero(game.ip.getString(), name);
            }
            heroes.add(hero);
        }
        game.setHeroes(heroes);

        while (game.getFightNumber() < 5 && game.countHeroes() > 0) {//default number of fights = 5 TODO set number of fights

            game.setEnemies(n);
            game.ip.printl(game.combatants.toString());

            game.ip.doSomethingToContinue();
            game.ip.printl("Fight №" + game.getFightNumber());
            game.ip.printl("Set the queue of moves:");
            List<Integer> order = game.setMoveOrder();
            Thread.sleep(900);


            while (game.countHeroes() > 0 && game.countEnemies() > 0)//1 fight
            {
                for (int i : order) {
                    if (game.combatants.get(i).isAlive()) {
                        game.combatants.get(i).makeMove(game);

                        game.printCombatantsByMoveOrder(order);
                        game.ip.doSomethingToContinue();
                    }
                    if(game.countHeroes() == 0 && game.countEnemies() == 0)
                    {
                        break;
                    }
                }
            }
            game.endFight();
        }
    }
}
