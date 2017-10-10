package task3;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MineSweeperImplTests {
	private MineSweeperImpl mineSweeperImpl;

	@Before
	public void beforeClass() {
		mineSweeperImpl = new MineSweeperImpl();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMineFieldCaseWrongCharacters() {
		String wrongInput = "...a";
		mineSweeperImpl.setMineField(wrongInput);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMineFieldCaseStartsWithNewLine() {
		String wrongInput = "\n...a";
		mineSweeperImpl.setMineField(wrongInput);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMineFieldCaseEmptyString() {
		String wrongInput = "";
		mineSweeperImpl.setMineField(wrongInput);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMineFieldCaseWrongLinesLength() {
		String wrongLinesLength = ".*\n*";
		mineSweeperImpl.setMineField(wrongLinesLength);
	}

	@Test()
	public void testSetMineFieldCaseOneLineWithNewLineCharacter() {
		String wrongLinesLength = ".*\n";
		mineSweeperImpl.setMineField(wrongLinesLength);
	}

	@Test()
	public void testSetMineFieldCaseOneLineWithoutNewLineCharacter() {
		String wrongLinesLength = ".*";
		mineSweeperImpl.setMineField(wrongLinesLength);
	}

	@Test
	public void testSetMineFieldCaseProperlyFormattedStringWontThrowException1() {
		String properlyFormattedString = "...\n...";
		mineSweeperImpl.setMineField(properlyFormattedString);
	}

	@Test
	public void testSetMineFieldCaseProperlyFormattedStringWontThrowException2() {
		String properlyFormattedString = ".*.\n..*";
		mineSweeperImpl.setMineField(properlyFormattedString);
	}

	@Test
	public void testSetMineFieldCaseProperlyFormattedStringCreatesCorrectArray() {
		String properlyFormattedString = ".*.\n..*";
		mineSweeperImpl.setMineField(properlyFormattedString);
		char[][] actualArray = mineSweeperImpl.getMineField();
		char[][] correctArray = new char[2][3];
		correctArray[0][0] = '.';
		correctArray[0][1] = '*';
		correctArray[0][2] = '.';
		correctArray[1][0] = '.';
		correctArray[1][1] = '.';
		correctArray[1][2] = '*';
		assertArrayEquals(correctArray[0], actualArray[0]);
		assertArrayEquals(correctArray[1], actualArray[1]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetHintFieldCaseMineFieldIsNull() {
		mineSweeperImpl.getHintField();
	}

	@Test
	public void testGetHintFieldCaseCorrect() {
		String properlyFormattedString = "*...\n..*.\n....";
		mineSweeperImpl.setMineField(properlyFormattedString);
		String actualHintField = mineSweeperImpl.getHintField();
		String expectedHintField = "*211\n12*1\n0111";
		assertEquals(expectedHintField, actualHintField);
	}

}
