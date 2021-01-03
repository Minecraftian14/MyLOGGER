package com.mcxiv.logger.decorations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     Apply this annotation to a CLASS/METHOD/CONSTRUCTOR to specify a set of 'Formatting Rules' to it.
 *     Here you can give a list of different strings as formats which corresponds to the formatting applied to
 *     it's respective input when using a Formatted Log Tool.
 * </p>
 *
 * <p>
 *     A Formatting Rule consists of 5 parts:
 * </p>
 *
 * <ul>
 *     <li><p>
 *         <b>Bare Prefix</b> - A prefix applied to it's respective input text which remains unformatted,
 *         ie, the formatting defined in 'content format' has no effects on it.
 *     </p></li>
 *     <li><p>
 *         <b>Formatted Prefix</b> - This prefix is applied to it's respective input text but shares the
 *         same formatting as applied to the input.
 *     </p></li>
 *     <li><p>
 *         <b>Content Format</b> - This part contains teh definition to how a a format is defined. You can
 *         use various key codes to define certain formatting effects. Though these are restrained to if the
 *         display is capable of parsing these formats.
 *     </p></li>
 *     <li><p>
 *         <b>Formatted Suffix</b> - Just like 'formatted prefix' it is applied to it's respective input and
 *         shares the same formatting rules.
 *     </p></li>
 *     <li><p>
 *         <b>Bare Suffix</b> - Similarly this is a suffix applied which stays there without any formats.
 *     </p></li>
 * </ul>
 *
 * <p>
 *     The format to define these values is 'PP::P:C:S::SS' where PP is Bare Prefix, P is Formatted Prefix,
 *     C is Content Format, S is Formatted Suffix and SS is Bare Suffix.
 * </p>
 *
 * <p>
 *     For Example:
 *     ' :: :$B$YBGbn: :: ' - For an input 'Hello' it will first apply a simple space ' '. Then it will add
 *     in a space with Yellow Background. Then 'Hello' will appear in Blue font color and same background.
 *     After that, another space is added with the same Yellow background finally ending with an unformatted
 *     space.
 * </p>
 *
 * <p>
 *     The following are the keycodes used to define the various formatting rules which can be applied to text.
 * </p>
 *
 * <ul>
 *     <li><p><b>b</b> - makes text appear bold</p></li>
 *     <li><p><b>i</b> - makes text appear in italics</p></li>
 *     <li><p><b>_</b> - makes text have an underlining</p></li>
 *
 *     <li><p><b>s</b> - makes text small</p></li>
 *     <li><p><b>-</b> - makes text with a strike through</p></li>
 *     <li><p><b>,</b> - makes subscripted text</p></li>
 *     <li><p><b>'</b> - makes superscripted text</p></li> <br />
 *
 *     <li><p><b>n</b> - creates a new line after the input</p></li>
 *     <li><p><b>t</b> - creates a tab space before the input</p></li>
 * </ul>
 *
 * <p>
 *     The following are the ways with which one can allot color effects to input:
 * </p>
 *
 * <ul>
 *     <li><p>
 *         <b>$XYZ</b> - Console Codes <br />
 *         These are the Short Form of the ANSI color codes as defined in {@code C} <br />
 *         eg: $R -> Red font color.
 *             $BBG ->Blue background
 *     </p></li>
 *     <li><p>
 *         <b>#K</b> - Grey Scale Value <br />
 *         A hex value (0-F) representing a value between Black and White (inclusivly) with 16 divisions.
 *         In {@code C} they are obtained using {@code C.hexToGray} <br />
 *         eg: #0 -> Black color <br />
 *             #8 -> Grey color <br />
 *             #F -> White color <br />
 *         Note: Value obtained from C.hexToGray will also have to be converted to a font color or background
 *         color using {@code C.getFontColor} and {@code C.getBackColor} respectively.
 *     </p></li>
 *     <li><p>
 *         <b>$RGB</b> - RGB 3-digit Values <br />
 *         these simple refer to a 3 digit hex value (000-FFF) to define a color using RED, GREEN,
 *         and BLUE values as a single digit hex. <br />
 *         eg: $F00 -> Red font color.
 *             $FF0 -> Yellow Color
 *     </p></li>
 *     <li><p>
 *         <b>$RRGGBB</b> - RGB 6-digit Values <br />
 *         Normal hex values <br />
 *     </p></li>
 * </ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface Format {
   String[] value();
}