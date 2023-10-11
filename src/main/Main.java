package main;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import parser.GramaticaTrivialLexer;
import parser.GramaticaTrivialParser;
import parser.MiniCompilerLexer;
import parser.MiniCompilerParser;

public class Main {
	public static void main(String[] args) {
		try {
			MiniCompilerLexer scanner = new MiniCompilerLexer(CharStreams.fromFileName("programa_checkpoint2.mc"));
			CommonTokenStream stream = new CommonTokenStream(scanner);
			MiniCompilerParser parser = new MiniCompilerParser(stream);
			parser.programa();
			System.out.println("Compilation Successful!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
