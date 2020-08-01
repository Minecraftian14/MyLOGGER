package main.utlities;

public class Html {

    StringBuilder builder;

    public Html() {
        builder = new StringBuilder();
    }

    public Html BOLD(String in) {
        builder.append("<strong>").append(in).append("</strong>");
        return this;
    }

    public Html ITAL(String in) {
        builder.append("<em>").append(in).append("</em>");
        return this;
    }

    public Html UNDE(String in) {
        builder.append("<ins>").append(in).append("</ins>");
        return this;
    }

    public Html MARK(String in) {
        builder.append("<mark>").append(in).append("</mark>");
        return this;
    }

    public Html SMAL(String in) {
        builder.append("<small>").append(in).append("</small>");
        return this;
    }

    public Html DELE(String in) {
        builder.append("<del>").append(in).append("</del>");
        return this;
    }

    public Html SUBS(String in) {
        builder.append("<sub>").append(in).append("</sub>");
        return this;
    }

    public Html SUPE(String in) {
        builder.append("<sup>").append(in).append("</sup>");
        return this;
    }

    public Html ABBR(String text, String disp) {
        builder.append("<abbr title=\"").append(text).append("\">").append(disp).append("</abbr>");
        return this;
    }

    public Html COLO(String text, int r, int g, int b) {

        builder.append("<font color=\"").append(PatchToTwoUnits(Integer.toHexString(r))).append(PatchToTwoUnits(Integer.toHexString(g))).append(PatchToTwoUnits(Integer.toHexString(b))).append("\">").append(text).append("</font>");
        return this;
    }

    public static String PatchToTwoUnits(String hex) {
        if (hex.length() == 2) return hex;
        if (hex.length() == 1) return "0" + hex;
        if (hex.length() == 0) return "00";
        System.out.println("What the heck!! Only hex values of range 0 to ff are allowed! This is wrong " + hex);
        return "00";
    }

    public static class Wrap {

        public static String BOLD(String in) {
            return "<strong>" + in + "</strong>";
        }

        public static String ITAL(String in) {
            return "<em>" + in + "</em>";
        }

        public static String UNDE(String in) {
            return "<ins>" + in + "</ins>";
        }

        public static String MARK(String in) {
            return "<mark>" + in + "</mark>";
        }

        public static String SMAL(String in) {
            return "<small>" + in + "</small>";
        }

        public static String DELE(String in) {
            return "<del>" + in + "</del>";
        }

        public static String SUBS(String in) {
            return "<sub>" + in + "</sub>";
        }

        public static String SUPE(String in) {
            return "<sup>" + in + "</sup>";
        }

        public static String BREK(String in) {
            return in + "<br />";
        }

        public static String ABBR(String text, String disp) {
            return "<abbr title=\"" + text + "\">" + disp + "</abbr>";
        }

        public static String COLO(String text, int r, int g, int b) {
            return "<font color=\"" + PatchToTwoUnits(Integer.toHexString(r)) + PatchToTwoUnits(Integer.toHexString(g)) + PatchToTwoUnits(Integer.toHexString(b)) + "\">" + text + "</font>";
        }

        public static String COLO(String text, String colour) {
            return "<font color=\"#" + colour + "\">" + text + "</font>";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Html().COLO("Hello", 255, 0, 0));
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
