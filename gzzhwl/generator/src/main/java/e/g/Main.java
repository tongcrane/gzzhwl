package e.g;

import org.roundface.generator.MybatisGenerator;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		MybatisGenerator mg = new MybatisGenerator();
		String[] table = new String[] { "zh_message_tips" };
		mg.gener(table);
		// mg.generExt(table);
	}

}
