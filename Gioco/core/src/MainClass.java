import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class MainClass {

	public MainClass() {
	}

	private static String encodingResource = "encodings/provaPython";

	private static Handler handler;

	public static void main(String[] args) {
		// displayMatrix();

		handler = new DesktopHandler(new DLVDesktopService("dlv2"));
		InputProgram facts = new ASPInputProgram();

		try {
			facts.addObjectInput(new Int(1));
		} catch (Exception e) {
			e.printStackTrace();
		}

		handler.addProgram(facts);

		InputProgram encoding = new ASPInputProgram();
		encoding.addFilesPath("encodings/prova.py");
		encoding.addFilesPath(encodingResource);
		handler.addProgram(encoding);

		Output o = handler.startSync();

		AnswerSets answers = (AnswerSets) o;
		for (AnswerSet a : answers.getAnswersets()) {

			try {
				for (Object obj : a.getAtoms()) {
					if (!(obj instanceof Int))
						continue;
					System.out.println(((Int)obj).getNumero());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}


	}

}
