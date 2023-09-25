package lexical;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import exceptions.ScannerException;
import utils.TokenType;

public class Scanner {
	char[] source_code;
	int state;
	int pos;
	
	public Scanner(String filename) {
		try {
			String contentBuffer = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			this.source_code = contentBuffer.toCharArray();
			this.pos = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Token nextToken() throws Exception {
		char currentChar;
		String content = "";
		this.state = 0;
		while(true) {
			if(isEOF()) {
				return null;
			}
			currentChar = nextChar();
			
			switch (state) {
			case 0:
				if(isLetter(currentChar)) {
					content += currentChar;
					this.state = 1;
				} else if (isDigit(currentChar)) {
					content += currentChar;
					this.state = 2;
				} else if(isMathOperator(currentChar)) {
					return new Token(TokenType.MATH_OP, content);
				} else if(isSpace(currentChar)) {
					this.state = 0;
				} else {
					throw new Exception("Unrecognized symbol \'" + currentChar + "\'");
				}
				break;
			case 1:
				if(isLetter(currentChar) || isDigit(currentChar)) {
					content += currentChar;
					this.state = 1;
				} else {
					back();
					return new Token(TokenType.IDENTIFIER, content);
				}
				break;
			case 2:
				if(isDigit(currentChar)) {
					content += currentChar;
					this.state = 2;
				} else if(isLetter(currentChar)) {
					throw new ScannerException("Number Malformed: expected number received \'"+currentChar+"\'");
				} else {
					back();
					return new Token(TokenType.NUMBER, content);
				}
				break;
			default:
				break;
			}
		}
	}

	private boolean isSpace(char currentChar) {
		if(currentChar == ' ' || currentChar == '\n' || currentChar == '\t' || currentChar == '\r')
			return true;
		return false;
	}
	
	private boolean isMathOperator(char currentChar) {
		return !(currentChar != '+' && currentChar != '-' && currentChar != '*' && currentChar != '/');
	}

	private void back() {
		this.pos--;		
	}

	private boolean isDigit(char currentChar) {
		if(currentChar >= '0' && currentChar <= '9')
			return true;
		return false;
	}

	private boolean isLetter(char currentChar) {
		if(currentChar >= 'a' && currentChar <= 'z')
			return true;
		return false;
	}

	private char nextChar() {
		return this.source_code[pos++];
	}

	private boolean isEOF() {
		if(this.pos >= this.source_code.length)
			return true;
		return false;
	}
}
