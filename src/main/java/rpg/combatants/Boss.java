package rpg.combatants;

public class Boss extends Enemy{
    public Boss(String name) {
        super(name);
        this.damage = 30;
        this.sign = "\uD83D\uDC79";
    }
    @Override
    public void beHit(int percent){
        percent -= 8;
        super.beHit(percent);
    }


}
