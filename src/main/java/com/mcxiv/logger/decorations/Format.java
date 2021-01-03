package com.mcxiv.logger.decorations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * b -> bold <br />
 * i -> italics <br />
 * _ -> underlined <br />
 * h -> highlighted <br />
 * s -> small <br />
 * - -> striked <br />
 * . -> subscript <br />
 * ' -> superscript <br />
 * n -> newline, more the number of n, more new lines <br />
 * t -> tab, more the number of t, more the tabs <br />
 * <p>
 * [237abf] | [1f4] -> colour in html <br />
 * ANSI code -> colour in console
 * <p>
 * :expr -> expr to be used as separator. <br />
 * <b>Please use it in the end!!!</b>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface Format {
   String[] value();
}