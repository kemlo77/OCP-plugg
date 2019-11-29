package kap7;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentCollections {


  public static void main(String[] args) {

    List<String> strList = Arrays
        .asList("Danni", "Arne", "Bodil", "Calle", "Elliot", "Fred", "Gert");
    SortedSet set = strList.parallelStream()
        .collect(ConcurrentSkipListSet::new, Set::add, Set::addAll);
    System.out.println(set); //[Arne, Bodil, Calle, Danni, Elliot, Fred, Gert]

    StringBuilder strb = strList.parallelStream()
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
    System.out.println(strb); //ArneBodilCalleDanniElliotFredGert


  }

}
