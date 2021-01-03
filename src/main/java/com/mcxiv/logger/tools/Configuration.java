package com.mcxiv.logger.tools;

public class Configuration {

    public String g;
    public String n;
    public String i;
    public String a;
    public String e;
    public String s;

    public static Configuration getSimpleFormatDefault() {
        Configuration DEFAULT = new Configuration();

        DEFAULT.g = C.B + "%s" + C.RS + System.lineSeparator();
        DEFAULT.n = C.M + "%s" + C.RS + System.lineSeparator();
        DEFAULT.i = C.G + "%s" + C.RS + System.lineSeparator();
        DEFAULT.a = C.FR + C.C + " %s " + C.RS + System.lineSeparator();
        DEFAULT.e = C.R + C.FU + "%s" + C.RS + System.lineSeparator();
        DEFAULT.s = C.Y + "%s" + C.RS;

        return DEFAULT;
    }

    public static Configuration getPrependableDefault() {
        Configuration DEFAULT = new Configuration();

        DEFAULT.g = C.B;
        DEFAULT.n = C.M;
        DEFAULT.i = C.G;
        DEFAULT.a = C.FR + C.C;
        DEFAULT.e = C.R + C.FU;
        DEFAULT.s = C.Y;

        return DEFAULT;
    }

    public static Configuration getHeadedPrependableDefault() {
        Configuration DEFAULT = new Configuration();

        DEFAULT.g = C.BBG + C.getFontColor(C.hexToGray(255)) + " GENERAL   " + C.RS + C.R + " " + C.B;
        DEFAULT.n = C.MBG + C.getFontColor(C.hexToGray(255)) + " NOTICE    " + C.RS + C.R + " " + C.M;
        DEFAULT.i = C.GBG + C.getFontColor(C.hexToGray(255)) + " INFO      " + C.RS + C.R + " " + C.G;
        DEFAULT.a = C.CBG + C.getFontColor(C.hexToGray(255)) + " ADVERTISE " + C.RS + C.R + C.FR + C.C;
        DEFAULT.e = C.RBG + C.getFontColor(C.hexToGray(255)) + " ERROR     " + C.RS + C.R + " " + C.FU;
        DEFAULT.s = C.YBG + C.getFontColor(C.hexToGray(255)) + " STATUS    " + C.RS + C.R + " " + C.Y;

        return DEFAULT;
    }

    public static Configuration getDoubleFormatDefault() {
        Configuration DEFAULT = new Configuration();

        DEFAULT.g = C.BBG + C.getFontColor(C.hexToGray(255)) + " GENERAL   " + C.RS + C.B /*              */ + " %s" + C.RS /*  */ + System.lineSeparator();
        DEFAULT.n = C.MBG + C.getFontColor(C.hexToGray(255)) + " NOTICE    " + C.RS + C.M /*              */ + " %s" + C.RS /*  */ + System.lineSeparator();
        DEFAULT.i = C.GBG + C.getFontColor(C.hexToGray(255)) + " INFO      " + C.RS + C.G /*              */ + " %s" + C.RS /*  */ + System.lineSeparator();
        DEFAULT.a = C.CBG + C.getFontColor(C.hexToGray(255)) + " ADVERTISE " + C.RS + C.FR + C.C /*       */ + " %s " + C.RS /* */ + System.lineSeparator();
        DEFAULT.e = C.RBG + C.getFontColor(C.hexToGray(255)) + " ERROR     " + C.RS + C.R + " " + C.FU /* */ + "%s" + C.RS /*   */ + System.lineSeparator();
        DEFAULT.s = C.YBG + C.getFontColor(C.hexToGray(255)) + " STATUS    " + C.RS + C.Y /*              */ + " %s" + C.RS;

        return DEFAULT;
    }
}
