import static java.lang.Math.floor;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class DnDCharacter {

   private Random random = new Random();
   private int strength = ability();
   private int dexterity = ability();
   private int constitution = ability();
   private int intelligence = ability();
   private int wisdom = ability();
   private int charisma = ability();
   private int hitpoints = 10 + modifier(constitution);

   int ability() {
      List<Integer> list = random.ints(4, 1, 6)
         .boxed()
         .sorted()
         .collect(Collectors.toList());
      return list.subList(1, list.size()).stream().mapToInt(i -> i).sum();
   }

   int modifier(int input) {
      return (int) floor((input - 10) / 2.0);
   }

   public int getStrength() {
      return strength;
   }

   public int getDexterity() {
      return dexterity;
   }

   public int getConstitution() {
      return constitution;
   }

   public int getIntelligence() {
      return intelligence;
   }

   public int getWisdom() {
      return wisdom;
   }

   public int getCharisma() {
      return charisma;
   }

   public int getHitpoints() {
      return hitpoints;
   }
}
