package acts;

import utilities.*;
import players.*;
import scenarios.*;
import acts_combat.*;

public class Gender
{
	public static final String[]
	
	
	
	
    triggers_subsafe_uncolloquial = {"gender", "sex", "♂", "♀", "am I male", "am I a male", "am I female", "am I a female", "am I m", "am I a m", "am I f", "am I a f", "am I man", "am I a man", "am I mister", "am I a mister", "am I guy", "am I a guy", "am I boy", "am I a boy", "am I dude", "am I a dude", "am I gentleman", "am I a gentleman", "am I lad", "am I a lad", "am I feller", "am I a feller", "am I chap", "am I a chap", "am I bloke", "am I a bloke", "am I woman", "am I a woman", "am I miss", "am I a miss", "am I gal", "am I a gal", "am I girl", "am I a girl", "am I g", "am I a g", "am I dudette", "am I a dudette", "am I lady", "am I a lady", "am I lass", "am I a lass", "am I babe", "am I a babe", "am I he", "am I a he", "am I she", "am I a she"},
	
	triggers_subsafe_colloquial = {},
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"gen"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
}