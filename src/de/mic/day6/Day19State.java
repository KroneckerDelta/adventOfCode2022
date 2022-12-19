package de.mic.day6;

import lombok.Data;

@Data
public class Day19State {

	private int ores;
	private int oreBot;
	private int clay;
	private int clayBot;
	private int obsidian;
	private int obsBot;
	private int geode;
	private int geodeBot;
	private int minute;

	public Day19State(int ores, int oreBot, int clay, int clayBot, int obsidian, int obsBot, int geode, int geodeBot,
			int minute) {
		this.ores = ores;
		this.oreBot = oreBot;
		this.clay = clay;
		this.clayBot = clayBot;
		this.obsidian = obsidian;
		this.obsBot = obsBot;
		this.geode = geode;
		this.geodeBot = geodeBot;
		this.minute = minute;
	}

}
