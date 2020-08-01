package main.htmlfileLogger.web;

public class GenHTML {

    public static String getIndexForTabs(int noFTabs, int[] noFSubTabs, String[] identities, String [][] headers, String [][] contents) {
        StringBuilder builder = new StringBuilder();

        // Writing Header
        {
            builder.append("<!DOCTYPE html>\n")
                    .append("<html lang=\"en\" >\n")
                    .append("<head>\n")
                    .append("  <meta charset=\"UTF-8\">\n")
                    .append("  <title>CodePen - Expanding Column Layout</title>\n")
                    .append("  <link rel=\"stylesheet\" href=\"./font-awesome.min.css\">\n")
                    .append("\n")
                    .append("<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\"><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css\">\n")
                    .append("<link rel=\"stylesheet\" href=\"./style.css\">\n")
                    .append("\n")
                    .append("</head>\n")
                    .append("<body>\n")
                    .append("<!-- partial:index.partial.html -->\n")
                    .append("<section class=\"strips\">\n");
        }

        // Writing for each tab
        {
            for (int i = 0; i < noFTabs; i++) {
                builder.append("<article class=\"strips__strip\">\n")
                        .append("    <div class=\"strip__content\">\n")
                        .append("      <h1 class=\"strip__title\" data-name=\"" + i + "\">" + identities[i] + "</h1>\n")
                        .append("      <div class=\"strip__inner-text\">\n")

                        .append(GenTabsHTML.getTabsSet(noFSubTabs[i], identities[i], headers[i], contents[i]))

                        .append("      </div>\n")
                        .append("      \n")
                        .append("    </div>\n")
                        .append("  </article>\n");
            }
        }

        // Writing the end
        {
            builder.append("<i class=\"fa fa-close strip__close\"></i>\n")
                    .append("</section>\n")
                    .append("<!-- partial -->\n")
                    .append("  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>\n")
                    .append("<script src='https://cdn.jsdelivr.net/npm/vue@2.6.11/dist/vue.js'></script>\n")
                    .append("<script  src=\"./script.js\"></script>\n")
                    .append("\n")
                    .append("</body>\n")
                    .append("</html>");
        }

        return builder.toString();

    }

}
