package utilities;

public class Type
{
 
 
 public static void delay(String text, int delay)
 {
  for (int i = 0; i < text.length(); i++)
  {
   System.out.print(text.charAt(i));
   Wait.milliseconds(delay);
  }
 }
 public static void delay(String text)
 {
  delay(text, 60);
 }
 public static void delay_line(String text)
 {
  delay(text);  System.out.println();
 }
 
 public static void delay_wait_line(String text, int wait)
 {
  delay(text);  Wait.milliseconds(wait);  System.out.println();
 }
 public static void delay_wait_line(String text)
 {
  delay_wait_line(text, 1000);
 }
 
 public static void delay_wait(String text, int wait)
 {
  delay(text);  Wait.milliseconds(wait);
 }
 public static void delay_wait(String text)
 {
  delay(text);  Wait.milliseconds(1000);
 }
 
 
 public static void delay(String text, int delay)
 {
  for (int i = 0; i < text.length(); i++)
  {
   System.out.print(text.charAt(i));
   Wait.milliseconds(delay);
  }
 }
 
 public static void delay_line(String text, int delay)
 {
  delay(text, delay);  System.out.println();
 }
 
 public static void delay_wait_line(String text, int delay, int wait)
 {
  delay(text, delay);  Wait.milliseconds(wait);  System.out.println();
 }
 
 public static void delay_wait(String text, int delay, int wait)
 {
  delay(text, delay);  Wait.milliseconds(wait);
 }
 
 
 public static void delay(String text, int delay1, int delay2)
 { 
  int delay = delay1;
  for (int i = 0; i < text.length(); i++)
  {
   System.out.print(text.charAt(i));
   Wait.milliseconds(delay);
   delay += (delay2 - delay1) / text.length();
  }
 }
 
 public static void delay_line(String text, int delay1, int delay2)
 {
  delay(text, delay1, delay2);  System.out.println();
 }
 
 public static void delay_wait_line(String text, int delay1, int delay2, int wait)
 {
  delay(text, delay1, delay2);  Wait.milliseconds(wait);  System.out.println();
 }
 
 
 
 public static void human_delay(String word, int delay1, int delay2)  // meant only as a helper method //
 {
  int delay = delay1;
  for (int i = 0; i < word.length(); i++)
  {
   // print this index's character //
   System.out.print(word.charAt(i));
   // if 3/4 of the time, delay according to the linear function //
   if (Random.whole() <= .75)  Wait.milliseconds(delay);
   // otherwise, delay a large amount (to emulate the occasional pecking for keys search difficulty aspect) //
   else  Wait.milliseconds(Random.integer_of(new int[] {Random.integer_from(100, 300), Random.integer_from(500, 600)}));
   // apply slope to the linear delay function //
   delay += (delay2 - delay1) / word.length();
  }
 }
 public static void human(String text)
 {
  String[] words = Text.separate_words(text);
  for (int i = 0; i < words.length; i++)
  {
   human_delay(words[i], Random.integer_from(45, 85), Random.integer_from(45, 85));
   if (i  <  words.length - 1)
    delay_wait(" ", Random.integer_from(65, 105), Random.integer_from(105, 150));
  }
 }
 public static void human_line(String text)
 {
  human(text);
  System.out.println();
 }
}