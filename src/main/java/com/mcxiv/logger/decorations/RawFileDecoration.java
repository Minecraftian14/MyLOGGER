package com.mcxiv.logger.decorations;

import com.mcxiv.logger.tools.C;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;

public class RawFileDecoration extends Decoration {

    private static Function<String[], Decoration> getPartnerDecoration = ConsoleDecoration::new;

    public static void setPartnerDecorationDecoration(Function<String[], Decoration> decoration) {
        getPartnerDecoration = decoration;
    }

    public static int MAX_BUFFER_SIZE = 200;

    Decoration decoration;
    FileWriter writer;
    StringBuilder buffer;


    //


    public RawFileDecoration(String... codes) {
        this(resolveFile(), codes);
    }

    private static File resolveFile() {
        new File("logs").mkdir();
        return new File("logs/" + LocalDateTime.now().toString().replaceAll("[^a-zA-Z0-9]", ",") + ".txt");
    }

    public RawFileDecoration(File file, String... codes) {
        if (codes.length == 0) return;
        this.decoration = getPartnerDecoration.apply(codes);

        try {
            writer = new FileWriter(file);
            buffer = new StringBuilder(MAX_BUFFER_SIZE);
            Runtime.getRuntime().addShutdownHook(new Thread(this::flush));
        } catch (IOException e) {
            System.out.println(C.RBG + C.hex.font.to24Bit(255, 255, 255) + "IO Exception " + C.RS + C.Y + e.getCause() + C.RS);
        }

        decorates = new Decorate[codes.length];

        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];


            //


            // Splitting up the formatting code to separate parts.
            DecorationCommonResolvers.FormattingCodeSplitter sp = new DecorationCommonResolvers.FormattingCodeSplitter(code);

            // And some basic initialisation.
            StringBuilder format = new StringBuilder(sp.prepre);
            Matcher m;


            //


            // Parsing Time Formatting
            DecorationCommonResolvers.TimeResolver timeResolver = new DecorationCommonResolvers.TimeResolver(sp.content);
            Supplier<String> timeFormatter = timeResolver.getTime();
            sp.content = timeResolver.content;


            //


            // Parsing other formatting chars

            if (sp.content.contains("~")) {
                last_one_repeats = true;
                repeater_index = i;
            }


            //


            // Finalising the static effects.

            format.append(sp.pre);

            // If a specific formatting like '%25s' or '%-25s' is provided, use it, else put a simple '%s'
            if ((m = re_formatting.matcher(sp.content)).find()) format.append(m.group(1));
            else format.append("%s");

            format.append(sp.suf).append(sp.sufsuf);

            // Apply as many new-lines as specified.
            for (int j = 0; j < sp.content.length(); j++)
                if (sp.content.charAt(j) == 'n') format.append('\n');


            //


            // Final 'form' to which the input is entered.
            final String form = format.toString();

            // Applying Time if defined/specified.
            if (timeFormatter != null)
                decorates[i] = s -> timeFormatter.get() + String.format(form, s);
            else
                decorates[i] = s -> String.format(form, s);

            // Applying center format if '%*25s' provided.
            if ((m = re_centerFormatting.matcher(sp.content)).find()) {

                int len = Integer.parseInt(m.group(1));

                final Decorate new_d = decorates[i];

                decorates[i] = s -> new_d.decorate(Decoration.center(len, s));

            }

            // Applying word Wrap
            if ((m = re_wordWrap.matcher(sp.content)).find()) {

                int spc = Integer.parseInt(m.group(1));

                final Decorate new_d = decorates[i];

                decorates[i] = s -> {
                    StringBuilder builder = new StringBuilder();

                    int key = 0, lastkey = -1;
                    while (key + spc < s.length() && (key = s.lastIndexOf(" ", key + spc)) != -1) {
                        builder.append(new_d.decorate(s.substring(lastkey + 1, key))).append("\n");
                        lastkey = key;
                    }

                    return builder.toString();
                };
            }

            // Applying splitter.
            if ((m = re_splitter.matcher(sp.content)).find()) {

                char c = m.group(1).charAt(0);

                final Decorate new_d = decorates[i];

                decorates[i] = s -> {
                    StringBuilder builder = new StringBuilder();

                    int last = 0;
                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == c) {
                            builder.append(new_d.decorate(s.substring(last, j)));
                            last = j + 1;
                        }
                    }

                    return builder.toString();
                };
            }

        }

    }

    @Override
    public String decorate(String... input) {
        if (writer != null) write(super.decorate(input.clone()));
        return decoration.decorate(input);
    }

    private void write(String msg) {
        if (buffer.length() > MAX_BUFFER_SIZE) flush();
        buffer.append(msg);
    }

    private void flush() {
        try {
            writer.write(buffer.toString());
            writer.flush();
        } catch (IOException e) {
            System.out.println(C.RBG + C.hex.font.to24Bit(255, 255, 255) + "IO Exception " + C.RS + C.Y + e.getCause() + C.RS);
        }
        buffer.setLength(0);
    }
}
