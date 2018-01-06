package it.unical.object;

import com.badlogic.gdx.graphics.Texture;

import it.unical.utility.Constant;

public class Coin extends Collectable {

	private boolean isSpecialCoin;
	private float animation;

	public Coin(int logic_x, int logic_y, boolean isSpecialCoin) {
		super(logic_x, logic_y);
		this.isSpecialCoin = isSpecialCoin;
		this.animation=0;
	}
	
	public boolean isSpecialCoin() {
		return isSpecialCoin;
	}
	
	public Texture getImage(float delta) {
		animation+=0.25*delta*25;
		animation%=6;
		if(isSpecialCoin)
			return Constant.coin_image[(int)animation];
		else
			return Constant.coin;
	}
	
	
	
	
	
	
	
	

}
