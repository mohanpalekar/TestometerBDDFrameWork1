package Utilities;

import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepStarted;

public class GetTestStepName implements ConcurrentEventListener {
	
	public static String currentStepName;
	
	public EventHandler<TestStepStarted> stepHandler = new EventHandler<TestStepStarted>() {
		@Override
		public void receive(TestStepStarted event) {
			handleTestStepStarted(event);
		}
	};


	private void handleTestStepStarted(TestStepStarted event) {
		if (event.getTestStep() instanceof PickleStepTestStep) {
			PickleStepTestStep testStep = (PickleStepTestStep)event.getTestStep();
			currentStepName = testStep.getStep().getText();
			
			Logs.getLog().getLogger().info("{GetTestStepName} Current Step Name ---> "+currentStepName);
		}
	}

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestStepStarted.class, stepHandler);

	}
}