package com.mcxiv.logger;

public class DefaultLoggers {

    private static Log s_log;
    private static Log h_log;
    private static Log di_log;
    private static Log hdi_log;

    public static Log getSimpleLogger() {
        if (s_log == null) s_log = new Logger_FormattedUniqueMethodAdaptations();
        return s_log;
    }

    public static Log getHeadedLogger() {
        if (h_log == null) h_log = new Logger_DoubleFormattedUniqueMethodAdaptations();
        return h_log;
    }

    public static Log getDILogger() {
        if (di_log == null) di_log = new Logger_AssembliedUniqueMethodAdaptations();
        return di_log;
    }

    public static Log getHeadedDILogger() {
        if (hdi_log == null) hdi_log = new Logger_HeadedAssembliedUniqueMethodAdaptations();
        return hdi_log;
    }

}
