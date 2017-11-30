package pl.coderslab.warsztat3.model;

public class SolutionWithUser {

	private Solution solution;
	private String userName;

	public SolutionWithUser(Solution solution, String userName) {
		super();
		this.solution = solution;
		this.userName = userName;
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
