package org.sigmah.shared.computation.instruction;

/*
 * #%L
 * Sigmah
 * %%
 * Copyright (C) 2010 - 2016 URD
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.util.Map;
import java.util.Stack;
import org.sigmah.shared.computation.dependency.Dependency;
import org.sigmah.shared.computation.value.ComputedValue;
import org.sigmah.shared.computation.value.DoubleValue;

/**
 * Average function.
 * 
 * @author Raphaël Calabro (raphael.calabro@netapsys.fr)
 * @since 2.2
 */
public class Average implements ReduceFunction, VariadicFunction {

	private int numberOfArguments = 1;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "avg";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Stack<ComputedValue> stack, Map<Dependency, ComputedValue> variables) {
		final AverageReductor reductor = new AverageReductor();
		for (int index = 0; index < numberOfArguments; index++) {
			stack.pop().feedToReductor(reductor);
		}
		stack.push(reductor.reduce());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumberOfArguments(int numberOfArguments) {
		this.numberOfArguments = numberOfArguments;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Function instantiate() {
		return new Average();
	}
	
	/**
	 * Reductor used to calculate the sum.
	 */
	private static class AverageReductor implements Reductor {
		
		private ComputedValue sum = new DoubleValue(0.0);
		private int count;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void feed(ComputedValue value) {
			sum = value.addTo(sum);
			count++;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ComputedValue reduce() {
			if (count > 0) {
				return new DoubleValue(count).divide(sum);
			} else {
				return DoubleValue.ZERO;
			}
		}
		
	}
	
}
