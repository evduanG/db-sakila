package assig3.ChoiceState;

import assig3.UI.UI;

public class StateDefault extends UserChoiceState {
	private final static String MSG = "Wrong choice try again";
	
	@Override
	public void choiceAction() {
		UI.cleanBuffer();
		System.out.println(MSG);
	}
}
