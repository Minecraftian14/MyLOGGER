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
|Token             |Usage  |Console|Tag    |HTML   |
|------------------|-------|-------|-------|-------|
|pre-prefix        |here:: |Yes    |Yes    |Yes    |
|prefix            |::here:|Yes    |Yes    |Yes    |
|content           |:here: |Yes    |Yes    |Yes    |
|suffix            |:here::|Yes    |Yes    |Yes    |
|suf-suffix        |::here |Yes    |Yes    |Yes    |
|Color Tag         |$code  |Yes    |Yes    |Yes    |
|6 digit hex color |#RRGGBB|Yes    |Yes    |Yes    |
|3 digit hex color |#RGB   |Yes    |Yes    |Yes    |
|1 digit hex color |#G     |Yes    |Yes    |Yes    |
|6 digit hex bg    |@RRGGBB|Yes    |Yes    |Yes    |
|3 digit hex bg    |@RGB   |Yes    |Yes    |Yes    |
|1 digit hex bg    |@G     |Yes    |Yes    |Yes    |
|Bracket Formats   |[code] |No     |Yes    |No     |
|Time Format  |&lt;code&gt;|Yes    |Yes    |Yes    |
|Default Time      |T      |Yes    |Yes    |Yes    |
|Bold              |b      |Yes    |No     |Yes    |
|Underline         |u      |Yes    |Yes    |Yes    |
|Strikethrough     |-      |No     |Yes    |Yes    |
|Format Repeater   |~      |Yes    |Yes    |Yes    |
|String Formatter  |%-45s  |Yes    |Yes    |?      |
|New Line          |n      |Yes    |Yes    |?      |
|Tab Space         |t      |No     |No     |?      |

* Console -> a Formatting Style to decorate the console outputs.
* Tags -> a Formatting Style consisting of appending bracketed
tags to the input to inform a parser about the text format.
An example of this formatting is used in HyperLap2D to apply
simple formatting in it's Console.
* HTML -> currently it's not supported at all... As such it's 
in my To-Do list, but it's lack of use is kinda pushing it
behind of other features.


#### Structure of a formatting code
> dd