package it.unical.object;

import com.badlogic.gdx.graphics.Texture;

import it.unical.mat.embasp.languages.Id;
import it.unical.utility.Constant;

public class Coin {

	private boolean isSpecialCoin;
	private float animation;
	private int logic_x;
	private int logic_y;

	public Coin(int logic_x, int logic_y, boolean isSpecialCoin) {
		this.logic_x = logic_x;
		this.logic_y = logic_y;
		this.isSpecialCoin = isSpecialCoin;
		this.animation = 0;
	}

	public int getLogic_x() {
		return logic_x;
	}

	public int getLogic_y() {
		return logic_y;
	}

	public void setLogic_x(int logic_x) {
		this.logic_x = logic_x;
	}

	public void setLogic_y(int logic_y) {
		this.logic_y = logic_y;
	}

	public boolean isSpecialCoin() {
		return isSpecialCoin;
	}

	public Texture getImage(float delta) {
		animation += 0.25 * delta * 25;
		animation %= 6;
		if (isSpecialCoin)
			return Constant.coin_image[(int) animation];
		else
			return Constant.coin;
	}

}
