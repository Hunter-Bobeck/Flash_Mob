package acts_world;

import utilities.*;
import players.*;
import acts.*;

public class Train
{
	public static final String[]
	
	
	
	
	triggers_main = {"train", "train", "train myself", "train myself", "teach myself", "learn", "learn", "practice", "practice", "try", "try out", "attempt"},
	triggers_submain = {"the ability", "the skill", "the talent"},
	
	
    triggers_subsafe_uncolloquial = Array.permute
	(
		Array.combine
		(
			triggers_main,
			new String[] {"acquire the ability to", "attain", "develop", "discipline", "evolve", "grasp", "habituate", "master", "pick up", "pull off", "rehearse", "study", "school", "educate", "improve", "instruct", "teach", "test", "tutor", "accustom", "cultivate", "enlighten", "qualify", "update", "work out"}
		),
		Array.combine
		(
			new String[] {""},
			Array.permute
			(
				new String[] {" "},
				Array.combine
				(
					triggers_submain,
					new String[] {"the capability", "the ability to", "the ability of", "the practice of", "the art of", "myself", "by myself", "all by myself", "mastery of", "how to", "to"}
				)
			)
		)
	),
	
	triggers_subsafe_colloquial = Array.permute
	(
		triggers_main,
		Array.combine
		(
			new String[] {"", "", "", "", "", "", ""},
			Array.permute
			(
				new String[] {" "},
				triggers_submain
			)
		)
	),
	
	
	triggers_subsafe = Array.combine(triggers_subsafe_uncolloquial, triggers_subsafe_colloquial),
	
	
	triggers_nonsubsafe_uncolloquial = {"p"},
	
	triggers_nonsubsafe_colloquial = {},
	
	
	triggers_nonsubsafe = Array.combine(triggers_nonsubsafe_uncolloquial, triggers_nonsubsafe_colloquial),
	
	
	
	triggers_colloquial = Array.combine(triggers_subsafe_colloquial, triggers_nonsubsafe_colloquial);
	
	
	
	
	
	
	
	

	public Train(Player player)
	{
		// setup input //
		String input = Act.input_backup;
		// detect any (class) unlearned ability trigger //
		int unlearned_ability_trigger_index = -1;
		for (int i = 0; i < player.unlearned_abilities.length; i++)
		{
			if (Array.subcontainment_case_ignored(input, player.unlearned_abilities_triggers_subsafe[i]))		unlearned_ability_trigger_index = i;
		}
		// if no unlearned ability trigger was inputted after the training trigger... //
		if (unlearned_ability_trigger_index == -1)
		{
			// determination of the the unlearned abilities' learnability //
			int learnable_count = 0, unlearnable_count = 0;
			for (int i = 0; i < player.unlearned_abilities.length; i++)
			{
				if (player.unlearned_abilities_experience_costs[i] <= player.experience)		learnable_count++;
				else		unlearnable_count++;
			}
			boolean enough_experience_to_learn = false;
			if (learnable_count >= 1)		enough_experience_to_learn = true;
			// if there are any unlearned abilities, and the player has enough experience to learn one of them, display the list of unlearned abilities and state any list of which abilities the player has enough experience to learn //
			if (player.unlearned_abilities.length > 0  &&  enough_experience_to_learn)
			{
				if (learnable_count > 1)		Type.delay_line(Random.text_of(new String[] {"You have "+player.experience+" experience, allowing you to train any of the following abilities", "With "+player.experience+" experience, you are capable of learning the following skills", "You are talented enough with "+player.experience+" experience to pick up the following abilities", "Your "+player.experience+" experience allows you to develop any of the following talents"})+":");
				else		Type.delay_line(Random.text_of(new String[] {"You have the requisite experience ("+player.experience+") to master the following skill", "With "+player.experience+" experience, you are capable of acquiring the following ability", "With "+player.experience+" experience, you are talented enough to pick up the following ability"})+"..");
				for (int i = 0; i < player.unlearned_abilities.length; i++)
				{
					if (player.unlearned_abilities_experience_costs[i] <= player.experience)		Type.delay_line("\t› "+player.unlearned_abilities[i]+"\t("+player.unlearned_abilities_experience_costs[i]+")");
				}
				if (unlearnable_count >= 1)
				{
					System.out.println();
					
					if (unlearnable_count > 1)		Type.delay_line(Random.text_of(new String[] {"You do not have enough experience to learn the following abilities", "You must become more experienced to train any of the following skills", "You lack the requisite experience for learning the following abilities"})+":");
					else		Type.delay_line(Random.text_of(new String[] {"You need more experience to learn the following ability", "Once you have enough experience, you can train yourself the following ability", "You lack the requisite experience for training the following ability", "You must acquire more experience before you can develop the following skill"})+"..");
					for (int i = 0; i < player.unlearned_abilities.length; i++)
					{
						if (player.unlearned_abilities_experience_costs[i] > player.experience)		Type.delay_line("\t› "+player.unlearned_abilities[i]+"\t("+player.unlearned_abilities_experience_costs[i]+")");
					}
				}
			}
			// else, if there are unlearned abilities but the player does not have enough experience to learn any of them, display the list of unlearned abilities and state that the player doesn't have enough experience to learn any of them //
			else if (player.unlearned_abilities.length > 0)
			{
				Type.delay_line(Random.text_of(new String[] {"You must gain more experience before learning any new talents. You currently have "+player.experience, "Your experience of "+player.experience+" is not enough to learn any new abilities", "You have but "+player.experience+" experience. You require more before you can learn any new abilities", "You require more experience before you can acquire any new abilities; right now you have "+player.experience})+".");
				System.out.println();
				if (unlearnable_count > 1)		Type.delay_line(Random.text_of(new String[] {"You have not yet learned the following abilities", "You have the following skills to learn", "With experience you will be able to learn the following abilities"})+":");
				else		Type.delay_line(Random.text_of(new String[] {"You have not yet learned the following ability", "You have only the following skill left to learn", "With experience you will finally be able to learn the following abilities"})+"..");
				for (int i = 0; i < player.unlearned_abilities.length; i++)
				{
					if (player.unlearned_abilities_experience_costs[i] > player.experience)		Type.delay_line("\t› "+player.unlearned_abilities[i]+"\t("+player.unlearned_abilities_experience_costs[i]+")");
				}
			}
			// else, state that there are no more abilities for the player to learn //
			else		Type.delay_line(Random.text_of(new String[] {"You know of no more abilities to train.", "You have mastered every ability you are aware of!", "You have done all you can to train yourself."}));
		}
		// otherwise, an unlearned ability's trigger was inputted after the training trigger... //
		else
		{
			String unlearned_ability = player.unlearned_abilities[unlearned_ability_trigger_index];
			// if the player has enough experience to learn the ability, and the player is not hungry nor thirsty, describe the player training (in a procedurally situational context), and if the description is of success then have the ability be learned //
			if (player.experience >= player.unlearned_abilities_experience_costs[unlearned_ability_trigger_index])
			{
				if (player.wetness == 0  &&  player.fullness == 0  &&  player.rest == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself, drink some water, and rest before training.", "You can't practice until you've satisfied your hunger and thirst and then rested."}));
				else if (player.wetness == 0  &&  player.fullness == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself and drink some water before training.", "You can't practice until you've satisfied your hunger and thirst."}));
				else if (player.wetness == 0  &&  player.rest == 0)		Type.delay(Random.text_of(new String[] {"You need to drink some water then rest before training.", "You can't practice until you've quenched your thirst and rested."}));
				else if (player.fullness == 0  &&  player.rest == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself then rest before training.", "You can't practice until you've satisfied your hunger and rested."}));
				else if (player.wetness == 0)		Type.delay(Random.text_of(new String[] {"You need to drink some water before training.", "You shouldn't practice until you've drank some water."}));
				else if (player.fullness == 0)		Type.delay(Random.text_of(new String[] {"You need to feed yourself before training.", "You shouldn't practice until you've settled your stomach."}));
				else if (player.rest == 0)		Type.delay(Random.text_of(new String[] {"You need to rest before training.", "You can't practice until you've rested."}));
				else
				{
					if (!player.mage)
					{
						Type.delay_wait_line(Random.text_of(new String[] {"You try out "+unlearned_ability, "You give "+unlearned_ability+" a try", "You attempt the "+unlearned_ability+" ability"})+Random.text_of(new String[] {"!", "!", "."}), 500);
					}
					else
					{
						Type.delay(Random.text_of(new String[] {"You read about "+unlearned_ability, "You study your spellbook", "You attempt to memorize "+unlearned_ability}));
						Type.delay_wait_line("...", 800, 500);
					}
					System.out.println();

					if (Random.whole() <= .549)
					{
						Type.delay_line(Random.text_of(new String[] {"You have gotten the hang of "+unlearned_ability, "You have learned "+unlearned_ability, "You have taught yourself how to "+unlearned_ability})+Random.text_of(new String[] {".", ".", "!"}));
						player.learn_ability(unlearned_ability_trigger_index);
					}
					else
					{
						if (!player.mage)		Type.delay_line("You fail to "+Random.text_of(new String[] {"train yourself to execute", "pull off"})+" the "+unlearned_ability+" ability.");
						else		Type.delay_line("You fail to "+Random.text_of(new String[] {"memorize", "grasp the spell for"})+" "+unlearned_ability+".");
					}
				}
			}
			// else, state that the player does not have enough experience to learn the ability //
			else
				Type.delay_line(Random.text_of(new String[] {"You are not experienced enough to learn "+unlearned_ability+" yet", "You don't have enough experience to learn "+unlearned_ability, "You must gain more experience before learning "+unlearned_ability})+".");
		}
	}
}