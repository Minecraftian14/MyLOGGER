# Creating Decorations...

### The Boring Terminology
Let's first clear up what all these Terms are really about...
* **Decorate**

This is a Class Object which acts as a function on String
to give another String. Basically this is the place where
I define "how a given string is to be manipulated to get
the required formatting effect".

Note that this works only on one String input. As an
example, consider a simple for mat where we have to
prepend "Hello " before the given String. The respective
`Decorate` will be:

`Decorate dd = s -> "Hello " +s;`   

So, accordingly `dd.decorate("World!")` will return `Hello World!` 

* **Decoration**

This is a Class object which manages and creates a bunch
of `Decorate` objects.

The main idea behind having an array of `Decorate` objects is to
* be able to receive an array of `String` objects as input.
* and be able to provide each string a unique format matching element-wise.

If `Decorates` exceed the number of `Inputs`, excess are ignored.
If `Inputs` exceed the number of `decorates`, excess are left unaltered.

* **Format**

This is an annotation which can be applied to a Class/Type,
a Method or Constructor. This annotation is used to provide a
set of formatting codes so that they can be used by the `Logger`
to create a `Decoration` object to be used for formatting the 
input text.




### Creating a formatting code
#### List of all the formats available.
|Token             |Usage  |Console|Tag    |HTML   |Note |
|------------------|-------|-------|-------|-------|-----|
|pre-prefix        |here:: |Yes    |Yes    |Yes    | A prefix staying unaffected by format. |
|prefix            |::here:|Yes    |Yes    |Yes    | A prefix affected by format. |
|content           |:here: |Yes    |Yes    |Yes    | Format codes to be put here. |
|suffix            |:here::|Yes    |Yes    |Yes    | A suffix affected by format. |
|suf-suffix        |::here |Yes    |Yes    |Yes    | A suffix staying unaffected by format. |
|Color Tag         |$code  |Yes    |Yes    |Yes    | To choose a format or color from a set of 16 predefined colors. |
|6 digit hex color |#RRGGBB|Yes    |Yes    |Yes    | To assign a font color using a 6 digit hex number as #RRGGBB. |
|3 digit hex color |#RGB   |Yes    |Yes    |Yes    | To assign a font color using a 6 digit hex number as #RGB. |
|1 digit hex color |#G     |Yes    |Yes    |Yes    | To assign a font color using a 6 digit hex number as #Grey Scale Value from 0-F. |
|6 digit hex bg    |@RRGGBB|Yes    |Yes    |Yes    | To assign a background color using a 6 digit hex number as #RRGGBB. |
|3 digit hex bg    |@RGB   |Yes    |Yes    |Yes    | To assign a background color using a 6 digit hex number as #RGB. |
|1 digit hex bg    |@G     |Yes    |Yes    |Yes    | To assign a background color using a 6 digit hex number as #Grey Scale Value from 0-F. |
|Bracket Formats   |[code] |No     |Yes    |No     | To assign a font color as [RRGGBB]or[RRGGBBAA], background color as [@RRGGBB]or[@RRGGBBAA] or a format as [UNDERLINE],[STRIKE]. |
|Time Format  |&lt;code&gt;|Yes    |Yes    |Yes    | To define a time format and put a time string formatted accordingly as pre-prefix. |
|Default Time      |T      |Yes    |Yes    |Yes    | To put a time string as pre-prefix. |
|Bold              |b      |Yes    |No     |Yes    | Bold Text |
|Underline         |u      |Yes    |Yes    |Yes    | Underlined Text |
|Strikethrough     |-      |No     |Yes    |Yes    | Striked Text |
|Italics           |i      |No     |No     |Yes    | Text in Italics |
|Format Repeater   |~      |Yes    |Yes    |Yes    | To repeat the format for unmatched input strings. |
|String Formatter  |%ns    |Yes    |Yes    |?      | To format a string to fit 'n' characters, right aligned if positive, left aligned if negative. Give in a * for center align. |
|New Line          |n      |Yes    |Yes    |?      | To append a new line. |
|Tab Space         |t      |No     |No     |?      | To prepend a tab space |
|Word Wrap         |wnw    |Yes    |Yes    |?      | Word Wrap input to fit a width of 'n' and apply the same format to each line. |

* Console -> a Formatting Style to decorate the console outputs.
* Tags -> a Formatting Style consisting of appending bracketed
tags to the input to inform a parser about the text format.
An example of this formatting is used in HyperLap2D to apply
simple formatting in it's Console.
* HTML -> currently it's not supported at all... As such it's 
in my To-Do list, but it's lack of use is kinda pushing it
behind of other features.



#### Structure of a formatting code
> PP::P:C:S::SS

> pre-prefix::prefix:content-format:suffix::suf-suffix

* Pre-Prefix - this is 'text' which is prepended to input. This
text is not formatted by the rules defined in 'content-format'.
* Prefix - this 'text' is prepended to input while also sharing
the same format as defined in 'content-format'.
* Content-Format - this contains the formatting styles.
* Suffix - this 'text' is appended to input while also sharing
the same format as defined in 'content-format'.
* Suf-Suffix - this is 'text' which is appended to input. This
text is not formatted by the rules defined in 'content-format'.



#### Creating simple formatting codes - Detailed

> @Format(":b:")
* `b` -> bold

![Bold Format](images/Bold.png)

> @Format(":$B$GBBG:")
* `$B` -> Blue Font Color
* `$GBBG` -> Green Bright Blue Background

![GBBG](images/GBBG.png)

> @Format(":$YBG#00fb:", ":@ff9$B:")
* `$YBG` -> Yellow Background
* `#00f` -> ![#0000ff](https://via.placeholder.com/15/0000ff/000000?text=+) `#0000ff` font color.
* `b` -> Bold
* `@ff9` -> ![#ffff99](https://via.placeholder.com/15/ffff99/000000?text=+) `#ffff99` background color.
* `$B` -> Blue font color

![Double Format](images/Double Format.png)

> @Format(":: :@d#0a0afabu: ::", "  :: > :@e#0a0%-30s:")
* `:: :` -> a single prepending `' '` with formatting before the input, here, `"Example"`.
* `@d` -> ![#dddddd](https://via.placeholder.com/15/dddddd/000000?text=+) `#dddddd` background color. 
* `#0a0afa` -> ![#0a0afa](https://via.placeholder.com/15/0a0afa/000000?text=+) `#0a0afa` font color.
* `b` -> Bold 
* `u` -> Underline 
* `: ::` -> a single appending `' '` with formatting after the input, here, `"Example"`.
* `  ::` -> double space, prepended without any formatting before the input, here, "String Formatting". 
* `:: > :` -> literal `' > '` prepended with formatting before the input, here, "String Formatting".
* `@e` ->  ![#eeeeee](https://via.placeholder.com/15/eeeeee/000000?text=+) `#eeeeee` background color.
* `#0e0` -> ![#00ee00](https://via.placeholder.com/15/00ee00/000000?text=+) `#00ee00` font color.
* `%-30s` -> String Formatting, string adjusted to 30 characters with the input left aligned. 

![String Format](images/String Format.png)



#### Creating simple formatting codes - Summary

> @Format(":: :#F6BT:")
* `T` -> Prepend default time without formatting.
 
![Default Time](images/DefTime.png)

> @Format(":<ss;SSS>:", " -:: :n:")
* `<ss;SSS>` -> Formatted Time,
    * ss -> 2 digits for seconds.
    * ; -> for a colon.
    * SSS -> 3 digits for milli seconds.
* `n` -> newLine in the end.

```
log.prt("", "Yo!");
Thread.sleep(999);
log.prt("", "Yo!");
Thread.sleep(999);
log.prt("", "Yo!");
Thread.sleep(999);
log.prt("", "Yo!");
``` 
 
![Formatted Time](images/FormattedTime.png)

> @Format({"::Par :$Y:", ":$B%10sn: points::"})
* `::Par :` -> Prepending, fromatted, "Par "
* `%10s` -> Formatting input to fit 10 characters, right aligned.
* `: points::` -> Appending, fromatted, " points"
 
![Default Time](images/SF2.png)

> @Format({<br />
>   "\n:: :@ff4$Bbu: ::",<br />
>   ":: :@ff9#FF1493b%-18s: ::",<br />
>   ":: :@ffd#82En%-27s: ::",<br />
>   "::    :w47w@e#4B0082%-47s: ::"<br />
> })
* `Format One`
    * `\n::` - prepended, formatless, newline
    * `:: :` - prepended, formatted, white space
    * `@ff4` - ![#ffff44](https://via.placeholder.com/15/ffff44/000000?text=+) `#ffff44` background color.
    * `$B` - Blue
    * `b` - Bold
    * `u` - Underline
    * `: ::`- appended, formatted, white space
* `Format Two`
    * `:: :` - prepended, formatted, white space
    * `@ff9` - ![#ffff99](https://via.placeholder.com/15/ffff99/000000?text=+) `#ffff99` background color.
    * `#ff1493` - ![#ff1493](https://via.placeholder.com/15/ff1493/000000?text=+) `#ff1493` font color.
    * `b` - Bold
    * `%-18s` - String formatted to fit 18 characters, left aligned  
    * `: ::`- appended, formatted, white space
* `Format Three`
    * `:: :` - prepended, formatted, white space
    * `@ffd` - ![#ffffdd](https://via.placeholder.com/15/ffffdd/000000?text=+) `#ffffdd` background color.
    * `#82e` - ![#8822ee](https://via.placeholder.com/15/8822ee/000000?text=+) `#8822ee` font color.
    * `n` - new line
    * `%-27s` - String formatted to fit 27 characters, left aligned  
    * `: ::`- appended, formatted, white space
* `Format Four`
    * `::    :` - prepended, formatted, white spaces
    * `w47w` - Word Warp. Divide the input such that their lengths wont exceed 47, and apply this same formatting to each. 
    * `@e` - ![#eeeeee](https://via.placeholder.com/15/eeeeee/000000?text=+) `#eeeeee` background color.
    * `#4B0082` - ![#4B0082](https://via.placeholder.com/15/4B0082/000000?text=+) `#4B0082` font color.
    * `%-47s` - String formatted to fit 47 characters, left aligned  
    * `: ::`- appended, formatted, white space
    * `::\n`- appended, formatless, newline
```
FLog log = FLog.getNew();

log.prt("1", "Useless Sentences", "Some boring text ahead...",
        "So here we have some totally boring text just "+
        "lying around here for you to read. Though feel "+
        "totally comfortable if you wish to skip ahead."
);

log.prt("2", "Senseless Art", "Valuable Shit",
        "The main theory behind Senseless Art is the "+
        "ability to use simple sentences to create a "+
        "feeling of improtance and value describing "+
        "things which can't even be compared to shit. "+
        "If one has such wonderful creativity and a "+
        "sense of how to use such to your advantage, "+
        "consider+ the person a master of this uniquely "+
        "special art form."
);
``` 
![NICE](images/NICE.png)

> @Format(":$B %*50s w49w:")
* `w49w` - *Higher priority*, word warp the text to fit a width of 49.
* `%*50s` - *Lower priority*, Center align text and to fit a width of 50.
```
FLog log = FLog.getNew();
log.prt("The main theory behind Senseless Art is the " +
        "ability to use simple sentences to create a " +
        "feeling of improtance and value describing " +
        "things which can't even be compared to shit. " +
        "If one has such wonderful creativity and a " +
        "sense of how to use such to your advantage, " +
        "consider+ the person a master of this uniquely " +
        "special art form."
);
```
![NICE](images/center.png)


