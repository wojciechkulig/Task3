package task3;

import java.util.regex.Pattern;

public class MineSweeperImpl implements MineSweeper {
	private char[][] mineField;

	public void setMineField(String mineFieldString) throws IllegalArgumentException {
		if (!isMineFieldValid(mineFieldString))
			throw new IllegalArgumentException("mineField is not properly formatted");
		this.mineField = createMineFieldFromString(mineFieldString);

	}

	private boolean isMineFieldValid(String mineField) {
		return hasOnlyValidCharacters(mineField) && areLinesLengthEqual(mineField);
	}

	private boolean hasOnlyValidCharacters(String mineField) {
		String regex = "[\\*\\.\n]+";
		return Pattern.matches(regex, mineField);

	}

	private boolean areLinesLengthEqual(String mineField) {
		String[] lines = mineField.split("\n");
		int lengthOfFirstLine = lines[0].length();
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].length() != lengthOfFirstLine)
				return false;
		}
		return true;
	}

	private char[][] createMineFieldFromString(String string) {
		String[] lines = string.split("\n");
		char[][] mineField = new char[lines.length][lines[0].length()];
		for (int i = 0; i < lines.length; i++) {
			mineField[i] = lines[i].toCharArray();
		}
		return mineField;
	}

	public String getHintField() throws IllegalStateException {
		if (mineField == null)
			throw new IllegalArgumentException(
					"Mine field has not been initialized. Please call setMineField(String string) method with appropriate String argument");
		return createHintFieldString();
	}

	private String createHintFieldString() {
		String hintFieldString = "";
		for (int row = 0; row < mineField.length; row++) {
			for (int column = 0; column < mineField[row].length; column++) {
				hintFieldString += convertMineFieldCharacterToHintCharacter(row, column);
			}
			if (!isLastLine(row))
				hintFieldString += "\n";
		}
		return hintFieldString;
	}

	private String convertMineFieldCharacterToHintCharacter(int row, int column) {
		if (isGivenFieldABomb(row, column))
			return "*";
		return getNumberOfSurroundingBombs(row, column);
	}

	private boolean isGivenFieldABomb(int row, int column) {
		return mineField[row][column] == '*';
	}

	private String getNumberOfSurroundingBombs(int row, int column) {
		int numberOfSurroundingBombs = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				if (i == row && j == column)
					continue;
				if (isABomb(i, j))
					numberOfSurroundingBombs++;
			}
		}
		return Integer.toString(numberOfSurroundingBombs);
	}

	private boolean isABomb(int row, int column) {
		return hasMineFieldGivenField(row, column) && isGivenFieldABomb(row, column);
	}

	private boolean hasMineFieldGivenField(int row, int column) {
		return isRowValid(row) && isColumnValid(row, column);
	}

	private boolean isRowValid(int row) {
		int numberOfRowsInMineField = mineField.length;
		return row >= 0 && row < numberOfRowsInMineField;
	}

	private boolean isColumnValid(int row, int column) {
		int numberOfColumns = mineField[row].length;
		return column >= 0 && column < numberOfColumns;
	}

	private boolean isLastLine(int row) {
		return row == mineField.length - 1;
	}

	public char[][] getMineField() {
		return mineField;
	}

}
