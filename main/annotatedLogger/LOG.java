package main.annotatedLogger;

import java.lang.reflect.Method;

public class LOG {

   public static void prt(String... args) {
      Decoration deco = getLastMethodCall();
      System.out.print(deco.decorate_console(args));
   }

   public static void prtl(String... args) {
      Decoration deco = getLastMethodCall();
      System.out.println(deco.decorate_console(args));
   }

   private static Decoration getLastMethodCall() {
      try {
         Class<?> obj = Class.forName(Thread.currentThread().getStackTrace()[3].getClassName());
         Format format = null;

         for (Method m : obj.getDeclaredMethods())
            if (m.getName().equals(Thread.currentThread().getStackTrace()[3].getMethodName()))
               if (m.isAnnotationPresent(Format.class))
                  format = m.getAnnotation(Format.class);

         if (format == null)
            for (Method m : obj.getMethods())
               if (m.getName().equals(Thread.currentThread().getStackTrace()[3].getMethodName()))
                  if (m.isAnnotationPresent(Format.class))
                     format = m.getAnnotation(Format.class);

         if (format != null) return new Decoration(format.value());
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return Decoration.getRandomDecoration();
   }

}

