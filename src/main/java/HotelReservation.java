import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotelReservation {
    public static void main(String[] args) {
        Scanner scstr = new Scanner(System.in);
        System.out.println("Olá! Bem vindo(a) ao nosso sistema de procura de hotéis mais baratos! :)");
        System.out.println("Por favor digite, nessa ordem, quais DIA/MÊS/ANO/DIA DA SEMANA deseja fazer uma reserva!");
        System.out.println("Exemplo --> Regular: 16Mar2009(mon) 06Mar2009(fri) 07Mar2009(sat)");
        System.out.println("Exemplo p/ Fidelidade --> Rewards: 16Mar2009(mon) 06Mar2009(fri) 07Mar2009(sat)");
        String input = scstr.nextLine();
        System.out.println(getCheapestHotel(input));
    }

    public static String getCheapestHotel(String input) {

        int lakewood = 0, bridgewood = 0, ridgewood = 0;
        Set<String> laborDays = new HashSet<>(Arrays.asList("mon", "tues", "wed", "thur", "fri"));
        int laborDaysCount = 0, weekendDaysCount = 0;

        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            if (laborDays.contains(matcher.group(1))) {
                laborDaysCount++;
            } else {
                weekendDaysCount++;
            }
        }

        if (input.substring(0, 7).equals("Regular")) {
            lakewood = (110 * laborDaysCount) + (90 * weekendDaysCount);
            bridgewood = (160 * laborDaysCount) + (60 * weekendDaysCount);
            ridgewood = (220 * laborDaysCount) + (150 * weekendDaysCount);
        } else {
            lakewood = (80 * laborDaysCount) + (80 * weekendDaysCount);
            bridgewood = (110 * laborDaysCount) + (50 * weekendDaysCount);
            ridgewood = (100 * laborDaysCount) + (40 * weekendDaysCount);
        }

        if (lakewood < bridgewood && lakewood < ridgewood) {
            return "Lakewood";
        } else if (bridgewood < lakewood && bridgewood < ridgewood) {
            return "Bridgewood";
        }
        return "Ridgewood";
    }
}