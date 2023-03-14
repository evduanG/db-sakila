package assig3.ChoiceState;

import java.util.EventListener;
import java.util.EventObject;

public interface FinishListener extends EventListener {
	
	public void stopListener(EventObject event);

}
