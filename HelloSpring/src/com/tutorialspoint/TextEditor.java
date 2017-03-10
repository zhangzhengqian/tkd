package com.tutorialspoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public class TextEditor {
	
	@Autowired
	@Qualifier("spellCheck1")
	private SpellCheck spellCheck;

	public SpellCheck getSpellCheck() {
		return spellCheck;
	}
	
	public void setSpellCheck(SpellCheck spellCheck) {
		this.spellCheck = spellCheck;
	}


	public void spell(){
		System.out.println("enter spell method");
		spellCheck.spell();
	}
}
