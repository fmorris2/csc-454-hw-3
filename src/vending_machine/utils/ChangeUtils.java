package vending_machine.utils;

import java.util.ArrayList;
import java.util.List;

import vending_machine.tokens.mixed.Coin;
import vending_machine.tokens.mixed.coins.Dime;
import vending_machine.tokens.mixed.coins.Nickel;
import vending_machine.tokens.mixed.coins.Quarter;
import framework.model.persistence.ReadableBag;

public class ChangeUtils {
	private static double totalChangeValueNeeded;
	
	public static Coin[] calculateChange(ReadableBag<?> state) {
		totalChangeValueNeeded = state.getInt("value");
		List<Coin> change = calculateChangeForValue(state);
		return change.toArray(new Coin[change.size()]);
	}
	
	private static List<Coin> calculateChangeForValue(ReadableBag<?> state) {
		Coin[] potentialCoins = {new Quarter(), new Dime(), new Nickel()};
		List<Coin> change = new ArrayList<>();
		
		for(Coin coin : potentialCoins) {
			if(coinCanBeUsedForChange(coin, state)) {
				for(int i = 0; i < totalChangeValueNeeded / coin.getValue(); i++) {change.add(coin);}
				totalChangeValueNeeded %= coin.getValue();
			}
		}
		
		if(totalChangeValueNeeded > 0) {
			throw new CannotProduceChangeException();
		}
		
		return change;
	}
	
	private static boolean coinCanBeUsedForChange(Coin coin, ReadableBag<?> state) {
		return totalChangeValueNeeded >= coin.getValue() && state.getInt(coin.getName()) > 0;
	}
	
	static class CannotProduceChangeException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
}
