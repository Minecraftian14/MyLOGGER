package main.htmlfileLogger.web.tests;

import main.htmlfileLogger.web.GenCSS;

import java.io.IOException;

public class CreatingRandomWebs {

    public static int noFTabs = 20;

    public static void main(String[] args) throws IOException {

        int no_of_tabs = 3;
        int[] no_of_sub_tabs = new int[]{2, 4, 3};

        String[] identies = new String[]{"idA", "idB", "idC"};

        String[][] headers = new String[][]{{"A", "B"}, {"C", "D", "E", "F"}, {"G", "H", "I"}};
        String[][] contents = new String[][]{{"a", "b"}, {"c", "d", "e", "f"}, {"g", "h", "i"}};

//        String out = GenHTML.getIndexForTabs(no_of_tabs, no_of_sub_tabs, identies, headers, contents);
//        String out = GenTabsJS.getTabsJS(no_of_tabs, no_of_sub_tabs, identies);
        String  out = GenCSS.getStyleForTabs(no_of_tabs, no_of_sub_tabs, identies);

        System.out.println(out);

    }


}
