package chapter5;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;


public class LocalizationPlugg {

  public static void main(String[] args) {

    //Checking current Locale
    Locale defaultLocale = Locale.getDefault();
    System.out.println(defaultLocale); //sv_SE



    //Locale constants
    Locale germanLocale = Locale.GERMAN;
    Locale germanyLocale = Locale.GERMANY;

    //Creating a locale
    Locale frenchLocale = new Locale("fr");
    Locale franceLocale = new Locale("fr_FR");

    //Creating with a builder design pattern
    Locale swedenLocale = new Locale.Builder()
        .setLanguage("se")
        .setRegion("SE")
        .build();

    //Changinig locale
    Locale.setDefault(swedenLocale);

    //Using a resource bundle
    ResourceBundle rb1 = ResourceBundle.getBundle("LocalizationPlugg",frenchLocale);
    System.out.println(rb1.getString("hello")); //Bonjour
    ResourceBundle rb2 = ResourceBundle.getBundle("LocalizationPlugg",swedenLocale);
    System.out.println(rb2.getString("hello")); //Hej	pa dig
    System.out.println(rb2.getString("open")); //Oppet
                                               //idag
    ResourceBundle rb3 = ResourceBundle.getBundle("LocalizationPlugg",germanLocale);
    System.out.println(rb3.getString("hello")); //Hej pa dig

    //Looping through the key-value pairs in a resource bundle
    ResourceBundle rb4 = ResourceBundle.getBundle("LocalizationPlugg",Locale.ENGLISH);
    Set<String> enKeys = rb4.keySet();
    enKeys.stream().map(k->k + " " +rb4.getString(k)).forEach(System.out::println);
    //hello Hello
    //open open

    //
    ResourceBundle rb5 = ResourceBundle.getBundle("resourcebundles.Tax",Locale.US);
    System.out.println(rb5.getString("hello"));

  }
}
