package main.utlities;

import java.util.ArrayList;
import java.util.Arrays;

public class Format {

    ArrayList<FormatType> format_list = new ArrayList<>();

    public static final FormatType NullFor = s -> s;

    public Format(FormatType... formats) {
        format_list.addAll(Arrays.asList(formats));
    }

    public String format(String... msgs) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Math.min(msgs.length, format_list.size()); i++)
            msgs[i] = format_list.get(i).format(msgs[i]);
        for (String msg : msgs) builder.append(msg);
        return builder.toString();
    }

    public interface FormatType {
        String format(String s);
    }

    public static void main(String[] args) {
        Format format = new Format(a -> a + " ", a -> new Html().BOLD(a).toString(), a -> " " + a + " ", a -> new Html().UNDE(a).toString());
        System.out.println(format.format("Sucessfully added", "FRED", "to", "KITCHEN"));
        System.out.println(format.format("Sucessfully added", "MARK", "to", "CLEAN HOUSE"));
        System.out.println(format.format("Sucessfully added", "JASP", "to", "STORE"));
        System.out.println(format.format("Sucessfully added", "JILL", "to", "CELL"));
    }

}
