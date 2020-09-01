package main.htmlfileLogger.web;

public class GenTabsJS {

    public static String getTabsJS(int noftabs, int[] nofSubTabs, String[] identities) {
        StringBuilder builder = new StringBuilder();

        builder.append("var Expand = (function() {\n")
               .append("  var tile = $('.strips__strip');\n")
               .append("  var tileLink = $('.strips__strip > .strip__content');\n")
               .append("  var tileText = tileLink.find('.strip__inner-text');\n")
               .append("  var stripClose = $('.strip__close');\n")
               .append("  \n")
               .append("  var expanded  = false;\n")
               .append("\n")
               .append("  var open = function() {\n")
               .append("      \n")
               .append("    var tile = $(this).parent();\n")
               .append("\n")
               .append("      if (!expanded) {\n")
               .append("        tile.addClass('strips__strip--expanded');\n")
               .append("        // add delay to inner text\n")
               .append("        tileText.css('transition', 'all .5s .3s cubic-bezier(0.23, 1, 0.32, 1)');\n")
               .append("        stripClose.addClass('strip__close--show');\n")
               .append("        stripClose.css('transition', 'all .6s 1s cubic-bezier(0.23, 1, 0.32, 1)');\n")
               .append("        expanded = true;\n")
               .append("      } \n")
               .append("    };\n")
               .append("  \n")
               .append("  var close = function() {\n")
               .append("    if (expanded) {\n")
               .append("      tile.removeClass('strips__strip--expanded');\n")
               .append("      // remove delay from inner text\n")
               .append("      tileText.css('transition', 'all 0.15s 0 cubic-bezier(0.23, 1, 0.32, 1)');\n")
               .append("      stripClose.removeClass('strip__close--show');\n")
               .append("      stripClose.css('transition', 'all 0.2s 0s cubic-bezier(0.23, 1, 0.32, 1)')\n")
               .append("      expanded = false;\n")
               .append("    }\n")
               .append("  }\n")
               .append("\n")
               .append("    var bindActions = function() {\n")
               .append("      tileLink.on('click', open);\n")
               .append("      stripClose.on('click', close);\n")
               .append("    };\n")
               .append("\n")
               .append("    var init = function() {\n")
               .append("      bindActions();\n")
               .append("    };\n")
               .append("\n")
               .append("    return {\n")
               .append("      init: init\n")
               .append("    };\n")
               .append("\n")
               .append("  }());\n")
               .append("\n")
               .append("Expand.init();\n");
        
        for (int i = 0; i < noftabs; i++) {
            builder.append("\n\nnew Vue({\n")
                    .append("    el: '#app").append(identities[i]).append("',\n")
                    .append("    data: {\n")
                    .append("        active: 0,\n")
                    .append("        animation: 'fadeUp',\n")
                    .append("        previous: 0,\n")
                    .append("        className: ''\n")
                    .append("    },\n")
                    .append("    methods: {\n")
                    .append("        selectedContent(index) {\n")
                    .append("            this.active = index;\n")
                    .append("\n")
                    .append("            if(index > this.previous) {\n")
                    .append("                this.animation = 'fadeUp';\n")
                    .append("            }\n")
                    .append("\n")
                    .append("            if(index < this.previous) {\n")
                    .append("                this.animation = 'fadeDown';\n")
                    .append("            }\n")
                    .append("\n")
                    .append("            this.previous = index;\n")
                    .append("\n");

            for (int j = 0; j < nofSubTabs[i]; j++) {
                builder.append("            if(index === ").append(j).append(") {\n")
                        .append("                this.className = '").append(identities[i]).append(GenTabsCSS.char_list.get(j)).append("'\n")
                        .append("            }\n");
            }

            builder.append("        }\n")
                    .append("    }\n")
                    .append("})");
        }

        return builder.toString();
    }

}
