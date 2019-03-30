package org.game_battle.model.Contract;

import java.util.Collection;
import java.util.List;

import org.game_battle.model.Implementation.Country;

public interface Strategy {
    String askText(String msg, String title);
    int askNumber(String title,  String prompt, int min, int max);
    public Collection getObjs(String prompt, Object[] objs);
	public boolean isUserOk(String title, String prompt) ;
	public  Country selectCountry(String title, String prompt, List<Country> countries) ;
}