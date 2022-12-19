package de.mic.day6;

import java.util.ArrayList;
import java.util.List;

import de.mic.framework.Solver;

public class Day19 extends Solver {

	public static void main(String[] args) {
		new Day19().solve("day19.txt");
	}

	@Override
	protected String solve() {

		List<Day19State> result = new ArrayList<>();

		int costOreBot = 4; // from input
		int costClayBot = 2; // from input
		int costObsOre = 3;// from input
		int costObsClay = 14;// from input
		int geodeBotCostOre = 2;// from input
		int geodeBotCostObs = 7;// from input
		result.add(new Day19State(0, 1, 0, 0, 0, 0, 0, 0, 0));

		for (int minute = 1; minute <= 24; minute++) {

			List<Day19State> future = new ArrayList<>();

			for (Day19State state : result) {

				int newOreBot = 0;
				int newObsBot = 0;
				int newClayBot = 0;
				int newGeodeBot = 0;

				if (state.getOres() >= geodeBotCostOre && state.getObsidian() >= geodeBotCostObs) {
					System.out.println("buy Geode day: " + minute);
					int geodeBot = state.getGeodeBot() + 1;
					int ores = state.getOres() - geodeBotCostOre;
					int obsidian = state.getObsidian() - geodeBotCostObs;
//					int ores, int oreBot, int clay, int clayBot, int obsidian, int obsBot, int geode, int geodeBot,

					future.add(new Day19State(ores, state.getOreBot(), state.getClay(), state.getClayBot(), obsidian,
							state.getObsBot(), state.getGeode(), geodeBot, minute));
				}
				if (ores >= costObsOre && clay >= costObsClay) {
					System.out.println("buy Obsisdian day: " + minute);
					newObsBot++;
					ores -= costObsOre;
					clay -= costObsClay;
				}
				if (ores >= costClayBot && clayBot < 5) {
					System.out.println("buy Clay day: " + minute);
					newClayBot++;
					ores -= costClayBot;
				}
				if (ores >= costOreBot && false) {
					System.out.println("buy Ores day: " + minute);
					newOreBot++;
					ores -= costOreBot;
				}

				// save money!

				ores += oreBot;
				obsidian += obsBot;
				clay += clayBot;
				geode += geodeBot;

//			executeOrder();

				oreBot += newOreBot;
				obsBot += newObsBot;
				clayBot += newClayBot;
				geodeBot += newGeodeBot;
			}
// 	replace result with future
		}
		System.out.println(
				String.format("OresBot %s ClayBots %s obsBot %s GeoBots %s", oreBot, clayBot, obsBot, geodeBot));
		return "" + geode;
	}

	private boolean someCondition() {
		// TODO Auto-generated method stub
		return true;
	}

}
