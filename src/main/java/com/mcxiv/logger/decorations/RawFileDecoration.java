package com.mcxiv.logger.decorations;

import java.util.regex.Matcher;

public class RawFileDecoration extends Decoration {


    public RawFileDecoration(String... codes) {

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

            // Apply as many tab-spaces as specified.
            for (int j = 0; j < sp.content.length(); j++)
                if (sp.content.charAt(j) == 't') format.insert(0, '\t');

            // Apply as many new-lines as specified.
            for (int j = 0; j < sp.content.length(); j++)
                if (sp.content.charAt(j) == 'n') format.append('\n');


            //


            // Final 'form' to which the input is entered.
            final String form = format.toString();

            decorates[i] = s -> String.format(form, s);


            // Applying Time if defined/specified.
            decorates[i] = timeResolver.TimeFormattingResolver(decorates[i]);


            // Applying Basic post Formatting
            decorates[i] = DecorationCommonResolvers.CommonFormattingResolver(m, sp.content, decorates[i]);

        }

    }

}
