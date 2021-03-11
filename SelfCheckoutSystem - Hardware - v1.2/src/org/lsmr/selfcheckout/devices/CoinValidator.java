package org.lsmr.selfcheckout.devices;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.listeners.CoinValidatorListener;

/**
 * Represents a device for optically and/or physically validating coins. Coins
 * deemed valid are moved to storage; coins deemed invalid are ejected.
 */
public final class CoinValidator extends AbstractDevice<CoinValidatorListener> implements Acceptor<Coin> {
	public final Currency currency;
	private List<BigDecimal> denominations;
	private UnidirectionalChannel<Coin> rejectionSink, storageSink;

	/**
	 * Creates a coin validator that recognizes coins of the specified denominations
	 * (i.e., values) and currency.
	 * 
	 * @param currency
	 *            The kind of currency to accept.
	 * @param denominations
	 *            An array of the valid coin denominations (like $0.05, $0.10, etc.)
	 *            to accept. Each value must be &gt;0 and unique in this array.
	 * @throws SimulationException
	 *             If either argument is null.
	 * @throws SimulationException
	 *             If the denominations array does not contain at least one value.
	 * @throws SimulationException
	 *             If any value in the denominations array is non-positive.
	 * @throws SimulationException
	 *             If any value in the denominations array is non-unique.
	 */
	public CoinValidator(Currency currency, List<BigDecimal> denominations) {
		if(currency == null)
			throw new SimulationException(new NullPointerException("currency is null"));

		if(denominations == null)
			throw new SimulationException(new NullPointerException("denominations is null"));

		if(denominations.size() < 1)
			throw new SimulationException(new IllegalArgumentException("There must be at least one denomination."));

		this.currency = currency;
		Collections.sort(denominations);

		HashSet<BigDecimal> checked = new HashSet<>();

		for(BigDecimal denomination : denominations) {
			if(denomination == null)
				throw new SimulationException(new NullPointerException("A denomination is null"));

			if(denomination.compareTo(BigDecimal.ZERO) <= 0)
				throw new SimulationException(
					new IllegalArgumentException("Non-positive denomination detected: " + denomination + "."));

			if(checked.contains(denomination))
				throw new SimulationException(new IllegalArgumentException(
					"Each denomination must be unique, but " + denomination + " is repeated."));

			checked.add(denomination);
		}

		this.denominations = denominations;
	}

	/**
	 * Connects input and output channels to the coin slot. Causes no events.
	 * 
	 * @param rejectionSink
	 *            The channel to which rejected coins are routed.
	 * @param storageSink
	 *            The channel to which valid coins are routed for storage.
	 * @throws SimulationException
	 *             If any argument is null.
	 * @throws SimulationException
	 *             If the number of standard sinks differs from the number of
	 *             denominations.
	 * @throws SimulationException
	 *             If any sink is used in more than one position.
	 */
	public void connect(UnidirectionalChannel<Coin> rejectionSink, UnidirectionalChannel<Coin> storageSink) {
		if(rejectionSink == null)
			throw new SimulationException(new NullPointerException("rejectionSink is null"));

		if(storageSink == null)
			throw new SimulationException(new NullPointerException("storageSink is null"));

		this.rejectionSink = rejectionSink;
		this.storageSink = storageSink;

		HashSet<UnidirectionalChannel<Coin>> set = new HashSet<>();

		set.add(rejectionSink);

		if(set.contains(storageSink))
			throw new SimulationException(new IllegalArgumentException("Each channel must be unique."));

		this.storageSink = storageSink;
	}

	private final Random pseudoRandomNumberGenerator = new Random();
	private static final int PROBABILITY_OF_FALSE_REJECTION = 1; /* out of 100 */

	private boolean isValid(Coin coin) {
		if(currency.equals(coin.getCurrency()))
			for(BigDecimal denomination : denominations)
				if(denomination.equals(coin.getValue()))
					return pseudoRandomNumberGenerator.nextInt(100) >= PROBABILITY_OF_FALSE_REJECTION;

		return false;
	}

	/**
	 * Tells the coin validator that the indicated coin is being inserted. If the
	 * coin is valid, a "validCoinDetected" event is announced to its listeners;
	 * otherwise, an "invalidCoinDetected" event is announced to its listeners.
	 * <p>
	 * If there is space in the machine to store a valid coin, it is passed to the
	 * sink channel corresponding to the denomination of the coin.
	 * </p>
	 * <p>
	 * If there is no space in the machine to store it or the coin is invalid, the
	 * coin is ejected to the source.
	 * </p>
	 * 
	 * @param coin
	 *            The coin to be added. Cannot be null.
	 * @throws DisabledException
	 *             if the coin validator is currently disabled.
	 * @throws SimulationException
	 *             If the coin is null.
	 * @throws SimulationException
	 *             If the coin cannot be delivered.
	 */
	public void accept(Coin coin) throws DisabledException {
		if(isDisabled())
			throw new DisabledException();

		if(coin == null)
			throw new SimulationException(new NullPointerException("coin is null"));

		if(isValid(coin)) {
			notifyValidCoinDetected(coin);

			if(storageSink.hasSpace()) {
				try {
					storageSink.deliver(coin);
				}
				catch(OverloadException e) {
					// Should never happen
					throw new SimulationException(e);
				}
			}
			else
				throw new SimulationException(new OverloadException("Coin storage is full."));
		}
		else {
			notifyInvalidCoinDetected(coin);

			try {
				rejectionSink.deliver(coin);
			}
			catch(OverloadException e) {
				// Should never happen
				throw new SimulationException(e);
			}
		}
	}

	@Override
	public boolean hasSpace() {
		return true; // Since we cannot know yet where a coin will route, assume that it is OK.
	}

	private void notifyValidCoinDetected(Coin coin) {
		for(CoinValidatorListener listener : listeners)
			listener.validCoinDetected(this, coin.getValue());
	}

	private void notifyInvalidCoinDetected(Coin coin) {
		for(CoinValidatorListener listener : listeners)
			listener.invalidCoinDetected(this);
	}
}
