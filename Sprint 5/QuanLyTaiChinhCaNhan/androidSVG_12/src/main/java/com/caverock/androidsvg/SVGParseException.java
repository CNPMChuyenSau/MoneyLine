
package com.caverock.androidsvg;

/**
 * Thrown by the parser if a problem is found in the SVG file.
 */

public class SVGParseException extends Exception
{
   public SVGParseException(String msg)
   {
      super(msg);
   }

   public SVGParseException(String msg, Throwable cause)
   {
      super(msg, cause);
   }

   public SVGParseException(Throwable cause)
   {
      super(cause);
   }
}
