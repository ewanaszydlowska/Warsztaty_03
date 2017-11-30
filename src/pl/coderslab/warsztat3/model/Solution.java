package pl.coderslab.warsztat3.model;

import java.util.Date;

public class Solution {
	
	private int id;
	private Date created;
	private Date updated;
	private String description;
	private int exerciseId;
	private long usersId;
	
	public Solution() {
		this.id = 0;
		this.created = null;
		this.updated = null;
		this.description = "";
		this.exerciseId = 0;
		this.usersId = 0l;
	}
	
	public Solution(Date created, Date updated, String description) {
		this.id = 0;
		this.created = created;
		this.updated = updated;
		this.description = description;
		this.exerciseId = 0;
		this.usersId = 0l;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public long getUsersId() {
		return usersId;
	}

	public void setUsersId(long usersId) {
		this.usersId = usersId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

