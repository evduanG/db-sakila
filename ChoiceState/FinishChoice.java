package assig3.ChoiceState;

import java.util.ArrayList;

public class FinishChoice extends UserChoiceState {
	private ArrayList<FinishListener> m_Listener;

	public FinishChoice() {
		m_Listener = new ArrayList<FinishListener>();
	}
	
	public void addListener(FinishListener i_Listener) {
		m_Listener.add(i_Listener);
	}

	@Override
	public void choiceAction() {
		for (FinishListener listener : m_Listener) {
			listener.stopListener(null);
		}
	}
}
