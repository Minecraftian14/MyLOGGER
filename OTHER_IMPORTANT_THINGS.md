# Umm, complete documentation?

### Initialisation

```
// Default initialisation, prints to System.out
FLog logger = FLog.getNew();

// Prepares and provides the formatted log text to the given object.
// The object can be an OutputStream, a ByteConsumer or a StringsConsumer.    
FLog logger = FLog.getNew(object);


// The same initialisation methods are followed by ALog
FLog logger = ALog.getNew();
FLog logger = ALog.getNew(object);


// To write logs to to a file "Hello.txt".
FLog logger = FileLog.getNew("Hello.txt");
// or
FLog logger = FileLog.getNew(new File("logs/Hello.txt"));

// To write logs to a file with a default name.
FLog logger = FileLog.getNew();


// To use a custom Decoration
log.setDecorationType(Decorations.TAG); // enable tag decos.
log.setDecorationType(Decorations.RAW); // enable raw decos, ie, no strange characters.


// creating a logger to both, print to console and write to file.
FLog log = ULog.forNew()
                .add(FLog.getNew())
                .add(FileLog.getNew("new.txt"))
                .create();


// When using ConsoleDecoration, to set the color mode.
ConsoleDecoration.setColorMode(mode);
// mode can be one of [NONE, BLACK, WHITE, BIT_3, BIT_4, BIT_8, TRUE_COLOR_BIT_24] 
```

#### Usage

```
// Print formatted text 
// the arguments can be strings or objects.
logger.prt(arg1, arg2, arg3 ... argn);

// Print unformatted text
logger.raw(arg);

// Print with a custom format
logger.prtf(format1, format2...).consume(arg1, arg2, arg3...);

// To set a specific decoration type
logger.setDecorationType(name);
// name can be one of Decorations.[CONSOLE, RAW, TAG, EMPTY]

// To print a cluster of data at once, you may prepare a packet
Packet pack = logger.newPacket();

pack.prt(...);
pack.raw(...);

pack.consume();

// To use log levels 
logger.general().prt(() -> new String[]{"General", "Hello", "World"});
// or
LogLevel.DEBUG.act(() -> log.prt("General", "Hello", "World"));

// To set a log level
LogLevel.setLevel(LogLevel.WARN);
// or
LogLevel.NOTICE.activate();  
```