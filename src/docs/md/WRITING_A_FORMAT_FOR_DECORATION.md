# Creating Decorations...

### The Boring Terminology

Let's first clear up what all these Terms are really about...

* **Decorate**

This is a Class Object which acts as a function on String to give another String. Basically this is the place where I
define "how a given string is to be manipulated to get the required formatting effect".

Note that this works only on one String input. As an example, consider a simple format where we have to prepend "
Hello " before the given String. The respective
`Decorate` will be:

`Decorate dd = s -> "Hello " +s;`

So, accordingly `dd.decorate("World!")` will return `Hello World!`

* **Decoration**

This is a Class object which manages and creates a bunch of `Decorate` objects.

The main idea behind having an array of `Decorate` objects is to

* be able to receive an array of `String` objects as input.
* and be able to provide each string a unique format matching element-wise.

If `Decorates` exceed the number of `Inputs`, excess are ignored. If `Inputs` exceed the number of `decorates`, excess
are left unaltered.

* **Format**

This is an annotation which can be applied to a Class/Type, a Method or Constructor. This annotation is used to provide
a set of formatting codes so that they can be used by the `Logger`
to create a `Decoration` object to be used for formatting the input text.

### Creating a formatting code

#### List of all the formats available.

*Html not yet supported*

| Token             | Usage              | Console | Tag | HTML    | Note                                                                                                                            |
|-------------------|--------------------|---------|-----|---------|---------------------------------------------------------------------------------------------------------------------------------|
| pre-prefix        | here::             | Yes     | Yes | Yes     | A prefix staying unaffected by format.                                                                                          |
| prefix            | ::here:            | Yes     | Yes | Yes     | A prefix affected by format.                                                                                                    |
| content           | :here:             | Yes     | Yes | Yes     | Format codes to be put here.                                                                                                    |
| suffix            | :here::            | Yes     | Yes | Yes     | A suffix affected by format.                                                                                                    |
| suf-suffix        | ::here             | Yes     | Yes | Yes     | A suffix staying unaffected by format.                                                                                          |
| Color Tag         | $code              | Yes     | Yes | Yes     | To choose a format or color from a set of 16 predefined colors.                                                                 |
| 6 digit hex color | #RRGGBB            | Yes     | Yes | Yes     | To assign a font color using a 6 digit hex number as #RRGGBB.                                                                   |
| 3 digit hex color | #RGB               | Yes     | Yes | Yes     | To assign a font color using a 6 digit hex number as #RGB.                                                                      |
| 1 digit hex color | #G                 | Yes     | Yes | Yes     | To assign a font color using a 6 digit hex number as #Grey Scale Value from 0-F.                                                |
| 6 digit hex bg    | @RRGGBB            | Yes     | Yes | Yes     | To assign a background color using a 6 digit hex number as #RRGGBB.                                                             |
| 3 digit hex bg    | @RGB               | Yes     | Yes | Yes     | To assign a background color using a 6 digit hex number as #RGB.                                                                |
| 1 digit hex bg    | @G                 | Yes     | Yes | Yes     | To assign a background color using a 6 digit hex number as #Grey Scale Value from 0-F.                                          |
| Bracket Formats   | [code]             | Yes     | Yes | Yes     | To assign a font color as [RRGGBB]or[RRGGBBAA], background color as [@RRGGBB]or[@RRGGBBAA] or a format as [UNDERLINE],[STRIKE]. |
| Time Format       | &lt;format&gt;     | Yes     | Yes | Yes     | To define a time format and put a time string formatted accordingly as pre-prefix.                                              |
| Default Time      | T                  | Yes     | Yes | Yes     | To put a time string as pre-prefix.                                                                                             |
| Bold              | b                  | Yes     | No  | Yes     | Bold Text                                                                                                                       |
| Faint             | f                  | No      | No  | Not Yet | Faint/Condensed Text                                                                                                            |
| Italics           | i                  | Yes     | No  | Yes     | Italics Text                                                                                                                    |
| Underline         | u                  | Yes     | No  | Yes     | Underlined Text                                                                                                                 |
| Reverse           | rev                | Yes     | No  | No      | Reverses the font and background color                                                                                          |
| Strike            | -                  | Yes     | No  | Yes     | Strikethrough                                                                                                                   |
| Double Underline  | tu                 | Yes     | No  | Yes     | Thick Underlined Text                                                                                                           |
| Frame             | frm                | Yes     | No  | Yes     | Put text in a box                                                                                                               |
| Encircle          | cir                | Yes     | No  | Yes     | Put text in a box*                                                                                                              |
| Overline          | o                  | No      | No  | Yes     | Put a line over the text                                                                                                        |
| Format Repeater   | ~                  | Yes     | Yes | Yes     | To repeat the format for unmatched input strings.                                                                               |
| String Formatter  | %ns                | Yes     | Yes | Yes     | To format a string to fit 'n' characters, right aligned if positive, left aligned if negative. Give in a * for center align.    |
| New Line          | n                  | Yes     | Yes | Yes     | To append a new line.                                                                                                           |
| Tab Space         | t                  | Yes     | Yes | Yes     | To prepend a tab space                                                                                                          |
| Word Wrap         | wnw                | Yes     | Yes | Yes     | Word Wrap input to fit a width of 'n' and apply the same format to each line.                                                   |
| Splitter          | xcx                | Yes     | Yes | Yes     | Split the given input about each occurrance of 'c'.                                                                             |
| Repeater          | R                  | Yes     | Yes | Yes     | Reuse the decorators from start when input exceeds them.                                                                        |
| Meta Data         | P C or M           | Yes     | Yes | Yes     | P -> package name, C -> class name, M -> method name.                                                                           |


* Console -> a Formatting Style to decorate the console outputs.
* Raw -> Just like console but no color/bold/underline... formatting.
* Tags -> a Formatting Style consisting of appending bracketed tags to the input to inform a parser about the text
  format. An example of this formatting is used in HyperLap2D to apply simple formatting in it's Console.
* HTML -> Most features are supported, though it needs some perfection.

#### Structure of a formatting code

> PP::P:C:S::SS

> pre-prefix::prefix:content-format:suffix::suf-suffix

* Pre-Prefix - this is 'text' which is prepended to input. This text is not formatted by the rules defined in '
  content-format'.
* Prefix - this 'text' is prepended to input while also sharing the same format as defined in 'content-format'.
* Content-Format - this contains the formatting styles.
* Suffix - this 'text' is appended to input while also sharing the same format as defined in 'content-format'.
* Suf-Suffix - this is 'text' which is appended to input. This text is not formatted by the rules defined in '
  content-format'.
