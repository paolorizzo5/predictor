package com.paolorizzo.predictor.client.model.v2;

import com.paolorizzo.predictor.client.model.v2.player._links;

public class Player_API {
	private String count;

	private String[] players;

	private _links _links;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String[] getPlayers() {
		return players;
	}

	public void setPlayers(String[] players) {
		this.players = players;
	}

	public _links get_links() {
		return _links;
	}

	public void set_links(_links _links) {
		this._links = _links;
	}

	@Override
	public String toString() {
		return "ClassPojo [count = " + count + ", players = " + players
				+ ", _links = " + _links + "]";
	}
}