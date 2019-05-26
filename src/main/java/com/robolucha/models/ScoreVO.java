package com.robolucha.models;

public class ScoreVO implements Comparable<ScoreVO> {

	// TODO: validate if still need the id and name
	private Integer id;
	private String name;
	private int k;
	private int d;
	private int score;

	public ScoreVO() {
		this.k = 0;
		this.d = 0;
		this.score = 0;
	}

	/**
	 * for renaming
	 *
	 * @param previous
	 * @param name
	 */
	public ScoreVO(ScoreVO previous, String name) {
		this.id = previous.id;
		this.name = name;
		this.k = previous.k;
		this.d = previous.d;
		this.score = previous.score;
	}

	public ScoreVO(Integer id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	// for tests only
	ScoreVO(Integer id, String name, int k, int d, int score) {
		this.id = id;
		this.name = name;
		this.k = k;
		this.d = d;
		this.score = score;
	}

	public int compareTo(ScoreVO o) {
		int sub = o.score - this.score;

		// retturn the difference as comparable
		if (sub != 0) {
			return sub;
		} else {

			// amount of kill
			sub = o.k - this.k;
			if (sub != 0) {
				return sub;
			} else {

				// died less
				sub = this.d - o.d;
				if (sub != 0) {
					return sub;
				} else {

					// if nothing else works the older one goes first :)
					return (int) (this.id - o.id);
				}
			}

		}
	}

	@Override
	public String toString() {
		return "ScoreVO [id=" + id + ", name=" + name + ", k=" + k + ", d=" + d + ", score=" + score + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
