package day7;

public class NumberConverter {
    public int from_10_to_n(int base, int newbase) {
        int k = 0;
        String res = "";
        String output = "";

        do {                        //Below I explain what I'm doing here
            base /= newbase;
            k = base % newbase;
            res += String.valueOf(k);
        } while (base != 0);

        for (int i = res.length() - 1; i > -1; i--) {
            output += res.charAt(i);
        }

        return Integer.parseInt(output);
    }
}
