package Utilities;

@SuppressWarnings("serial")
class AlreadyFailedTestStepException extends Exception {
	public AlreadyFailedTestStepException(String s) {
		super(s);
	}
}