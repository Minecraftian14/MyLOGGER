package main.htmlfileLogger.web;

import java.util.ArrayList;

public class GenCSS {

    public static void main(String[] args) {
        System.out.println(GenCSS.getStyleForTabs(3, new int[]{2, 5, 7}, new String[]{"a", "b", "c"}));
    }

    public static String getStyleForTabs(int noFTabs, int[] noFSubTabs, String[] identities) {
        StringBuilder builder = new StringBuilder();

        float tab_width = 100f / noFTabs;
        ArrayList<String> colours_used = new ArrayList<>(noFTabs);

        // Writing Header
        {
            builder.append("* {\n")
                    .append("  box-sizing: border-box;\n")
                    .append("}\n")
                    .append("\n")
                    .append(".strips {\n")
                    .append("  min-height: 100vh;\n")
                    .append("  text-align: center;\n")
                    .append("  overflow: hidden;\n")
                    .append("  color: white;\n")
                    .append("}\n")
                    .append(".strips__strip {\n")
                    .append("  will-change: width, left, z-index, height;\n")
                    .append("  position: absolute;\n")
                    .append("  width: ").append(tab_width).append("%;\n")
                    .append("  min-height: 100vh;\n")
                    .append("  overflow: hidden;\n")
                    .append("  cursor: pointer;\n")
                    .append("  -webkit-transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("  transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("}\n");
        }

        // Writing strips__strip
        {
            for (int i = 0; i < noFTabs; i++) {
                builder.append(".strips__strip:nth-child(").append((i + 1)).append(") {\n")
                        .append("    left: ").append((i * tab_width)).append("vw;\n")
                        .append("}\n");
            }
        }

        // Writing strip__content
        {
            String color = getRandomColor();
            colours_used.add(color);
            builder.append(".strips__strip:nth-child(1) .strip__content {\n")
                    .append("  background: ").append(color).append(";\n")
                    .append("  -webkit-transform: translate3d(-100%, 0, 0);\n")
                    .append("          transform: translate3d(-100%, 0, 0);\n")
                    .append("  -webkit-animation-name: strip1;\n")
                    .append("          animation-name: strip1;\n")
                    .append("  -webkit-animation-delay: 0.1s;\n")
                    .append("          animation-delay: 0.1s;\n")
                    .append("}\n");

            for (int i = 1; i < noFTabs - 1; i++) {
                color = getRandomColor();
                colours_used.add(color);
                builder.append(".strips__strip:nth-child(").append((i + 1)).append(") .strip__content {\n")
                        .append("  background: ").append(color).append(";\n")
                        .append("  -webkit-transform: translate3d(0, ").append((i % 2 == 0 ? "-" : "")).append("100%, 0);\n")
                        .append("          transform: translate3d(0, ").append((i % 2 == 0 ? "-" : "")).append("100%, 0);\n")
                        .append("  -webkit-animation-name: strip").append((i + 1)).append(";\n")
                        .append("          animation-name: strip").append((i + 1)).append(";\n")
                        .append("  -webkit-animation-delay: ").append((i + 1f) / 10f).append("s;\n")
                        .append("          animation-delay: ").append((i + 1f) / 10f).append("s;\n")
                        .append("}\n");
            }

            if (noFTabs > 1) {
                color = getRandomColor();
                colours_used.add(color);
                builder.append(".strips__strip:nth-child(").append(noFTabs).append(") .strip__content {\n")
                        .append("  background: ").append(color).append(";\n")
                        .append("  -webkit-transform: translate3d(").append((noFTabs % 2 == 0 ? "-" : "")).append("100%, 0, 0);\n")
                        .append("          transform: translate3d(").append((noFTabs % 2 == 0 ? "-" : "")).append("100%, 0, 0);\n")
                        .append("  -webkit-animation-name: strip").append(noFTabs).append(";\n")
                        .append("          animation-name: strip").append(noFTabs).append(";\n")
                        .append("  -webkit-animation-delay: ").append(noFTabs / 10f).append("s;\n")
                        .append("          animation-delay: ").append(noFTabs / 10f).append("s;\n")
                        .append("}\n");
            }
        }

        // Writing Mid Line (as ML)
        {
            builder.append("@media screen and (max-width: 760px) {\n")
                    .append("  .strips__strip {\n")
                    .append("    min-height: 20vh;\n") // Todo is it <20> or <").append((i * tab_width)).append("> ?
                    .append("  }\n");
        }

        // Writing post ML strips__strip
        {
            for (int i = 0; i < noFTabs; i++) {
                builder.append(".strips__strip:nth-child(").append((i + 1)).append(") {\n")
                        .append("    top: ").append((i * tab_width)).append("vh;\n")
                        .append("    left: 0;\n")
                        .append("    width: 100%;\n")
                        .append("  }\n");
            }
        }

        // Writing a heck load long text
        {
            builder.append("}\n")
                    .append(".strips .strip__content {\n")
                    .append("  -webkit-animation-duration: 1s;\n")
                    .append("          animation-duration: 1s;\n")
                    .append("  -webkit-animation-timing-function: cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("          animation-timing-function: cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("  -webkit-animation-fill-mode: both;\n")
                    .append("          animation-fill-mode: both;\n")
                    .append("  display: -webkit-box;\n")
                    .append("  display: flex;\n")
                    .append("  -webkit-box-align: center;\n")
                    .append("          align-items: center;\n")
                    .append("  -webkit-box-pack: center;\n")
                    .append("          justify-content: center;\n")
                    .append("  position: absolute;\n")
                    .append("  top: 0;\n")
                    .append("  left: 0;\n")
                    .append("  width: 100%;\n")
                    .append("  height: 100%;\n")
                    .append("  text-decoration: none;\n")
                    .append("}\n")
                    .append(".strips .strip__content:hover:before {\n")
                    .append("  -webkit-transform: skew(-30deg) scale(3) translate(0, 0);\n")
                    .append("          transform: skew(-30deg) scale(3) translate(0, 0);\n")
                    .append("  opacity: 0.1;\n")
                    .append("}\n")
                    .append(".strips .strip__content:before {\n")
                    .append("  content: \"\";\n")
                    .append("  position: absolute;\n")
                    .append("  z-index: 1;\n")
                    .append("  top: 0;\n")
                    .append("  left: 0;\n")
                    .append("  width: 100%;\n")
                    .append("  height: 100%;\n")
                    .append("  background: white;\n")
                    .append("  opacity: 0.05;\n")
                    .append("  -webkit-transform-origin: center center;\n")
                    .append("          transform-origin: center center;\n")
                    .append("  -webkit-transform: skew(-30deg) scaleY(1) translate(0, 0);\n")
                    .append("          transform: skew(-30deg) scaleY(1) translate(0, 0);\n")
                    .append("  -webkit-transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("  transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("}\n")
                    .append(".strips .strip__inner-text {\n")
                    .append("  will-change: transform, opacity;\n")
                    .append("  position: absolute;\n")
                    .append("  z-index: 5;\n")
                    .append("  top: 50%;\n")
                    .append("  left: 50%;\n")
                    .append("  width: 70%;\n")
                    .append("  -webkit-transform: translate(-50%, -50%) scale(0.5);\n")
                    .append("          transform: translate(-50%, -50%) scale(0.5);\n")
                    .append("  opacity: 0;\n")
                    .append("  -webkit-transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("  transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("}\n")
                    .append(".strips__strip--expanded {\n")
                    .append("  width: 100%;\n")
                    .append("  height: 3000%;\n") // TODO To add a calculated fixed height.
                    .append("  top: 0 !important;\n")
                    .append("  left: 0 !important;\n")
                    .append("  z-index: 3;\n")
                    .append("  cursor: default;\n")
                    .append("}\n")
                    .append("@media screen and (max-width: 760px) {\n")
                    .append("  .strips__strip--expanded {\n")
                    .append("    min-height: 100vh;\n")
                    .append("  }\n")
                    .append("}\n")
                    .append(".strips__strip--expanded .strip__content:hover:before {\n")
                    .append("  -webkit-transform: skew(-30deg) scale(1) translate(0, 0);\n")
                    .append("          transform: skew(-30deg) scale(1) translate(0, 0);\n")
                    .append("  opacity: 0.05;\n")
                    .append("}\n")
                    .append(".strips__strip--expanded .strip__title {\n")
                    .append("  opacity: 0;\n")
                    .append("}\n")
                    .append(".strips__strip--expanded .strip__inner-text {\n")
                    .append("  opacity: 1;\n")
                    .append("  -webkit-transform: translate(-50%, -50%) scale(1);\n")
                    .append("          transform: translate(-50%, -50%) scale(1);\n")
                    .append("}\n")
                    .append("\n")
                    .append(".strip__title {\n")
                    .append("  display: block;\n")
                    .append("  margin: 0;\n")
                    .append("  position: relative;\n")
                    .append("  z-index: 2;\n")
                    .append("  width: 100%;\n")
                    .append("  font-size: 3.5vw;\n")
                    .append("  color: white;\n")
                    .append("  -webkit-transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("  transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("}\n")
                    .append("@media screen and (max-width: 760px) {\n")
                    .append("  .strip__title {\n")
                    .append("    font-size: 28px;\n")
                    .append("  }\n")
                    .append("}\n")
                    .append("\n")
                    .append(".strip__close {\n")
                    .append("  position: absolute;\n")
                    .append("  right: 3vw;\n")
                    .append("  top: 3vw;\n")
                    .append("  opacity: 0;\n")
                    .append("  z-index: 10;\n")
                    .append("  -webkit-transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("  transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);\n")
                    .append("  cursor: pointer;\n")
                    .append("  -webkit-transition-delay: 0.5s;\n")
                    .append("          transition-delay: 0.5s;\n")
                    .append("}\n")
                    .append(".strip__close--show {\n")
                    .append("  opacity: 1;\n")
                    .append("}\n\n");
        }

        // Writing -webkit-keyframes
        {
            builder.append("@-webkit-keyframes strip1 {\n")
                    .append("  0% {\n")
                    .append("    -webkit-transform: translate3d(-100%, 0, 0);\n")
                    .append("            transform: translate3d(-100%, 0, 0);\n")
                    .append("  }\n")
                    .append("  100% {\n")
                    .append("    -webkit-transform: translate3d(0, 0, 0);\n")
                    .append("            transform: translate3d(0, 0, 0);\n")
                    .append("  }\n")
                    .append("}\n")
                    .append("\n")
                    .append("@keyframes strip1 {\n")
                    .append("  0% {\n")
                    .append("    -webkit-transform: translate3d(-100%, 0, 0);\n")
                    .append("            transform: translate3d(-100%, 0, 0);\n")
                    .append("  }\n")
                    .append("  100% {\n")
                    .append("    -webkit-transform: translate3d(0, 0, 0);\n")
                    .append("            transform: translate3d(0, 0, 0);\n")
                    .append("  }\n")
                    .append("}\n");

            for (int i = 1; i < noFTabs - 1; i++) {
                builder.append("@-webkit-keyframes strip").append((i + 1)).append(" {\n")
                        .append("  0% {\n")
                        .append("    -webkit-transform: translate3d(0, ").append((i % 2 == 0 ? "-" : "")).append("100%, 0);\n")
                        .append("            transform: translate3d(0, ").append((i % 2 == 0 ? "-" : "")).append("100%, 0);\n")
                        .append("  }\n")
                        .append("  100% {\n")
                        .append("    -webkit-transform: translate3d(0, 0, 0);\n")
                        .append("            transform: translate3d(0, 0, 0);\n")
                        .append("  }\n")
                        .append("}\n")
                        .append("\n")
                        .append("@keyframes strip").append((i + 1)).append(" {\n")
                        .append("  0% {\n")
                        .append("    -webkit-transform: translate3d(0, ").append((i % 2 == 0 ? "-" : "")).append("100%, 0);\n")
                        .append("            transform: translate3d(0, ").append((i % 2 == 0 ? "-" : "")).append("100%, 0);\n")
                        .append("  }\n")
                        .append("  100% {\n")
                        .append("    -webkit-transform: translate3d(0, 0, 0);\n")
                        .append("            transform: translate3d(0, 0, 0);\n")
                        .append("  }\n")
                        .append("}\n");
            }

            if (noFTabs > 1) {
                builder.append("@-webkit-keyframes strip").append(noFTabs).append(" {\n")
                        .append("  0% {\n")
                        .append("    -webkit-transform: translate3d(").append(((noFTabs - 1) % 2 == 0 ? "-" : "")).append("100%, 0, 0);\n")
                        .append("            transform: translate3d(").append(((noFTabs - 1) % 2 == 0 ? "-" : "")).append("100%, 0, 0);\n")
                        .append("  }\n")
                        .append("  100% {\n")
                        .append("    -webkit-transform: translate3d(0, 0, 0);\n")
                        .append("            transform: translate3d(0, 0, 0);\n")
                        .append("  }\n")
                        .append("}\n")
                        .append("\n")
                        .append("@keyframes strip").append(noFTabs).append(" {\n")
                        .append("  0% {\n")
                        .append("    -webkit-transform: translate3d(").append(((noFTabs - 1) % 2 == 0 ? "-" : "")).append("100%, 0, 0);\n")
                        .append("            transform: translate3d(").append(((noFTabs - 1) % 2 == 0 ? "-" : "")).append("100%, 0, 0);\n")
                        .append("  }\n")
                        .append("  100% {\n")
                        .append("    -webkit-transform: translate3d(0, 0, 0);\n")
                        .append("            transform: translate3d(0, 0, 0);\n")
                        .append("  }\n")
                        .append("}\n");
            }
        }

        // Writing the end
        {
            builder.append("body {\n")
                    .append("  font-family: 'Lato';\n")
                    .append("  -webkit-font-smoothing: antialiased;\n")
                    .append("  text-rendering: geometricPrecision;\n")
                    .append("  line-height: 1.5;\n")
                    .append("}\n")
                    .append("\n")
                    .append("h1, h2 {\n")
                    .append("  font-weight: 300;\n")
                    .append("}\n")
                    .append("h1 {\n")
                    .append("  transform: rotate(-90deg)\n")
                    .append("}\n")
                    .append("\n")
                    .append(".fa {\n")
                    .append("  font-size: 30px;\n")
                    .append("  color: white;\n")
                    .append("}\n")
                    .append("\n")
                    .append("h2 {\n")
                    .append("  font-size: 36px;\n")
                    .append("  margin: 0 0 16px;\n")
                    .append("}\n")
                    .append("\n")
                    .append("p {\n")
                    .append("  margin: 0 0 16px;\n")
                    .append("}\n\n\n\n\n\n");
        }

        // Writing CSS for animated tabs
        {
            builder.append(GenTabsCSS.getTabsStyleHeader());
            for (int i = 0; i < noFTabs; i++)
                builder.append(GenTabsCSS.getTabsStyleIndividualTabData(noFSubTabs[i], identities[i]));
        }

        return builder.toString();

    }

    private static String getRandomColor() {
        return "#" + getRandomHex() + getRandomHex() + getRandomHex();
    }

    private static String getRandomHex() {
        return Integer.toString(64 + (int) (127.5 * Math.random()), 16).toUpperCase();
    }

}
