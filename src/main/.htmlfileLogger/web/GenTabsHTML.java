package main.htmlfileLogger.web;

public class GenTabsHTML {

    public static String getTabsSet(int noFSubTabs, String identity, String[] headers, String[] content) {
        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"container\" id=\"app").append(identity).append("\">\n")
                .append("  <div class=\"tab\">\n")
                .append("    \n")
                .append("    <div class=\"menu").append(identity).append("\">\n");

        for (int i = 0; i < noFSubTabs; i++) {
            builder.append("      <div class=\"menu__item menu__item--").append(identity).append(GenTabsCSS.char_list.get(i)).append("\" :class=\"[ active === ").append(i).append(" ? 'is-active' : '']\" @click=\"selectedContent(").append(i).append(")\">").append(headers[i]).append("</div>\n");
        }

        builder.append("      <div class=\"menu__line\" :class=\"className\"></div>\n")
                .append("    </div>\n")
                .append("    \n")
                .append("    <div class=\"contents\">\n")
                .append("      <transition :name=\"animation\" mode=\"out-in\">\n\n");

        for (int i = 0; i < noFSubTabs; i++) {
            builder.append("        <div class=\"contents__content\" v-if=\"active === ").append(i).append("\" key=\"").append(i).append("\">\n")
//                    .append("          <span class=\"material-icons\">build</span>\n")
                    .append("          <h2>").append(headers[i]).append("</h2>\n")
                    .append("          <p>").append(content[i]).append("</p>\n")
//                    .append("          <button>Learn more</button>\n")
                    .append("        </div>\n\n");
        }

        builder.append("      </transition>\n")
                .append("    </div>\n")
                .append("  </div>\n")
                .append("</div>");

        return builder.toString();
    }

}
