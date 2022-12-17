package rpg.combatants;

import rpg.Game;

public /*abstract*/ class Enemy extends Combatant {//TODO добавить рядового монсра и босса


    public Enemy(String name) {
        super(name);
        this.sign = "\uD83D\uDC7E";
        this.damage = 10;//TODO 10
    }



    @Override
    public void makeMove(Game game){
        while (true){
            int randomPlayer = (int) Math.round(Math.random() * (game.countHeroes() - 1));
            if(game.combatants.get(randomPlayer).isAlive())
            {
                Hero hero = (Hero) game.combatants.get(randomPlayer);
                game.ip.printl("Enemy №" + getName() + " hit " + hero.getName());
                hero.beHit(this.damage);
                break;
            }
        }
    }



}
