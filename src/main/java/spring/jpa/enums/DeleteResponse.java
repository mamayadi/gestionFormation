package spring.jpa.enums;

public class DeleteResponse {

	private final String message;

	public DeleteResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
