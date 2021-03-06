/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.ml4j.nn;

import org.ml4j.nn.layers.DirectedLayer;
import org.ml4j.nn.neurons.NeuronsActivation;

/**
 * Base interface for classes representing a DirectedNeuralNetwork.
 *
 * @author Michael Lavelle
 * 
 * @param <L> The type of DirectedLayer used within this NeuralNetwork
 * @param <C> The NeuralNetworkContext used with this NeuralNetwork
 * @param <N> The type of NeuralNetwork
 */
public interface DirectedNeuralNetwork<L extends DirectedLayer<?, ?>, 
    C extends NeuralNetworkContext, 
    N extends DirectedNeuralNetwork<L, C, N>> extends NeuralNetwork<L, C, N> {

  /**
   * Forward propagate the activation through this DirectedLayer
   * 
   * @param inputActivation The NeuronsActivation input on the left hand side of this 
   *        DirectedLayer.
   * @param context The context in which we are performing the forward propagation
   * @return A ForwardPropagation instance encapsulating the artifacts generated during the
   *         forward propagation through this DirectedLayer, including the NeuronsActivation output
   *         on the right hand side of the DirectedLayer
   */
  ForwardPropagation forwardPropagate(NeuronsActivation inputActivation, C context);
  
  /**
   * @return The training context used for the last epoch of training, or null if no context
   *         available.
   */
  C getLastEpochTrainingContext();
  
}
